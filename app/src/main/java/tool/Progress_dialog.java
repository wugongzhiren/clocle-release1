package tool;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clocle.huxiang.clocle.R;

/**
 * Created by Administrator on 2016/8/7.
 */
public class Progress_dialog {
    private Context mcontext;
   private LayoutInflater inflater;
    public Progress_dialog(Context context){
        this.mcontext=context;
        this.inflater=LayoutInflater.from(context);
    }
    public Dialog createLoadingDialog( String msg) {

        //LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.progress_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
       // TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                mcontext, R.anim.loading_dialog);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
       // tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(mcontext, R.style.loading_dialog);// 创建自定义样式dialog

        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return loadingDialog;

    }
}
