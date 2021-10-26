package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class ProfileActivity extends AppCompatActivity {

    TextView text_email;
    TextView text_name;
    TextView text_phone;
    TextView text_address;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //타이틀바 없애기
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

//        //UI 객체생성
        text_email = (TextView)findViewById(R.id.text_email);
        text_name = (TextView)findViewById(R.id.text_name);
        text_phone = (TextView)findViewById(R.id.text_phone);
        text_address = (TextView)findViewById(R.id.text_address);
        btn = (Button)findViewById(R.id.btn);

        //데이터 가져오기
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String address = intent.getStringExtra("address");

        text_email.setText("E-mail : " + email);
        text_name.setText("Name : " + name);
        text_phone.setText("Phone Number : " + phone);
        text_address.setText("Address : " + address);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //바깥레이어 클릭시 안닫히게
//        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        //안드로이드 백버튼 막기
//        return;
//    }

}