package test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.clocle.huxiang.clocle.R;
import com.constant.Constant;
import com.function.Flea_publish;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;


/**
 * Created by Administrator on 2016/8/1.
 */
public class Test2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // new Clocle_help_AsyncTask().execute(Constant.GET_INDEX_JSON);
        setContentView(R.layout.test_fresco);
        Button button= (Button) findViewById(R.id.test_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Test2.this, Flea_publish.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        Log.i("tag","onResume");
        super.onResume();
    }
}
