package com.function;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;

import android.widget.ImageView;
import android.widget.TextView;

import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.MainActivity;
import com.clocle.huxiang.clocle.R;
import com.clocle.huxiang.clocle.Reg;
import com.rx.MyRxObservable;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 欢迎页，判断用户是否登录过，
 * 没有就跳转到登录页面，有的话就跳转到引导页
 * Created by Administrator on 2016/11/3.
 */

public class WelComeActivity extends AppCompatActivity{
private Boolean isLogin = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


       /* */

        setContentView(R.layout.welcome_activity);
       // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        getActionBar().hide();
        ImageView mImageView= (ImageView) findViewById(R.id.img_welcome);
       /* TextView view= (TextView) findViewById(R.id.text3);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
        animator.setDuration(5000);
        animator.start();

        ObjectAnimator animatorY =ObjectAnimator.ofFloat(mImageView,"scaleY",1f,1.2f);

        animatorY.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator animatorX =ObjectAnimator.ofFloat(mImageView,"scaleX",1f,1.2f);
        animatorX.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet set =new AnimatorSet();
        set.play(animatorY).with(animatorX);
        set.setDuration(5000);
        set.start();*/


        Animator animation = AnimatorInflater.loadAnimator(this, R.animator.welcome_animator);
        animation.setTarget(mImageView);
        MyRxObservable.add(animation)
                .subscribeOn(AndroidSchedulers.mainThread())//指定订阅的Observable对象的call方法运行在ui线程中
                .observeOn(Schedulers.io())
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        //filter过滤：判断是否登录过，如果false就会跳过下面的操作
                        Bmob_UserBean bean= BmobUser.getCurrentUser(Bmob_UserBean.class);
                        if(bean !=null){
                            isLogin = true;

                        }

                        return isLogin;
                    }
                })
                .flatMap(new Func1<Void, Observable<String>>() {
                    @Override
                    public Observable<String> call(Void aVoid) {
                        //flatMap转换：和 map() 有一个相同点：它也是把传入的参数转化之后返回另一个对象。但需要注意，和 map()不同的是， flatMap() 中返回的是个 Observable 对象
                        //上面两步都为true才能到这里，取出用户信息，开始联网。
                       // String userAccount= (String) SPUtils.get(getApplicationContext(),Constant.USERACCOUNT,"");
                       // String userPassword= (String) SPUtils.get(getApplicationContext(),Constant.USERPASSWORD,"");
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//最后统一回到UI线程中处理
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        if (isLogin){
                            Intent intent =new Intent(WelComeActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Intent intent =new Intent(WelComeActivity.this,Reg.class);
                            startActivity(intent);
                            finish();
                        }

                        //所有操作完成，统一回调这里，实现Activity跳转功能
                       // MainActivity.launch(WelcomeActivity.this);
                      //  finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //异常处理，根据每个项目而定，这里不具体写
                       // Logger.d(e.toString());
                    }

                    @Override
                    public void onNext(String tokenBean) {
                        //只有网络成功才会回调这里，这里可以保存网络数据。
                    }
                });
    }
}
