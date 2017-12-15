package tool;

import android.animation.Animator;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.clocle.huxiang.clocle.R;

/**
 * 动画的帮助类
 * Created by Hodo on 2017/12/14.
 */

public class AnimationsHelper {

    public static Animation getDialogEnterAni(Context mcontext){
        return AnimationUtils.loadAnimation(mcontext, R.anim.dialog_enter);
    }
    public static Animation getDialogExitAni(Context mcontext){
        return AnimationUtils.loadAnimation(mcontext, R.anim.dialog_exit);
    }
}
