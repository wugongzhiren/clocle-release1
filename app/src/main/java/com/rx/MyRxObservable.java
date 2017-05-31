package com.rx;

import android.animation.Animator;

import rx.Observable;
import tool.Utils;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MyRxObservable {
    public static Observable<Void> add(Animator animator){
        Utils.checkNotNull(animator,"Animation is null");

        return Observable.create(new AnimatorOnSubscribe(animator));
    }
}
