package com.rx;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import rx.Observable;
import rx.Subscriber;
import tool.Utils;

/**
 * Created by Administrator on 2016/11/3.
 */

public class AnimatorOnSubscribe implements Observable.OnSubscribe<Void> {
    final Animator animator;

    public AnimatorOnSubscribe(Animator animator) {
        this.animator = animator;
    }

    @Override
    public void call(final Subscriber<? super Void> subscriber) {
        Utils.checkUiThread();//检查是否在UI线程调用，否则不能播放动画
        AnimatorListenerAdapter adapter=new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                subscriber.onNext(null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                subscriber.onCompleted();
            }
        };

        animator.addListener(adapter);
        animator.start();//先绑定监听器再开始
    }
}