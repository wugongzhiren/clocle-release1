package com.toolview;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.clocle.huxiang.clocle.R;

/**
 * Created by Administrator on 2016/7/20.
 */
public class Edit_Activity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.edit_activity_layout);
    }
}
