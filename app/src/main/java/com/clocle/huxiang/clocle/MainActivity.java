package com.clocle.huxiang.clocle;


import android.app.FragmentManager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.Base_activity;
import com.adapter.MyfragmentPagerAdapter;
import com.gesture.MygestureListener;

import java.util.ArrayList;


public class MainActivity extends Base_activity {


    private TextView tapdongtai;
    private TextView tabfaxian;
    private TextView tabfriend;
    private TextView tabfankui;
    private FragmentManager manager;
    public Index_fg fg1;
    private Faxian_fg fg2;
    private Friend_fg fg3;
    private My_fg fg4;
    private SlidingPaneLayout paneLayout;
    private MygestureListener gesture;
    private GestureDetector detector;
    private Boolean needOpenMenu;
    private FragmentTabHost tabHost;
private ViewPager mainVp;
    private TabLayout mainTab;

    private LayoutInflater inflater;
    private ArrayList<String> titles;//viewpager的标题
    private ArrayList<Fragment> fragments ;//装载进viewpager的fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        hideStatus();
        setContentView(R.layout.activity_main);
        mainVp= (ViewPager) findViewById(R.id.main_vp);
        mainTab= (TabLayout) findViewById(R.id.main_tab);
        inflater = getLayoutInflater();
        titles=new ArrayList<>();
        fragments=new ArrayList<>();
        titles.add("分享");
        titles.add("消息");
        titles.add("发现");
        titles.add("我的");
        fragments.add(new Index_fg());
        fragments.add(new Friend_fg());
        fragments.add(new Faxian_fg());
        fragments.add(new My_fg());
        MyfragmentPagerAdapter adapter=new MyfragmentPagerAdapter(getSupportFragmentManager(),titles,fragments);
        mainVp.setAdapter(adapter);
        mainTab.setupWithViewPager(mainVp);
       // mainTab.setTabsFromPagerAdapter(adapter);
        /*Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show();
       *//* gesture=new MygestureListener();
       detector=new GestureDetector(this,gesture);*//*
        // manager = getFragmentManager();
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(),
                R.id.index_fragment);
        // 2. 新建TabSpec
        TabHost.TabSpec spec1 = tabHost.newTabSpec("TAB1");
        View view1 = inflater.inflate(R.layout.index_bottom, null);
        TextView tabtext1 = (TextView) view1.findViewById(R.id.tabtext);
        ImageView tabimg1 = (ImageView) view1.findViewById(R.id.tabimg);
        tabimg1.setImageResource(R.mipmap.tab1);
        tabtext1.setText("新鲜事");
        //spec需要一个indicator,indic需要view
        spec1.setIndicator(view1);
        tabHost.addTab(spec1, Index_fg.class, null);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("TAB2");
        View view2 = inflater.inflate(R.layout.index_bottom, null);
        TextView tabtext2 = (TextView) view2.findViewById(R.id.tabtext);
        ImageView tabimg2 = (ImageView) view2.findViewById(R.id.tabimg);
        tabimg2.setImageResource(R.mipmap.friend);
        tabtext2.setText("好友");
        //spec需要一个indicator,indic需要view
        spec2.setIndicator(view2);

        // 3. 添加TabSpec

        tabHost.addTab(spec2, Friend_fg.class, null);


        TabHost.TabSpec spec3 = tabHost.newTabSpec("TAB3");
        View view3 = inflater.inflate(R.layout.index_bottom, null);
        TextView tabtext3 = (TextView) view3.findViewById(R.id.tabtext);
        ImageView tabimg3 = (ImageView) view3.findViewById(R.id.tabimg);
        tabimg3.setImageResource(R.mipmap.faxian);
        tabtext3.setText("发现");
        //spec需要一个indicator,indic需要view
        spec3.setIndicator(view3);
        tabHost.addTab(spec3, Faxian_fg.class, null);


        TabHost.TabSpec spec4 = tabHost.newTabSpec("TAB4");
        View view4 = inflater.inflate(R.layout.index_bottom, null);
        TextView tabtext4 = (TextView) view4.findViewById(R.id.tabtext);
        ImageView tabimg4 = (ImageView) view4.findViewById(R.id.tabimg);
        tabimg4.setImageResource(R.mipmap.fankui);
        tabtext4.setText("反馈");
        //spec需要一个indicator,indic需要view
        spec4.setIndicator(view4);

        // 3. 添加TabSpec

        tabHost.addTab(spec4, My_fg.class, null);*/

       /* // 2. 新建TabSpec
        spec = tabhost.newTabSpec(TAB_CONTACT);
        contactIndicator = new TabIndicatorView(this);
        contactIndicator.setTabIcon(R.drawable.tab_icon_contact_normal,
                R.drawable.tab_icon_contact_focus);
        contactIndicator.setTabTitle("通讯录");
        contactIndicator.setTabUnreadCount(10);
        spec.setIndicator(contactIndicator);
        // 3. 添加TabSpec
        tabhost.addTab(spec, ContactFra.class, null);

        // 2. 新建TabSpec
        spec = tabhost.newTabSpec(TAB_DISCOVER);
        discoverIndicator = new TabIndicatorView(this);
        discoverIndicator.setTabIcon(R.drawable.tab_icon_discover_normal,
                R.drawable.tab_icon_discover_focus);
        discoverIndicator.setTabTitle("发现");
        discoverIndicator.setTabUnreadCount(10);
        spec.setIndicator(discoverIndicator);
        // 3. 添加TabSpec
        tabhost.addTab(spec, DiscoverFra.class, null);

        // 2. 新建TabSpec
        spec = tabhost.newTabSpec(TAB_ME);
        meIndicator = new TabIndicatorView(this);
        meIndicator.setTabIcon(R.drawable.tab_icon_me_normal,
                R.drawable.tab_icon_me_focus);
        meIndicator.setTabTitle("我");
        meIndicator.setTabUnreadCount(10);
        spec.setIndicator(meIndicator);
        // 3. 添加TabSpec
        tabhost.addTab(spec, MeFra.class, null);

        // 去掉分割线
        tabhost.getTabWidget().setDividerDrawable(android.R.color.white);

        // 初始化 tab选中
        tabhost.setCurrentTabByTag(TAB_CHAT);
        chatIndicator.setTabSelected(true);

        // 设置tab切换的监听
        tabhost.setOnTabChangedListener(this);*/
        bindviews();
        // tapdongtai.performClick();//模仿一次点击，聚焦在index
        //Toast.makeText(this,"滑动",Toast.LENGTH_SHORT).show();
    }


    private void bindviews() {
        paneLayout = (SlidingPaneLayout) findViewById(R.id.index_drawer);
        paneLayout.setParallaxDistance(200);
    }




}
