package tool;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/19.
 */
public class ShowToast {
    private static Toast toast;
    public static void showToast(Context mcontext,String msgs){
        if(toast==null){
            Toast.makeText(mcontext,msgs,Toast.LENGTH_SHORT).show();
        }
        else {
            toast.setText(msgs);
            toast.setGravity(Gravity.BOTTOM,0,0);
        }
    }
}
