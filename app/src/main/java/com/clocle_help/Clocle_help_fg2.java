package com.clocle_help;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bean.ADInfo;
import com.bean.ServiceUser;
import com.bean.User;
import com.clocle.huxiang.clocle.R;
import com.clocle_help.adapter.HotUserAdapter;
import com.clocle_help.adapter.LatestDatasAdapter;
import com.clocle_help.adapter.LatestUserAdapter;
import com.common_tool.ImageFactory;
import com.view.CycleViewPager;
import com.view.EmptyRecleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 圈圈帮-服务页-tab2
 * Created by Hodo on 2017/12/21.
 */

public class Clocle_help_fg2 extends android.support.v4.app.Fragment {
    private CycleViewPager cycleViewPager;
    private RecyclerView hotUserRv;//热门用户列表
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<User> hotUserList =new ArrayList<>();
    private RecyclerView lastestUser;//最新用户列表
    private List<ServiceUser> serviceUsers=new ArrayList<>();//最新用户列表数据
    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
                Toast.makeText(getActivity(),
                        "position-->" + info.getContent(), Toast.LENGTH_SHORT)
                        .show();
            }

        }

    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.clocle_help_fg2_layout,container,false);
        //加载轮播图控件
        //initialize();
        //加载热门服务用户
        //inithotuser(view);
        //加载提供帮助用户信息
        initProvider(view);
        //加载数据
        initData();
        return view;
    }

    /**
     * 加载轮播图控件
     */
    private void initialize() {

        //cycleViewPager = (CycleViewPager) getChildFragmentManager()
              //  .findFragmentById(R.id.cycle_viewpager);

        for (int i = 0; i < imageUrls.length; i++) {
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i);
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ImageFactory.getImageWithGlide(getActivity(), infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ImageFactory.getImageWithGlide(getActivity(), infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ImageFactory.getImageWithGlide(getActivity(), infos.get(0).getUrl()));

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
    /**
     * 加载热门
     */
    private void inithotuser(View view) {
        //获取数据
        getHotUserList();

        hotUserRv=view.findViewById(R.id.hotuser_rv);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hotUserRv.setLayoutManager(manager);
        HotUserAdapter adapter=new HotUserAdapter(getActivity());
        hotUserRv.setAdapter(adapter);
        adapter.setData(hotUserList);


    }

    /**
     * 加载提供帮助者
     */
    private void initProvider(View view) {
        lastestUser=view.findViewById(R.id.lastestUserRv);

        lastestUser.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

       /* LatestUserAdapter adapter=new LatestUserAdapter(getActivity());
        lastestUser.setAdapter(adapter);
*/
        getLatestUserDatas();
        LatestDatasAdapter adapter=new LatestDatasAdapter(R.layout.cloclehelp_latestuseritem,serviceUsers,getActivity());
        lastestUser.setAdapter(adapter);
       /* Log.i("clocle_help2", "serviceUsers: "+serviceUsers.size());
        adapter.setDatas(serviceUsers);*/
    }

    /**
     * 加载数据
     */
    private void initData() {
    }

    /**
     * 加载热门用户
     */
    private void getHotUserList(){
        //todo
        //从服务器获取数据
        //造假数据
        User user=new User();
        user.setUsername("我是豆豆a");
        user.setPhotoUrl("https://wx1.sinaimg.cn/mw690/69c1dcbbgy1fllg3gowdoj20hs0hsmzo.jpg");
        User user1=new User();
        user1.setUsername("爱笑的兔纸");
        user1.setPhotoUrl("https://wx3.sinaimg.cn/mw690/69c1dcbbgy1fllg3j1c7ij20xc0xc0wl.jpg");
        User user2=new User();
        user2.setUsername("小果子");
        user2.setPhotoUrl("https://wx2.sinaimg.cn/mw690/69c1dcbbgy1fllg3sihqmj20m80m8ae3.jpg");
        User user3=new User();
        user3.setUsername("喜欢微笑zzzz");
        user3.setPhotoUrl("https://wx3.sinaimg.cn/mw690/69c1dcbbgy1fllg3fhhq4j20xc0xdgq4.jpg");
        User user4=new User();
        user4.setUsername("不会忧伤");
        user4.setPhotoUrl("https://wx2.sinaimg.cn/mw690/69c1dcbbgy1fllg43obgcj214b14bqa6.jpg");
        User user5=new User();
        user5.setUsername("做梦的瓜皮");
        user5.setPhotoUrl("https://wx4.sinaimg.cn/mw690/69c1dcbbgy1fllg3zd4nwj20xc0xcdlv.jpg");
        User user6=new User();
        user6.setUsername("我是豆豆a");
        user6.setPhotoUrl("https://wx1.sinaimg.cn/mw690/69c1dcbbgy1fllg3gowdoj20hs0hsmzo.jpg");
        User user7=new User();
        user7.setUsername("我是豆豆a");
        user7.setPhotoUrl("https://wx1.sinaimg.cn/mw690/69c1dcbbgy1fllg3gowdoj20hs0hsmzo.jpg");
        User user8=new User();
        user8.setUsername("我是豆豆a");
        user8.setPhotoUrl("https://wx1.sinaimg.cn/mw690/69c1dcbbgy1fllg3gowdoj20hs0hsmzo.jpg");
        hotUserList.add(user);
        hotUserList.add(user1);
        hotUserList.add(user2);
        hotUserList.add(user3);
        hotUserList.add(user4);
        hotUserList.add(user5);
        hotUserList.add(user6);
        hotUserList.add(user7);
        hotUserList.add(user8);
    }

    /**
     * 获取最新服务者数据
     */
    public void getLatestUserDatas(){
        //造假数据
        List<String> imgs=new ArrayList<>();
        imgs.add("http://ww4.sinaimg.cn/bmiddle/4a4673aagy1fhahigitiaj20ic0qik2l.jpg");
        List<String> imgs1=new ArrayList<>();
        imgs1.add("https://wx4.sinaimg.cn/mw690/005XrVZply1fmm9sroyvxj30qo0qo427.jpg");
        List<String> imgs2=new ArrayList<>();
        imgs2.add("https://wx2.sinaimg.cn/mw690/005XrVZply1fgqcz90nz4j30e70e8adg.jpg");
        List<String> imgs3=new ArrayList<>();
        imgs3.add("https://wx2.sinaimg.cn/mw690/005XrVZply1fmiklbu07tj30k00zkq5x.jpg");
        User user=new User();
        user.setUsername("我是豆豆a");
        user.setPhotoUrl("https://wx1.sinaimg.cn/mw690/69c1dcbbgy1fllg3gowdoj20hs0hsmzo.jpg");
        User user1=new User();
        user1.setUsername("爱笑的兔纸");
        user1.setPhotoUrl("https://wx3.sinaimg.cn/mw690/69c1dcbbgy1fllg3j1c7ij20xc0xc0wl.jpg");
        User user2=new User();
        user2.setUsername("小果子");
        user2.setPhotoUrl("https://wx2.sinaimg.cn/mw690/69c1dcbbgy1fllg3sihqmj20m80m8ae3.jpg");
        User user3=new User();
        user3.setUsername("喜欢微笑zzzz");
        user3.setPhotoUrl("https://wx3.sinaimg.cn/mw690/69c1dcbbgy1fllg3fhhq4j20xc0xdgq4.jpg");
        //TODO
        ServiceUser data=new ServiceUser();
        data.setServiceContent("拿快递，代课，带早点，快来找我哟");
        data.setImgs(imgs);
        data.setUser(user);
        ServiceUser data1=new ServiceUser();
        data1.setServiceContent("跑腿装系统，我最擅长");
        data1.setImgs(imgs1);
        data1.setUser(user1);
        ServiceUser data2=new ServiceUser();
        data2.setServiceContent("陪跑步，陪健身，陪打球，天天运动身体棒棒哒");
        data2.setImgs(imgs2);
        data2.setUser(user2);
        ServiceUser data3=new ServiceUser();
        data3.setServiceContent("考研的妹子陪你上自习，一起学习共同进步吧");
        data3.setImgs(imgs3);
        data3.setUser(user3);
        serviceUsers.add(data);
        serviceUsers.add(data1);
        serviceUsers.add(data2);
        serviceUsers.add(data3);
    }
}
