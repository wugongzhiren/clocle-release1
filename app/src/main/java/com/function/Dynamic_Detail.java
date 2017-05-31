package com.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.adapter.Dynamic_Comment_Adapter;
import com.adapter.Dynamic_Rv_Adapter;
import com.adapter.Rv_single_imgs_adapter;
import com.bean.Dynamic;
import com.bean.Dynamic_Comment;
import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.view.Preview_photo;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import tool.ShowToast;

/**
 * 动态详情页面
 * Created by Administrator on 2016/11/20.
 */

public class Dynamic_Detail extends AppCompatActivity{
    private Button button;
    private EditText comment;
    private SimpleDraweeView photo;
    private TextView content;
    private TextView nickname;
    private RecyclerView detail_imgs_rv;
    private RecyclerView commentRv;//评论
    private Dynamic_Comment_Adapter comment_adapter;
    private List<Dynamic_Comment> dynamic_commentList;
    private Boolean isResponse=false;//是否发表回复
    private Rv_single_imgs_adapter single_imgs_adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        final Dynamic mdynamic=(Dynamic) intent.getSerializableExtra("dynamic");
        setContentView(R.layout.dynamic_detail_layout);
        //用户评论
        comment= (EditText) findViewById(R.id.dynamic_comment_edit);
        //发表评论
        button= (Button) findViewById(R.id.dynamic_comment_publish_bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usercomment ="";
                if(!isResponse) {
                    usercomment = comment.getText().toString();//获取评论内容
                }
                else {
                    usercomment=comment.getHint().toString()+comment.getText().toString();
                }

                final Dynamic_Comment commentBean=new Dynamic_Comment();
                commentBean.setComment(usercomment);
                commentBean.setCommentuser(BmobUser.getCurrentUser(Bmob_UserBean.class));
                commentBean.setDynamicID(mdynamic.getObjectId());
                commentBean.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            ShowToast.showToast(Dynamic_Detail.this,"评论成功");
                            dynamic_commentList.add(commentBean);
                            comment_adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
        dynamic_commentList=new ArrayList<>();
        //头像
        photo= (SimpleDraweeView) findViewById(R.id.index_rv_photo_detail);
        //内容
        content= (TextView) findViewById(R.id.detail_content_tv);
        //昵称
        nickname= (TextView) findViewById(R.id.dynamic_name_tv_detail);
        //图片
        detail_imgs_rv= (RecyclerView) findViewById(R.id.detail_imgs_rv);
        //线性布局
        detail_imgs_rv.setLayoutManager(new LinearLayoutManager(this));
        //评论RV
        commentRv= (RecyclerView) findViewById(R.id.dynamic_detail_comment_rv);
        commentRv.setLayoutManager(new LinearLayoutManager(this));




        //加载评论列表
        BmobQuery<Dynamic_Comment> query=new BmobQuery<>();
        query.addWhereEqualTo("dynamicID", mdynamic.getObjectId());
        query.setLimit(1000);
        query.include("commentuser");
        query.findObjects(new FindListener<Dynamic_Comment>() {
            @Override
            public void done(List<Dynamic_Comment> list, BmobException e) {
                if(e==null){
                    dynamic_commentList.clear();
                    dynamic_commentList.addAll(list);
                    comment_adapter=new Dynamic_Comment_Adapter(Dynamic_Detail.this,dynamic_commentList);
                    //回复评论
                    comment_adapter.setOnItemClickLitener(new Dynamic_Comment_Adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View itemview, int position) {
                            //判断是否回复自己
                            if(BmobUser.getCurrentUser(Bmob_UserBean.class).getObjectId()==dynamic_commentList.get(position).getCommentuser().getObjectId()){
                                ShowToast.showToast(Dynamic_Detail.this,"不能回复自己的评论");
                                return;
                            }
                            isResponse=true;
                            comment.requestFocus();
                            comment.setHint("回复"+dynamic_commentList.get(position).getCommentuser().getUsername());
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {

                        }
                    });
                    commentRv.setAdapter(comment_adapter);
                }
            }
        });

        photo.setImageURI(mdynamic.getUser().getphotoUrl());
        content.setText(mdynamic.getDynamicContent());
        nickname.setText(mdynamic.getUser().getUsername());
        single_imgs_adapter=new Rv_single_imgs_adapter(mdynamic.getImgs(),this);
        single_imgs_adapter.setOnItemOnclickListener(new Rv_single_imgs_adapter.OnItemOnclickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Intent intent =new Intent(Dynamic_Detail.this, Preview_photo.class);
                intent.putStringArrayListExtra("urlList",((ArrayList)mdynamic.getImgs()));
                intent.putExtra("position",pos);
                startActivity(intent);
            }
        });
        detail_imgs_rv.setAdapter(single_imgs_adapter);

    }
}
