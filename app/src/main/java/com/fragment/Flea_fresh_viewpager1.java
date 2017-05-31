package com.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adapter.Rv_single_imgs_adapter;
import com.bean.Flea_market;
import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.function.FleaMarket_details;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/10/11.
 */
public class Flea_fresh_viewpager1 extends Fragment {
    private static  SwipeRefreshLayout refreshLayout;
    public static Flea_fresh_viewpager1 newInstance(Context context, Bundle bundle) {
        Flea_fresh_viewpager1 newFragment = new Flea_fresh_viewpager1();
        newFragment.setArguments(bundle);
        //Flea_fresh_viewpager1.refreshLayout=refreshLayout;
        return newFragment;
    }

    private RecyclerView mRecyclerView;
private List<Flea_market> flea_marketList;//从服务器获取的二手信息

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("tag","onCreateView"+"创建");
        Bmob.initialize(getActivity(), "fbd7c66a38b160c5677a774971be3294");
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.flea_viewpager1, container,false);
        flea_marketList=new ArrayList<>();
        //测试用模拟数据开始
        initData();//从服务器取数据
        /*Flea_market m1=new Flea_market();
        Bmob_UserBean bean=new Bmob_UserBean();
        bean.setUsername("胡翔");
        m1.setFlea_user(bean);
        ArrayList<String> list=new ArrayList<>();
        list.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        list.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        list.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        m1.setFlea_imgs(list);
        Flea_market m2=new Flea_market();
        Bmob_UserBean bean1=new Bmob_UserBean();
        bean1.setUsername("胡翔");
        m2.setFlea_user(bean1);
        ArrayList<String> list1=new ArrayList<>();
        list1.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        list1.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        list1.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        m2.setFlea_imgs(list1);
        Flea_market m3=new Flea_market();
        Bmob_UserBean bean2=new Bmob_UserBean();
        bean2.setUsername("胡翔");
        m3.setFlea_user(bean2);
        ArrayList<String> list2=new ArrayList<>();
        list2.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        list2.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        list2.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        m3.setFlea_imgs(list2);
        Flea_market m4=new Flea_market();
        Bmob_UserBean bean3=new Bmob_UserBean();
        bean3.setUsername("胡翔");
        m4.setFlea_user(bean3);
        ArrayList<String> list3=new ArrayList<>();
        list3.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        list3.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        list3.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        m4.setFlea_imgs(list3);
        Flea_market m5=new Flea_market();
        Bmob_UserBean bean4=new Bmob_UserBean();
        bean4.setUsername("胡翔");
        m5.setFlea_user(bean4);
        ArrayList<String> list4=new ArrayList<>();
        list4.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        list4.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        list4.add("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
        m5.setFlea_imgs(list4);
        flea_marketList.add(m1);
        flea_marketList.add(m2);
        flea_marketList.add(m3);
        flea_marketList.add(m4);
        flea_marketList.add(m5);*/
        //测试用模拟数据开始结束
        //---------------------------------
        //从服务器获取数据
        return mRecyclerView;
    }
    public void initData(){
        //开发阶段返回所有数据
        BmobQuery<Flea_market> query=new BmobQuery<>();
        query.include("flea_user");
        query.findObjects(new FindListener<Flea_market>() {
            @Override
            public void done(List<Flea_market> list, BmobException e) {
                if(e==null){
                    //查询成功
                    flea_marketList.addAll(list);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
                    mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity()));
                   // refreshLayout.setRefreshing(false);
                }
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("tag","onActivityCreated");


    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private Context mContext;

        public RecyclerViewAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i("tag","onCreateViewHolder"+"创建");
            View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flea_rv, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder,final int position) {
            Log.i("tag","onBindViewHolder"+"马上要显示图片");
            //以唯一的obejectId来标识item
            holder.itemView.setTag(flea_marketList.get(position).getObjectId());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击跳转到详情页面
                    Bmob_UserBean bean=flea_marketList.get(position).getFlea_user();
                    Intent intent=new Intent(getActivity(), FleaMarket_details.class);
                    intent.putExtra("username",bean.getUsername());
                    intent.putExtra("sex",bean.getSex());
                    intent.putExtra("photourl",bean.getphotoUrl());
                    intent.putExtra("userobjectid",bean.getObjectId());
                    intent.putExtra("school",bean.getSchool());
                    intent.putExtra("price",flea_marketList.get(position).getFlea_money());
                    intent.putExtra("content",flea_marketList.get(position).getFlea_content());
                    intent.putStringArrayListExtra("imgs",(ArrayList<String>) flea_marketList.get(position).getFlea_imgs());
                    intent.putExtra("views",flea_marketList.get(position).getViews());
                    startActivity(intent);
                }
            });
            holder.img_flea_rv.setLayoutManager(new StaggeredGridLayoutManager(1,
                    StaggeredGridLayoutManager.HORIZONTAL));
            holder.img_flea_rv.setAdapter(new Rv_single_imgs_adapter(flea_marketList.get(position).getFlea_imgs(),getActivity()));
            holder.userPhoto.setImageURI(flea_marketList.get(position).getFlea_user().getphotoUrl());
            holder.flea_nickname.setText(flea_marketList.get(position).getFlea_user().getUsername());
        }

        @Override
        public int getItemCount() {
            return flea_marketList.size();
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public SimpleDraweeView userPhoto;//头像
            public TextView flea_nickname;//昵称
            public TextView strdate;//日期
            public TextView flea_money;
            public RecyclerView img_flea_rv;

            public ViewHolder(View itemview) {
                super(itemview);
                flea_nickname = (TextView) itemview.findViewById(R.id.flea_nickname);
                flea_money= (TextView) itemview.findViewById(R.id.flea_money);
                img_flea_rv= (RecyclerView) itemview.findViewById(R.id.flea_rv_imgs);
                userPhoto= (SimpleDraweeView) itemview.findViewById(R.id.flea_userphoto);

            }
        }
    }
}
