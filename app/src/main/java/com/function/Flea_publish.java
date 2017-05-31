package com.function;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Base_activity;
import com.adapter.ChoosePhotoListAdapter;
import com.application.App;
import com.bean.Flea_market;
import com.clocle.huxiang.clocle.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import tool.ShowToast;

/**
 * Created by Administrator on 2016/10/16.
 */
public class Flea_publish extends Base_activity {
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    public EditText flea_content;//内容
    private Button flea_money;//价格
    private Button flea_type;//类别
    private Button publish_bt;//发布
    private RecyclerView mLvPhoto;//已经选择的图片预览view
    private List<PhotoInfo> mPhotoList;//已经选择图片
    private ChoosePhotoListAdapter mChoosePhotoListAdapter;
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                mPhotoList.clear();
                mPhotoList.addAll(resultList);
                mPhotoList.add(new PhotoInfo());
                mChoosePhotoListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(Flea_publish.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoList = new ArrayList<>();
        mPhotoList.add(new PhotoInfo());
        setContentView(R.layout.flea_market_publish);
        initviews();


        mChoosePhotoListAdapter = new ChoosePhotoListAdapter(this, mPhotoList, mOnHanlderResultCallback);
        mLvPhoto = (RecyclerView) findViewById(R.id.lv_photo);

        mLvPhoto.setLayoutManager(new GridLayoutManager(this, 4));//设置为4列的grid布局
        mLvPhoto.setAdapter(mChoosePhotoListAdapter);

    }

    private void initviews() {
        flea_content = (EditText) findViewById(R.id.et_flea_content);
        flea_type = (Button) findViewById(R.id.bt_flea_type);
        flea_money = (Button) findViewById(R.id.bt_flea_money);
        publish_bt = (Button) findViewById(R.id.bt_flea_publish);
        publish_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPhotoList.size() == 1) {
                    ShowToast.showToast(Flea_publish.this, "总要拍张照片吧");
                    return;
                }
                if (flea_content.getText().length() == 0) {
                    ShowToast.showToast(Flea_publish.this, "还没有描述物品噢");
                    return;
                }
                if (flea_money.getText().length() == 0) {
                    ShowToast.showToast(Flea_publish.this, "给物品定个价呀");
                    return;
                }
                //上传图片
                mPhotoList.remove(mPhotoList.size() - 1);
                // String path1=mPhotoList.get(1).getPhotoPath();
                // String path2=mPhotoList.get(2).getPhotoPath();
                final String[] filePaths = new String[mPhotoList.size()];
                for (int i = 0; i < mPhotoList.size(); i++) {
                    filePaths[i] = mPhotoList.get(i).getPhotoPath();
                }

                // filePaths[0] = path1;
                //filePaths[1] = path2;
                BmobFile.uploadBatch(filePaths, new UploadBatchListener() {

                    @Override
                    public void onSuccess(List<BmobFile> files, List<String> urls) {
                        //1、files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
                        //2、urls-上传文件的完整url地址
                        ShowToast.showToast(App.getContext(), "图片上传成功");
                        if (urls.size() == filePaths.length) {//如果数量相等，则代表文件全部上传完成
                            //do something
                            Flea_market market = new Flea_market();
                            market.setFlea_content(flea_content.getText().toString());
                            market.setFlea_type("电子数码");
                            //需要common方法截取
                            market.setFlea_money(new Integer(140));
                            market.setFlea_user(App.getuser());
                            market.setFollows(new Integer(230));
                            market.setViews(new Integer(120));
                            market.setFlea_imgs(urls);
                            market.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e == null) {
                                        ShowToast.showToast(App.getContext(), "发布成功，可在我的跳蚤市场查看");
                                    } else {
                                        ShowToast.showToast(App.getContext(), "发布失败");
                                    }
                                }
                            });


                        }
                    }

                    @Override
                    public void onError(int statuscode, String errormsg) {
                        ShowToast.showToast(App.getContext(), "上传失败");
                    }

                    @Override
                    public void onProgress(int curIndex, int curPercent, int total, int totalPercent) {
                        //1、curIndex--表示当前第几个文件正在上传
                        //2、curPercent--表示当前上传文件的进度值（百分比）
                        //3、total--表示总的上传文件数
                        //4、totalPercent--表示总的上传进度（百分比）
                    }
                });
                Flea_publish.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.i("tag", "onDestroy");
        super.onDestroy();
    }
}
