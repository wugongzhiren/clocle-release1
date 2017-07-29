package com.clocle.huxiang.clocle;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.LazyFrament;
import com.application.App;
import com.bean.Dynamic;
import com.function.Dynamic_publish;
import com.google.gson.Gson;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.http.repository.DynamicManage;
import com.view.ninegrid.NineGridTestLayout;
import com.view.nrv.NRecyclerView;
import com.view.nrv.base.BaseLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tool.ShowToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * Created by Administrator on 2016/7/6.
 */
public class Index_fg extends LazyFrament implements
        BaseLayout.RefreshAndLoadingListener {
/*public class Index_fg extends Activity implements
        BaseLayout.RefreshAndLoadingListener {*/
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    DynamicManage service = App.getRetrofit().create(DynamicManage.class);
    public List<Dynamic> messages = new ArrayList<Dynamic>();
    public MyAdapter myadapter;//首页动态Adpater
    //private EmptyRecleView recyclerView;
    private FloatingActionButton dynamic_action_bt;
    private LinearLayout linearLayout;
    private NRecyclerView nRecyclerView;
    int page = 1;//当前页面第几页
    private int totalpages = 1;//动态内容总页数
    private boolean isloaded = false;//是否加载过
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //public void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.index_fg, container, false);

        //setContentView(R.layout.index_fg);
        dynamic_action_bt = (FloatingActionButton) view.findViewById(R.id.dynamic_action_bt);
        dynamic_action_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), Dynamic_publish.class);
                //startActivity(intent);
            }
        });
        /*linearLayout= (LinearLayout) view.findViewById(R.id.dynamic_content);
        linearLayout.setOnClickListener(this);*/
        /*recyclerView= (EmptyRecleView) view.findViewById(R.id.main_rv);*/
        //StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager( 2 , StaggeredGridLayoutManager.VERTICAL ); //两列，纵向排列


        //初始化Nrv
        nRecyclerView = (NRecyclerView) view.findViewById(R.id.dynamic_rv);
        nRecyclerView.setNoItemAnimator(true);
        //暂时设置为线性布局
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        // mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nRecyclerView.setLayoutManager(mLayoutManager);

        nRecyclerView.setOnRefreshAndLoadingListener(this);

        ViewGroup adVentureView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.adventure_layout, (ViewGroup) view.findViewById(android.R.id.content), false);
        nRecyclerView.setAdtureView(adVentureView);

        //没有数据时，显示“我是有底线的”
        ViewGroup bottomView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.bottom_layout, (ViewGroup) view.findViewById(android.R.id.content), false);
        nRecyclerView.setBottomView(bottomView);

        //datas = Arrays.asList(getResources().getStringArray(R.array.data));

        //addItems();
        //adapter = new MyAdapter(currentDatas);
        //nRecyclerView.setAdapter(adapter);
        nRecyclerView.setTotalPages(page);
        //XXX初始化view的各控件
        isPrepared = true;
        lazyLoad();


        return view;


    }

    private void initData(final int currentPage) {
        //初次加载获取第一页数据
        Call<List<Dynamic>> call = service.getDynamic(currentPage - 1);
        call.enqueue(new Callback<List<Dynamic>>() {
            @Override
            public void onResponse(Call<List<Dynamic>> call, Response<List<Dynamic>> response) {
                addItems(response.body());
                totalpages = response.body().get(0).getTotalpages();
                //设置是否可以加载更多
                nRecyclerView.setPullLoadEnable(currentPage >= totalpages ? false : true);
                if (!isloaded) {
                    myadapter = new MyAdapter(messages);
                    nRecyclerView.setAdapter(myadapter);

                    nRecyclerView.setTotalPages(totalpages);
                } else {
                    myadapter.setItems(messages);
                    isloaded = true;
                }
                nRecyclerView.endRefresh();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                ShowToast.showToast(App.getContext(), "服务器开小差了，请稍后再试");
            }
        });
    }


    // @Override
    protected void lazyLoad() {
       /* if (!isPrepared || !isVisible) {
            return;
        }*/
        //填充各控件的数据,加载第一页
        initData(1);
    }


    //nrv下拉刷新
    @Override
    public void refresh() {
        //下拉刷新和页面初次加载的行为一样
        initData(1);
    }

    //nrv上拉加载
    @Override
    public void load() {
        page++;
        if (page >= totalpages) {
            nRecyclerView.pullNoMoreEvent();
        } else {
            initData(page);
            nRecyclerView.endLoadingMore();
        }
    }


    /**
     * 获取到的信息添加到List中
     *
     * @param PageItems 每次下拉刷新或者加载更多时从服务器获取到的List
     */
    private void addItems(List<Dynamic> PageItems) {


        if (nRecyclerView.isRefreshing()) {
            messages.clear();
            messages.addAll(PageItems);
        } else {
            messages.addAll(PageItems);
        }
    }


    //首页动态nrv的adapter
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NrvViewHoader> {

        private List<Dynamic> data;

        public MyAdapter(List<Dynamic> data) {
            this.data = data;
        }

        @Override
        public NrvViewHoader onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.index_rv_item, parent, false);
            return new NrvViewHoader(view);
        }

        @Override
        public void onBindViewHolder(NrvViewHoader holder, int position) {
            Dynamic dynamic = data.get(position);
            //将图片URL转为List
/*            List<String> imgsList=new ArrayList<>();
           ;
            List<String> imgsList = App.getGson().fromJson(dynamic.getImgHttpUrlJson(),
                    new TypeToken<List<String>>() {
                    }.getType());*/


            holder.nameTV.setText(dynamic.getUserName());
            holder.imgsLayout.setUrlList( dynamic.getImgHttpUrlJson().split(";"));
        }

        //addItem后调用
        public void setItems(List<Dynamic> data) {
            this.data = data;
            this.notifyDataSetChanged();
        }

        public void clearData() {
            this.data.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        public class NrvViewHoader extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView indexPhoto;//用户头像
            private TextView contentTv;//文字内容
            private TextView nameTV;//昵称
            private NineGridTestLayout imgsLayout;
            //TODO 还有时间，点赞数，评论数需要显示

            public NrvViewHoader(View itemView) {
                super(itemView);
                imgsLayout= (NineGridTestLayout) itemView.findViewById(R.id.dynamic_imgs);
                //indexImg = (ImageView) itemView.findViewById(R.id.index_rv_img);
                contentTv = (TextView) itemView.findViewById(R.id.index_rv_content);
                indexPhoto = (ImageView) itemView.findViewById(R.id.index_rv_photo);
                nameTV = (TextView) itemView.findViewById(R.id.dynamic_name_tv);
                contentTv.setOnClickListener(this);
                indexPhoto.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                   /* case R.id.index_rv_content:
                        //跳转到详情页
                        Intent intent=new Intent(getActivity(), Dynamic_Detail.class);
                        intent.putExtra("dynamic",(Dynamic)itemView.getTag());
                        mcontext.startActivity(intent);*/
                }
            }
        }
    }

}









