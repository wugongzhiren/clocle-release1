package tool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clocle.huxiang.clocle.R;

/**
 * Created by Hodo on 2017/12/18.
 */

public class ToolbarHelper implements View.OnClickListener{
    private ActivityTask activityTask;
    private String title;//标题
    private Button back;
    private Button menu;
    private TextView titleContent;
    private RelativeLayout myToolbar;
    public interface ActivityTask{
        void action(View v);
    }
    public void setActivityTask(ActivityTask task){
        this.activityTask=task;
    }
    private Context context;
    /**
     * 设置toolbar
     * @param isOrigin 是否显示自定义布局
     * @param context
     * @param toolBar
     * @param title 标题
     * @param leftIcon 返回图标
     */
    public void setToolBar(boolean isOrigin,final Context context, Toolbar toolBar, String title, int leftIcon){
        this.context=context;
        this.title=title;
        myToolbar=toolBar.findViewById(R.id.toolbar_rl);
        if(!true) {
            myToolbar.setVisibility(View.GONE);
            if (toolBar != null) {
                toolBar.setTitle(title);
            }
            ((AppCompatActivity) context).setSupportActionBar(toolBar);
            //设置返回的icon
            toolBar.setNavigationIcon(leftIcon);
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((AppCompatActivity) context).finish();
                }
            });
        }
        else{
            myToolbar.setVisibility(View.VISIBLE);
            back=toolBar.findViewById(R.id.back);
            menu=toolBar.findViewById(R.id.toolbar_bt);
            titleContent=toolBar.findViewById(R.id.title);
            back.setOnClickListener(this);
            menu.setOnClickListener(this);
            titleContent.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                ((AppCompatActivity) context).finish();
                break;
            case R.id.toolbar_bt:
                activityTask.action(v);
                break;
            case R.id.title:
                titleContent.setText(title);
                break;
                default:
                    break;

        }
    }
}
