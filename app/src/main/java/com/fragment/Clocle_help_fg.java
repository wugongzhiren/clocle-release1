package com.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adapter.Clocle_help_adapter;
import com.adapter.Rv_single_imgs_adapter;
import com.application.App;
import com.bean.ADInfo;
import com.bean.ClocleResult;
import com.bean.Clocle_help;
import com.bean.Messages;
import com.clocle.huxiang.clocle.MainActivity;
import com.clocle.huxiang.clocle.R;
import com.common_tool.ImageFactory;
import com.facebook.drawee.view.SimpleDraweeView;
import com.http.repository.Clocle_Help_ContentRs;
import com.view.CycleViewPager;
import com.view.EmptyRecleView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tool.RecycleViewAdapter;
import tool.ShowToast;


/**
 * 圈圈帮主页
 * Created by Administrator on 2016/8/13.
 */
public class Clocle_help_fg extends Fragment {
    private EmptyRecleView recleView;
    private Clocle_help_adapter adapter;
    private Retrofit retrofit;
private Clocle_Help_ContentRs repository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.clocle_help_fg_layout,container,false);
        recleView= (EmptyRecleView) view.findViewById(R.id.clocle_help_rv);
        recleView.setEmptyView(App.getEmptyView());
        retrofit=App.getRetrofit();
        repository=retrofit.create(Clocle_Help_ContentRs.class);
        //加载请求帮助信息
        initGetHelp();
        return view;

    }

   /* @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Bmob初始化

        //getDeviceWH();
        setContentView(R.layout.clocle_help_layout);
        //加载轮播图控件
        //initialize();
        //加载热门服务用户
        inithotuser();
        //加载提供帮助用户信息
        initProvider();
        //加载请求帮助用户信息
        initGetHelp();
        pageList = new ArrayList<>();
        initView();
        initData();
    }*/

    /**
     * 加载请求帮助信息
     */
    private void initGetHelp() {
        Call<ClocleResult<Clocle_help>> call=repository.getContent(1);
        call.enqueue(new Callback<ClocleResult<Clocle_help>>() {
            @Override
            public void onResponse(Call<ClocleResult<Clocle_help>> call, Response<ClocleResult<Clocle_help>> response) {
                ClocleResult<Clocle_help> result=response.body();
                if(result.getCode()==100) {
                    //获取数据
                    List<Clocle_help> helplist = result.getT();
                    adapter=new Clocle_help_adapter(getActivity());
                    recleView.setAdapter(adapter);
                    adapter.setDatas(helplist);


                }

            }

            @Override
            public void onFailure(Call<ClocleResult<Clocle_help>> call, Throwable t) {

                ShowToast.showToast(getActivity(),"服务器连接错误");
                recleView.setAdapter(null);
            }
        });
    }

    /**
     * 加载提供帮助用户信息
     */
    private void initProvider() {
    }

    /**
     * //加载热门服务用户
     */
/*
    private void inithotuser() {
        hotuserRv = (RecyclerView) findViewById(R.id.hotuser_rv);
        hotuserRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //造假数据
        List<String> list = new ArrayList<>();
        list.add("http://e.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c62bee33059f16fdfaaf5167e4.jpg");
        list.add("http://c.hiphotos.baidu.com/image/pic/item/4d086e061d950a7bb013102e0ed162d9f2d3c932.jpg");
        list.add("http://photocdn.sohu.com/20160218/mp59453678_1455787269473_1_th.jpeg");
        list.add("http://image.tianjimedia.com/uploadImages/2015/253/56/72B7LDDSB77R.jpg");
        list.add("http://image.tianjimedia.com/uploadImages/2015/253/56/72B7LDDSB77R.jpg");
        list.add("http://image.tianjimedia.com/uploadImages/2015/253/56/72B7LDDSB77R.jpg");
        list.add("http://image.tianjimedia.com/uploadImages/2015/253/56/72B7LDDSB77R.jpg");
        hotuserRv.setAdapter(new Rv_single_imgs_adapter(list, this));
    }
*/

    /**
     * 加载轮播图控件
     */
/*
    private void initialize() {

        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);

        for (int i = 0; i < imageUrls.length; i++) {
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i);
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ImageFactory.getSimpleDraweeView(this, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ImageFactory.getSimpleDraweeView(this, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ImageFactory.getSimpleDraweeView(this, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }
*/

/*
    */
