package tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**根据图片的url和Imgview的大小来对图片进行压缩
 * Created by Administrator on 2016/8/13.
 */
public class DecodeSampleBitmapFromUrl {
    public Bitmap decodeSampleBitmapFromPath(String path, ImageView img) throws NoSuchFieldException {
        //获得图片的宽和高，并不把图片加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);//options获得了图片的实际的宽高
        ImageSize imageSize;
        //根据Imgview获取合适的宽高
        imageSize=getImageViewSize(img);
        options.inSampleSize = caculateSampleSize(options, imageSize.width, imageSize.height);
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

        return imageSize;}
    private class ImageSize {
        int width;
        int height;
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

}
