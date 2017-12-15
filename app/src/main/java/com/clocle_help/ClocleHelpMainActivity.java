package com.clocle_help;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.Base_activity;
import com.adapter.MyFragmentPageAdapter;
import com.clocle.huxiang.clocle.R;
import com.view.NoSlideViewPager;

import java.util.ArrayList;
import java.util.List;

import tool.AnimationsHelper;

public class ClocleHelpMainActivity extends Base_activity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener,ViewPager.OnPageChangeListener{
    private List<Fragment> fragments=new ArrayList<>();
    private RadioGroup radioGroup;
    private NoSlideViewPager viewPager;
    private Dialog bottomSheet;
    private LinearLayout root;
    private String TAG="ClocleHelpMainActivity";
    private RadioButton publish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        setStatusBar();
        //initStatusBar();
        //hideStatus();
        setContentView(R.layout.activity_clocle_help_main);
        initFragments();
        initView();
    }

    /**
     * 实例化控件，配置adapter,配置监听器
     */
    private void initView() {
        radioGroup= (RadioGroup) findViewById(R.id.radios);
        publish= (RadioButton) findViewById(R.id.tabC);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出bottomSheet
                if(bottomSheet==null){
                    getBottomSheet().show();
                }
                else {
                    bottomSheet.show();
                }
            }
        });
        viewPager= (NoSlideViewPager) findViewById(R.id.viewpager);
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
        MyFragmentPageAdapter adapter=new MyFragmentPageAdapter(getSupportFragmentManager(),null,fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCanSlide(false);
    }

    /**
     * 实例化fragment
     */
    public void initFragments(){
        Fragment fragment1=new Clocle_help_fg();
        /*Fragment fragment2=new Fragment2();
        Fragment fragment3=new Fragment3();
        Fragment fragment4=new Fragment4();*/
        fragments.add(fragment1);
        /*fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);*/
    }


    /**
     * 切换tab页面
     */
    public void selectPage(int position){
        // 将所有的tab的icon变成灰色的
/*        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            Drawable gray = getResources().getDrawable(R.drawable.ic_launcher_foreground);
            // 不能少，少了不会显示图片
            gray.setBounds(0, 0, gray.getMinimumWidth(),
                    gray.getMinimumHeight());
            RadioButton child = (RadioButton) radioGroup.getChildAt(i);
            child.setCompoundDrawables(null, gray, null, null);
            child.setTextColor(getResources().getColor(
                    R.color.colorGray));
        }*/
        // 切换页面
        viewPager.setCurrentItem(position, true);
        // 改变图标
       /* Drawable yellow = getResources().getDrawable(R.drawable.ic_launcher_background);
        yellow.setBounds(0, 0, yellow.getMinimumWidth(),
                yellow.getMinimumHeight());
        RadioButton select = (RadioButton) radioGroup.getChildAt(position);
        select.setCompoundDrawables(null, yellow, null, null);
        select.setTextColor(getResources().getColor(
                R.color.colorAccent));*/
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.tab1 :
                selectPage(0);
                break;
            case R.id.tab2 :
                selectPage(1);
                break;
            case R.id.tab3 :
                selectPage(2);
                break;
            case R.id.tab4 :
                selectPage(3);
                break;
            default:
                break;
        }

    }

    /**
     * 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 页面跳转完后得到调用，position是你当前选中的页面的Position（位置编号）
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

    }

    /**
     * 状态改变的时候调用
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom1:
                //跳转到发布页面
                Intent intent=new Intent(this,Publish.class);
                startActivity(intent);
                break;
            case R.id.bottom2:
            //TODO
                break;
        }
    }

    /**
     * 点击发布的时候弹出
     */
    public Dialog getBottomSheet(){
        bottomSheet = new Dialog(this, R.style.clocleHelp_bottomdialog);
        bottomSheet.setCanceledOnTouchOutside(true);

        //设置dialog出现和消失时的监听器
/*        bottomSheet.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                dialogWindow.startAnimation(AnimationsHelper.getDialogEnterAni(ClocleHelpMainActivity.this));
            }
        });
        bottomSheet.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });*/
        root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.cloclehelp_bottomsheet, null);
        root.findViewById(R.id.bottom1).setOnClickListener(this);
        root.findViewById(R.id.bottom2).setOnClickListener(this);
        bottomSheet.setContentView(root);
        Window dialogWindow = bottomSheet.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);//新位置以底部为坐标原点
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 设置宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
        root.measure(0, 0);//高度，wrapcontent
        lp.height = root.getMeasuredHeight();
        //Log.i(TAG, "getBottomSheet: "+lp.height);
        lp.alpha = 1f; // 透明度
        dialogWindow.setAttributes(lp);
        return bottomSheet;
    }
}