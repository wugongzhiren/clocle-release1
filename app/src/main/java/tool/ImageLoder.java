package tool;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.LogRecord;

/**
 * 本地图片选择器
 * Created by Administrator on 2016/8/7.
 */
public class ImageLoder {
    private static ImageLoder instance;
    private LruCache<String, Bitmap> mlruCache;//缓存对象
    private ExecutorService mThreadPool;//线程池
    private static final int DEAFAULT_THREAD_COUNT = 1;
    private Type mType = Type.LIFO;//队列调度方式
    private LinkedList<Runnable> mTaskQueue;//任务队列
    private Thread mPoolThread;//后台轮询线程
    private Handler mPoolThreadHandler;
    private Handler mUIhander;
    private Semaphore semaphorePoolThreadHandler = new Semaphore(0);
    private Semaphore semaphoreThreadPool;

    public enum Type {
        FIFO, LIFO
    }

    private ImageLoder(int threadCount, Type type) {
        init(threadCount, type);
    }

    /**
     * 初始化方法
     *
     * @param threadCount
     * @param type
     */
    private void init(int threadCount, Type type) {
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //线程池取出一个任务执行
                        mThreadPool.execute(getTask());
                        try {
                            semaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //释放一个信号量
                semaphorePoolThreadHandler.release();
                Looper.loop();
            }
        };
        mPoolThread.start();
        int MaxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = MaxMemory / 8;
        mlruCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
        //创建线程池
        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mTaskQueue = new LinkedList<Runnable>();
        mType = type;
        semaphoreThreadPool = new Semaphore(threadCount);
    }

    private Runnable getTask() {
        if (mType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else if (mType == Type.LIFO) {
            return mTaskQueue.removeLast();
        }
        return null;
    }

    public static ImageLoder getInstance() {
        if (instance == null) {
            synchronized (ImageLoder.class) {
                if (instance == null) {
                    instance = new ImageLoder(DEAFAULT_THREAD_COUNT, Type.LIFO);
                }
            }
        }
        return instance;
    }

    public static ImageLoder getInstance(int threadCount, Type type) {
        if (instance == null) {
            synchronized (ImageLoder.class) {
                if (instance == null) {
                    instance = new ImageLoder(DEAFAULT_THREAD_COUNT, Type.LIFO);
                }
            }
        }
        return instance;
    }

    public void loadImage(final String path, final ImageView imageview) {
        imageview.setTag(path);
        if (mUIhander == null) {
            mUIhander = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    //获取得到图片，为imageview回调设置图片
                    ImgBeanHolder holder = (ImgBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    ImageView imageview = holder.imageview;
                    String path = holder.path;
                    //将path与getTag存储路径进行比较
                    if (imageview.getTag().toString().equals(path)) {
                        imageview.setImageBitmap(bm);
                    }
                }
            };
        }
        //根据path在缓存中获取bitmap
        Bitmap bm = getBitmapFromLruCache(path);
        if (bm != null) {
            refreshBitmap(path, imageview, bm);
        } else {
            addTask(new Runnable() {
                @Override
                public void run() {
                    //加载图片，图片压缩
                    //获得图片需要显示的大小
                    ImageSize imageSize = null;
                    try {
                        imageSize = getImageViewSize(imageview);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    //压缩图片
                    Bitmap bm = decodeSampleBitmapFromPath(path, imageSize.width, imageSize.height);
                    //把图片加入到缓存
                    addBitmapToLruCache(path, bm);
                    refreshBitmap(path, imageview, bm);
                    semaphoreThreadPool.release();
                }
            });
        }
    }

    private void refreshBitmap(String path, ImageView imageView, Bitmap bitmap) {
        Message message = Message.obtain();
        ImgBeanHolder holder = new ImgBeanHolder();
        holder.bitmap = bitmap;
        holder.path = path;
        holder.imageview = imageView;
        message.obj = holder;
        mUIhander.sendMessage(message);
    }

    /**
     * 将图片加入到缓存
     *
     * @param path
     * @param bm
     */
    protected void addBitmapToLruCache(String path, Bitmap bm) {
        if (getBitmapFromLruCache(path) == null) {
            if (bm != null) {
                mlruCache.put(path, bm);
            }

        }
    }

    /**
     * 根据图片的宽和高对图片进行压缩
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    protected Bitmap decodeSampleBitmapFromPath(String path, int width, int height) {
        //获得图片的宽和高，并不把图片加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);//options获得了图片的实际的宽高
        options.inSampleSize = caculateSampleSize(options, width, height);
//使用获得的inSampleSize再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;


    }

    /**
     * 根据需求的图片宽高和图片的实际宽高计算SampleSize
     *
     * @param options
     * @param reqheight
     * @param reqwidth
     * @return
     */
    private int caculateSampleSize(BitmapFactory.Options options, int reqwidth, int reqheight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqwidth || height > reqheight) {
            int widthRadio = Math.round(width * 1.0f / reqwidth);
            int heightRadio = Math.round(height * 1.0f / reqheight);
            inSampleSize = Math.max(widthRadio, heightRadio);
        }
        return inSampleSize;
    }

    /**
     * 根据imageview获取适当的宽和高
     *
     * @param imageView
     * @return
     */
    protected ImageSize getImageViewSize(ImageView imageView) throws NoSuchFieldException {
        ImageSize imageSize = new ImageSize();
        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        int width = imageView.getWidth();//获取imageview的实际宽度
        if (width <= 0) {
            width = lp.width;//获取imageview在layout中声明的宽度
        }
        if (width <= 0) {
            width = getImageviewFieldValue(imageView, "mMaxWidth");//检查最大值
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }
        int height = imageView.getHeight();//获取imageview的实际宽度

        if (height <= 0) {
            height = lp.height;//获取imageview在layout中声明的宽度
        }
        if (height <= 0) {
            height = getImageviewFieldValue(imageView, "mMaxHeight");//检查最大值
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }
        imageSize.width = width;
        imageSize.height = height;

        return imageSize;
    }

    private static int getImageviewFieldValue(Object object, String fieldName) throws NoSuchFieldException {
        int value = 0;
        Field field = ImageView.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            int fieldValue = field.getInt(object);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    private synchronized void addTask(Runnable runnable) {
        mTaskQueue.add(runnable);
        try {
            if (mPoolThreadHandler == null)
                semaphorePoolThreadHandler.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mPoolThreadHandler.sendEmptyMessage(0x110);
    }

    /**
     * 根据path在缓存中获取bitmap
     *
     * @param key
     * @return
     */
    private Bitmap getBitmapFromLruCache(String key) {
        return mlruCache.get(key);
    }

    private class ImgBeanHolder {
        Bitmap bitmap;
        ImageView imageview;
        String path;
    }

    private class ImageSize {
        int width;
        int height;
    }
}
