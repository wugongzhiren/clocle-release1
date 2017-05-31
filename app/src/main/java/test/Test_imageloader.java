/*
package test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bean.FolderBean;
import com.clocle.huxiang.clocle.Publish;
import com.clocle.huxiang.clocle.R;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import tool.ImageAdapter;
import tool.ImageLoder;
import tool.Popwindow;
import tool.SerializableMap;
import tool.ViewHolder;


*/
/**
 * Created by Administrator on 2016/8/8.
 *//*

public class Test_imageloader extends Activity implements View.OnClickListener {
    private GridView mGridView;
    private List<String> mImgs;
    private ImageAdapter mimageAdatpter;
    private RelativeLayout mBottomly;
    private TextView mDirname;
    private TextView mDirCount;
    private File mCurrentDir;
    private int mMaxCount;
    private Button button;
    private Popwindow mpopwindow;


    private ArrayList<String> imgsurl=new ArrayList<String>();
    private Intent intent;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x110) {
                mprogressdialog.dismiss();
                //绑定数据到view中
                datatoview();
                initPopwindow();
            }

        }
    };
    private ProgressDialog mprogressdialog;

    private List<FolderBean> mFolderBeans = new ArrayList<FolderBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_picker);
intent=getIntent();
        initViews();
        initData();
        initEvents();
    }

    */
/**
     * 将选择的图片放入intent中
     * @param v
     *//*

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_imgs:
                //myMap=new SerializableMap();
                if (imgsurl.isEmpty()) {
                    Toast.makeText(this, "当前没有选择一张图片", Toast.LENGTH_SHORT).show();
                    return;}
                if(imgsurl.size()>3){
                    Toast.makeText(getApplicationContext(),"只能选择三张图片哦",Toast.LENGTH_SHORT).show();
                    return;}
                intent.putStringArrayListExtra("url",imgsurl);
                setResult(401,intent);
                this.finish();
*/
/*switch (imgsurl.size()){
    case 1:
        intent.putExtra("img1",imgsurl.get(0));
        setResult(401,intent);
        this.finish();
        break;
    case 2:
        intent.putExtra("img1",imgsurl.get(0));
        intent.putExtra("img2",imgsurl.get(1));
        setResult(402,intent);
        this.finish();
        break;
    case 3:
        intent.putExtra("img1",imgsurl.get(0));
        intent.putExtra("img2",imgsurl.get(1));
        intent.putExtra("img3",imgsurl.get(2));
        setResult(403,intent);
        this.finish();
        break;
    defaultphoto:
        Toast.makeText(this,"只能选择3张图片哦！！",Toast.LENGTH_SHORT).show();
        break;*//*

}
                    */
/*myMap.setMap(map);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("imgmap", myMap);
                    intent.putExtras(bundle);
                startActivity(intent);*//*

                    //setResult(401, intent);
                    //Test_imageloader.this.finish();


        }


    private void initPopwindow() {
        mpopwindow = new Popwindow(this, mFolderBeans);

        mpopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });
        mpopwindow.setOnDirSelectedListener(new Popwindow.OnDirSelectedListener() {
            @Override
            public void onSelected(FolderBean folderBean) {
                mCurrentDir = new File(folderBean.getDir());
                mImgs = Arrays.asList(mCurrentDir.list(new FilenameFilter() {
                            @Override
                            public boolean accept(File dir, String filename) {
                                if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg"))
                                    return true;
                                return false;
                            }
                        })
                );
                mimageAdatpter = new ImageAdapter(imgsurl,Test_imageloader.this, mImgs, mCurrentDir.getAbsolutePath());
                mGridView.setAdapter(mimageAdatpter);
                mDirCount.setText((mImgs.size()) + "");//加引号的目的是转化为字符串
                mDirname.setText(folderBean.getName());
                mpopwindow.dismiss();
            }
        });
    }

    */
/**
     * 内容区域变亮
     *//*

    private void lightOn() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);

    }

    private void initEvents() {
        mBottomly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopwindow.setAnimationStyle(R.style.mypopwindow_anim);
                mpopwindow.showAsDropDown(mBottomly, 0, 0);

                lightOff();

            }
        });

    }

    private void lightOff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = .3f;
        getWindow().setAttributes(lp);

    }

    private void datatoview() {
        if (mCurrentDir == null) {
            Toast.makeText(this, "未扫描到图片", Toast.LENGTH_SHORT).show();
            return;
        }
        mImgs = Arrays.asList(mCurrentDir.list());
        mimageAdatpter = new ImageAdapter(imgsurl,this, mImgs, mCurrentDir.getAbsolutePath());
        mGridView.setAdapter(mimageAdatpter);
        mDirCount.setText(mMaxCount + "");
        mDirname.setText(mCurrentDir.getName());

    }

    */
/**
     * 利用contentProvider扫描手机中的所有图片
     *//*

    private void initData() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "当前存储卡不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        mprogressdialog = ProgressDialog.show(this, null, "正在加载...");
        new Thread() {
            @Override
            public void run() {
                super.run();
                Uri mImguri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;//MediaStore是安卓多媒体数据库
                ContentResolver cr = Test_imageloader.this.getContentResolver();
                //查询内存卡中照片的url，
                Cursor cursor = cr.query(mImguri, null, MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_ADDED);
                Set<String> mdirPath = new HashSet<String>();
                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) {
                        continue;
                    }
                    String dirPath = parentFile.getAbsolutePath();
                    FolderBean folderBean = null;
                    if (mdirPath.contains(dirPath)) {
                        continue;
                    } else {
                        mdirPath.add(dirPath);
                        folderBean = new FolderBean();
                        folderBean.setDir(dirPath);
                        folderBean.setFirstImgPath(path);
                    }

                    if (parentFile.list() == null) {
                        continue;
                    }
                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg"))
                                return true;
                            return false;
                        }
                    }).length;
                    folderBean.setCount(picSize);
                    mFolderBeans.add(folderBean);
                    if (picSize > mMaxCount) {
                        mMaxCount = picSize;
                        mCurrentDir = parentFile;
                    }

                }
                cursor.close();
                //通知hangdler扫描图片完成
                mhandler.sendEmptyMessage(0x110);
            }
        }.start();
    }

    private void initViews() {
        mGridView = (GridView) findViewById(R.id.image_picker);
        mBottomly = (RelativeLayout) findViewById(R.id.id_bottom_ly);
        mDirname = (TextView) findViewById(R.id.id_dir_name);
        mDirCount = (TextView) findViewById(R.id.id_dir_count);
        button= (Button) findViewById(R.id.confirm_imgs);//确定已经选择的图片
        button.setOnClickListener(this);
    }


}
*/
