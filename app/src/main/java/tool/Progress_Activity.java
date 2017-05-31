package tool;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.clocle.huxiang.clocle.R;

/**
 * Created by Administrator on 2016/8/6.
 */
public class Progress_Activity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_activity);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        finish();
        return true;
    }
}
