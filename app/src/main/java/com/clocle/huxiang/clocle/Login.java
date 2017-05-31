package com.clocle.huxiang.clocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tool.GetuserInfo;

public class Login extends Activity implements View.OnClickListener {
    private Button loginbutton;
    private TextView username;
    private TextView password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbutton = (Button) findViewById(R.id.login);
        username= (TextView) findViewById(R.id.username);
        password= (TextView) findViewById(R.id.password);
        loginbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        GetuserInfo getuserInfo =new GetuserInfo(this);//获取保存用户登录信息的类
       // Map map=getuserInfo.getUserinfo();
        //map.get("username");
        //username.setText();
       // Iterator iterator=map.entrySet().iterator();
        //while (iterator.hasNext()){
        //    Map.Entry entry=(Map.Entry)iterator.next();
        //    String user_name=entry.get
      //  }
        String name=username.getText().toString();
        if(name==null|name.equals("")){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;

        }
        String psword=password.getText().toString();
        if(psword==null|psword.equals("")){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;

        }
        else {
           // GetuserInfo getuserInfo =new GetuserInfo(this);
            getuserInfo.saveUserinfo(name,psword);
            Intent intent = new Intent(this, MainActivity.class);
            //Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            //intent.setClass(Login.this,MainActivity.class);
            startActivity(intent);
this.finish();
        }
    }
}
