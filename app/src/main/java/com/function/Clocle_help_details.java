package com.function;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bean.Clocle_help_coment;
import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 圈圈帮的详情页面
 * Created by Administrator on 2016/8/16.
 */
public class Clocle_help_details extends Activity {
    private ListView detailList;
    private List<Clocle_help_coment> comments;
    private SimpleDraweeView photo;
    private TextView nickname;
    private ImageView sex;
    private TextView school;
    private TextView detailsText;
    private GridView imgsGridview;
    private ArrayList<String> urls;
    private ListView detailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.clocle_help_details);
        detailsList= (ListView) findViewById(R.id.details_list);
        Intent intent = getIntent();
        String name = intent.getStringExtra("nickname");
        String text = intent.getStringExtra("detailsText");
        String userphoto = intent.getStringExtra("userphoto");
        urls = intent.getStringArrayListExtra("urls");
        detailList = (ListView) findViewById(R.id.details_list);//实例化详情页的listview
        View headerview =getLayoutInflater().inflate(R.layout.clocle_help_details_head, null);
        imgsGridview = (GridView) headerview.findViewById(R.id.clocle_help_details_photos);//附带图片
        if (urls == null) {
            imgsGridview.setAdapter(null);
        }
        imgsGridview.setAdapter(new GridviewAdapter());
       photo = (SimpleDraweeView) headerview.findViewById(R.id.clocle_help_details_photo);//用户头像
        nickname = (TextView) headerview.findViewById(R.id.nickname);
        nickname.setText(name);
        sex = (ImageView) headerview.findViewById(R.id.help_sex);
        sex.setImageResource(R.mipmap.woman);
        school = (TextView) headerview.findViewById(R.id.school);
        school.setText("安徽中医药大学");
        detailsText = (TextView) headerview.findViewById(R.id.clocle_help_details_text);
        detailsText.setText(text);
        photo.setImageURI(Uri.parse(userphoto));//设置头像
        detailList.addHeaderView(headerview);
        comments = new ArrayList<>();
        //从服务器获取评论
        Bmob_UserBean user=new Bmob_UserBean();
        user.setUsername("测试666");
        comments.add(new Clocle_help_coment("好的",user,"11231"));
        detailsList.setAdapter(new Detalis_comment_adapter());
    }

    class Detalis_comment_adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return comments.size();
        }

        @Override
        public Object getItem(int position) {
            return comments.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Detalis_comment_holder holder=null;

            if (convertView == null) {
                holder=new Detalis_comment_holder();
convertView=LayoutInflater.from(Clocle_help_details.this).inflate(R.layout.listview_detail_list,parent,false);
                holder.commentUsernickname= (TextView) convertView.findViewById(R.id.comment_nickname);
            holder.commentText= (TextView) convertView.findViewById(R.id.comment_text);
                convertView.setTag(holder);
            }
            else holder= (Detalis_comment_holder) convertView.getTag();
            holder.commentUsernickname.setText(comments.get(position).getCommentUser().getUsername());
            holder.commentText.setText(comments.get(position).getComent());
            return convertView;
        }
    }
    class Detalis_comment_holder{
        public TextView commentUsernickname;
        public TextView commentText;
    }

    class GridviewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return urls.size();
        }

        @Override
        public Object getItem(int position) {
            return urls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GridviewHolder holder = null;
            if (convertView == null) {

                convertView = LayoutInflater.from(Clocle_help_details.this).inflate(R.layout.single_img, parent, false);
                holder = new GridviewHolder();
                holder.img = (SimpleDraweeView) convertView.findViewById(R.id.item_single_img);
                convertView.setTag(holder);


            } else holder = (GridviewHolder) convertView.getTag();
            holder.img.setImageURI(urls.get(position));
            return convertView;
        }

    }

    class GridviewHolder {
        public SimpleDraweeView img;

    }
}