/**
     * 加载viewpager
     *//*

    private void initView() {

        viewPager = (ViewPager) findViewById(R.id.viewpager_help);
        LayoutInflater inflater = getLayoutInflater();
        viewpager1 = inflater.inflate(R.layout.viewpager_help1, null);
        //发表菜单
        publish_action_bar = (FloatingActionButton) viewpager1.findViewById(R.id.publish_action_bar);
        menu = new PopupMenu(this, publish_action_bar);
        Menu menuItem = menu.getMenu();
        menuItem.add(Menu.NONE, Menu.FIRST + 0, 0, "求助");
        menuItem.add(Menu.NONE, Menu.FIRST + 1, 1, "服务");
        //弹出发表菜单
        publish_action_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.show();
            }
        });
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case Menu.FIRST + 0:
                        Toast.makeText(Clocle_help_fg.this, "求助",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Clocle_help_fg.this, MainActivity.class);
                        startActivityForResult(intent, 301);
                        break;
                    case Menu.FIRST + 1:
                        Toast.makeText(Clocle_help_fg.this, "服务",
                                Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;

                }
                return false;
            }
        });
        viewpager2 = inflater.inflate(R.layout.viewpager_help2, null);
        help_recycleview = (RecyclerView) viewpager1.findViewById(R.id.id_recycleview);
        //下拉刷新控件
        mrefresh = (SwipeRefreshLayout) viewpager1.findViewById(R.id.help_refresh);

    }
*/

/*
    private void initData() {
        //测试用list

        //new Clocle_help_AsyncTask().execute(Constant.GET_INDEX_JSON);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(viewpager1);

        viewList.add(viewpager2);

        final PagerAdapter pageradapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };
        viewPager.setAdapter(pageradapter);
        help_recycleview.setLayoutManager(new LinearLayoutManager(this));

        help_recycleview.setAdapter(null);

        //滚动监听
        help_recycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) help_recycleview.getLayoutManager();
                //获取当前recycleview中的Item的总数

                int totalItemCount = layoutManager.getItemCount();
                //获取最后一个item的位置
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                // if(totalItemCount)
            }
        });
        mrefresh.setColorSchemeColors(Color.BLACK);
        mrefresh.post(new Runnable() {
            @Override
            public void run() {

                mrefresh.setRefreshing(true);
                Toast.makeText(Clocle_help_fg.this, "开始测试", Toast.LENGTH_SHORT).show();
                //首次进入，预加载

                //new Clocle_help_AsyncTask(pageList,Clocle_help_activity.this.mrefresh, Clocle_help_activity.this, help_recycleview).execute(Constant.GET_HELP_JSON);
               */
/* BmobQuery<Clocle_help> query = new BmobQuery<Clocle_help>();
                query.addWhereGreaterThan("peopleNum", 0);
                query.include("bmob_userBean");
                query.setLimit(15);
                //query.order("-createdAt");
                query.findObjects(new FindListener<Clocle_help>() {
                    @Override
                    public void done(List<Clocle_help> list, BmobException e) {
                        //
                        if (e == null) {
                            if (list.size() > 0) {
                                Toast.makeText(Clocle_help_activity.this, "访问结束", Toast.LENGTH_SHORT).show();
                                Log.i("返回", list.size() + "");
                                Clocle_help clocle_help = list.get(0);
                                Log.i("返回", clocle_help.getContent());
                                help_recycleview.setAdapter(new RecycleViewAdapter(Clocle_help_activity.this, list));
                                mrefresh.setRefreshing(false);
                            } else {
                                help_recycleview.setAdapter(null);
                                mrefresh.setRefreshing(false);
                            }
                        }
                    }
                });
*//*

            }
        });
        mrefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新，我绝对要将当前页面的list给传过去
                // new Clocle_help_AsyncTask(pageList,mrefresh, Clocle_help_activity.this, help_recycleview).execute(Constant.GET_HELP_JSON);
               */
/* BmobQuery<Clocle_help> query = new BmobQuery<>();
                query.addWhereGreaterThan("peopleNum", 0);
                query.include("bmob_userBean");
                query.setLimit(15);
                query.findObjects(new FindListener<Clocle_help>() {
                    @Override
                    public void done(List<Clocle_help> list, BmobException e) {
                        //
                        Toast.makeText(Clocle_help_activity.this, "刷新数据", Toast.LENGTH_SHORT).show();
                        Log.i("返回", list.size() + "");
                        Clocle_help clocle_help = list.get(0);
                        Log.i("返回", clocle_help.getContent());
                        help_recycleview.setAdapter(new RecycleViewAdapter(Clocle_help_activity.this, list));
                        mrefresh.setRefreshing(false);
                    }
                });*//*

            }
        });


    }

*/


/*    *//**
     * 获取屏幕宽高（像素）
     *//*
    public void getDeviceWH() {
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        deviceWidth = dm.widthPixels;
        deviceHeight = dm.heightPixels;
    }*/
}
