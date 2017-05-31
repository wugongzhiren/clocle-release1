package com.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.Base_activity;
import com.clocle.huxiang.clocle.R;
import com.fragment.Flea_fresh_viewpager1;
import com.view.MyfleMarket_viewpager;

import java.util.ArrayList;
import java.util.List;

/**圈圈二手市场首页
 * Created by Administrator on 2016/10/11.
 */
public class FleaMarket extends Base_activity {
    private MyfleMarket_viewpager flea_viewpager;
    private ArrayList<Fragment> fragmentsList;
    private int currIndex;
    private FloatingActionButton flea_publish;
    private SwipeRefreshLayout flea_refresh;
    List<String> titles;
    private TabLayout tab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flea_market_layout);
        flea_publish= (FloatingActionButton) findViewById(R.id.flea_publish);
       // flea_refresh= (SwipeRefreshLayout) findViewById(R.id.flea_refresh);
//        flea_refresh.setRefreshing(true);
        flea_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FleaMarket.this,Flea_publish.class);
                startActivity(intent);
            }
        });
        tab= (TabLayout) findViewById(R.id.flea_tabs);
        flea_viewpager= (MyfleMarket_viewpager) findViewById(R.id.flea_body);
        initviewPager();
    }

    private void initviewPager() {
        titles = new ArrayList<>();
        titles.add("新鲜二手货");
        titles.add("同校好货");
        tab.addTab(tab.newTab().setText(titles.get(0)));
        tab.addTab(tab.newTab().setText(titles.get(1)));
        tab.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.__picker_item_photo_border_selected));

        fragmentsList = new ArrayList<Fragment>();
        Bundle bundle = new Bundle();
        Fragment viewpager1 = Flea_fresh_viewpager1.newInstance(
                this, bundle);
        Fragment viewpager2 = Flea_fresh_viewpager1.newInstance(
                this, bundle);


        fragmentsList.add(viewpager1);
        fragmentsList.add(viewpager2);
        TabFragmentPagerAdapter tabFragmentPagerAdapter = new TabFragmentPagerAdapter(
                getSupportFragmentManager(), fragmentsList);
        flea_viewpager.setAdapter(new TabFragmentPagerAdapter(
                getSupportFragmentManager(), fragmentsList));
        flea_viewpager.setCurrentItem(0);
        flea_viewpager.setOnPageChangeListener(null);


        tab.setupWithViewPager(flea_viewpager);
        tab.setTabsFromPagerAdapter(tabFragmentPagerAdapter);
    }


    class TabFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> mFragmentsList;

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public TabFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList) {
            super(fm);
            mFragmentsList = fragmentsList;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentsList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentsList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

    }
}
