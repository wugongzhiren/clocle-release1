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

import com.application.App;
import com.bean.Flea_market;
import com.clocle.huxiang.clocle.R;

import java.util.ArrayList;
import java.util.List;

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
/*    private List<PhotoInfo> mPhotoList;//已经选择图片
    private ChoosePhotoListAdapter mChoosePhotoListAdapter;*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mPhotoList = new ArrayList<>();
       // mPhotoList.add(new PhotoInfo());
        setContentView(R.layout.flea_market_publish);
        initviews();


        //mChoosePhotoListAdapter = new ChoosePhotoListAdapter(this, mPhotoList, mOnHanlderResultCallback);
        mLvPhoto = (RecyclerView) findViewById(R.id.lv_photo);

        mLvPhoto.setLayoutManager(new GridLayoutManager(this, 4));//设置为4列的grid布局
        //mLvPhoto.setAdapter(mChoosePhotoListAdapter);

    }

    private void initviews() {
        flea_content = (EditText) findViewById(R.id.et_flea_content);
        flea_type = (Button) findViewById(R.id.bt_flea_type);
        flea_money = (Button) findViewById(R.id.bt_flea_money);
        publish_bt = (Button) findViewById(R.id.bt_flea_publish);
        publish_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (true) {
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
                //mPhotoList.remove(mPhotoList.size() - 1);
                // String path1=mPhotoList.get(1).getPhotoPath();
                // String path2=mPhotoList.get(2).getPhotoPath();
                //final String[] filePaths = new String[mPhotoList.size()];
               /* for (int i = 0; i < mPhotoList.size(); i++) {
                    filePaths[i] = mPhotoList.get(i).getPhotoPath();
                }*/

                // filePaths[0] = path1;
                //filePaths[1] = path2;

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
