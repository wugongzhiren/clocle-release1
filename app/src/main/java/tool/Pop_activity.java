package tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.clocle.huxiang.clocle.R;
import com.clocle.huxiang.clocle.Self_Info;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/7/24.
 */
public class Pop_activity extends Activity implements View.OnClickListener,DatePicker.OnDateChangedListener{
    private EditText nickname;
    private Button confirm;
    private Button cancel;
    private TextView man;
    private TextView woman;
    private Intent intent;
    private DatePicker datepicker;
    private EditText phoneedit;
    private Button confirmphone;
    private Button cancelphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent requestintent=getIntent();
       String requestcode= requestintent.getStringExtra("requestcode");
        switch (requestcode){
            case "100":
                setContentView(R.layout.pop_layout1);
                bindviews1();
                break;
            case "101":
                setContentView(R.layout.pop_layout2);
                bindviews2();
                break;
            case "102":
                setContentView(R.layout.pop_layout3);
                bindviews3();
                break;
            case "103":
                setContentView(R.layout.pop_layout4);
                bindviews4();
                break;
            case "104":
                setContentView(R.layout.pop_layout5);
                bindviews5();
                break;
            case "105":
                setContentView(R.layout.pop_layout6);
                bindviews6();
                break;
            case "106":
                setContentView(R.layout.pop_layout7);
                bindviews7();
                break;
            case "107":
                setContentView(R.layout.pop_layout8);
                bindviews8();
                break;
            default:
                break;
        }



    }

    private void bindviews1() {
        nickname= (EditText) findViewById(R.id.nickname);
        confirm= (Button) findViewById(R.id.confirm);
        cancel=(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }
    private void bindviews2() {
         man= (TextView) findViewById(R.id.man);
         woman= (TextView) findViewById(R.id.woman);
        man.setOnClickListener(this);
        woman.setOnClickListener(this);
    }
    private void bindviews3() {
      datepicker= (DatePicker) findViewById(R.id.datepicker);
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        datepicker.init(year,month,day,this);
    }
    private void bindviews4() {
        nickname= (EditText) findViewById(R.id.nickname);
        confirm= (Button) findViewById(R.id.confirm);
        cancel=(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }
    private void bindviews5() {
        nickname= (EditText) findViewById(R.id.nickname);
        confirm= (Button) findViewById(R.id.confirm);
        cancel=(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }    private void bindviews6() {
        nickname= (EditText) findViewById(R.id.nickname);
        confirm= (Button) findViewById(R.id.confirm);
        cancel=(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }    private void bindviews7() {
        nickname= (EditText) findViewById(R.id.nickname);
        confirm= (Button) findViewById(R.id.confirm);
        cancel=(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }
    private void bindviews8() {
        phoneedit= (EditText) findViewById(R.id.phone_edit);
        confirmphone= (Button) findViewById(R.id.confirmphone);
        cancelphone=(Button)findViewById(R.id.cancelphone);
        cancelphone.setOnClickListener(this);
        confirmphone.setOnClickListener(this);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return  true;
    }

    @Override
    public void onClick(View v) {
        intent=getIntent();
        switch (v.getId()){
            case R.id.confirm:
                //首先判断Edittext是不是为空
                if(TextUtils.isEmpty(nickname.getText())){
                    Toast.makeText(this,"不能为空哦",Toast.LENGTH_SHORT);
                    return;
                }
                else{

                    intent.putExtra("nickname",nickname.getText().toString());
                    setResult(100,intent);
                finish();
                    break;
        }
            case R.id.cancel:
                finish();

                break;
            case R.id.man:
                intent.putExtra("sex",man.getText().toString());
                setResult(101,intent);
                finish();
            case R.id.woman:
                intent.putExtra("sex",woman.getText().toString());
                setResult(101,intent);
                finish();

    }
}

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        intent=getIntent();
        intent.putExtra("date",year+"年"+monthOfYear+"月"+dayOfMonth+"日");
        setResult(102,intent);
        finish();
    }
}
