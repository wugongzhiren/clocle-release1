package tool;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bean.FolderBean;
import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.R;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/8/10.
 */
public class Popwindow extends PopupWindow {
    private int mWidth;
    private int mHeight;
    private View mConvertView;
    private Button confirm;
    private Button cancel;
    private EditText text;
    private Context mcontext;
private int flag;

    public Popwindow(Context context,int flag) {
        super(context);
        this.flag=flag;
        this.mcontext = context;
        calWidthAndHeight(context);
        mConvertView = LayoutInflater.from(context).inflate(R.layout.pop_layout1, null);
        setContentView(mConvertView);
        setWidth(mWidth);
        setHeight(mHeight);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);//外部可以点击，点击后可以消失
        setBackgroundDrawable(new BitmapDrawable());
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
        initViews();
        initEvent();
    }

    private void initEvent() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新用户昵称
                Bmob_UserBean newUser = new Bmob_UserBean();
                Bmob_UserBean userBean = BmobUser.getCurrentUser(Bmob_UserBean.class);
                newUser.setUsername(text.getText().toString());
                newUser.update(userBean.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            ShowToast.showToast(mcontext, "昵称更换成功");
                            dismiss();
                        }

                    }
                });
            }
        });
    }

    private void initViews() {
        confirm = (Button) mConvertView.findViewById(R.id.confirm);
        cancel = (Button) mConvertView.findViewById(R.id.cancel);
        text = (EditText) mConvertView.findViewById(R.id.text);
        if(flag==1){
            text.setHint("美美的人都有签名");
        }
        if(flag==0){
            text.setHint("玩转圈圈从好昵称开始");
        }
    }

    /**
     * 计算popwindows的宽高
     *
     * @param context
     */
    private void calWidthAndHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mWidth = outMetrics.widthPixels;
        mHeight = (int) (outMetrics.heightPixels * 0.3);


    }

}
