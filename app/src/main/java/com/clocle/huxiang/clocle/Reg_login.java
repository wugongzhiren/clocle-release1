package com.clocle.huxiang.clocle;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/7/23.
 */
public class Reg_login extends Activity implements View.OnClickListener{
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_login);
        editText= (EditText) findViewById(R.id.phoneedit);
        button= (Button) findViewById(R.id.nextbutton);
        button.setOnClickListener(this);
   /*     SMSSDK.initSDK(this,"14f6eb5d87954", "1d4ec79b64b6f02275d0509b6123729c");
        SMSSDK.registerEventHandler(new EventHandler(){
            @Override
            public void afterEvent(int event, int i1, Object o) {
                if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    Toast.makeText(Reg_login.this,"短信验证码请求成功",Toast.LENGTH_SHORT).show();
                }
            }
        });*/



    }

    @Override
    public void onClick(View v) {

//SMSSDK.getVerificationCode("中国", editText.getText().toString());

    }
}

