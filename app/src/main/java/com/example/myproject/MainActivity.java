package com.example.myproject;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private Button btn_logout, btn_profile, btn_set, btn_del;
    private String email, name, phone, address;
    private CheckBox check1, check2, check3, check4, check5, check6;
    private ImageView image1, image2, image3, image4, image5, image6;
    String fruit_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("상품 등록 및 삭제");
        setContentView(R.layout.activity_main);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user!=null? user.getUid():null;

        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_profile = (Button) findViewById(R.id.btn_profile);
        btn_set = (Button) findViewById(R.id.btn_set);
        btn_del = (Button) findViewById(R.id.btn_del);
        check1 = (CheckBox) findViewById(R.id.check1);
        check2 = (CheckBox) findViewById(R.id.check2);
        check3 = (CheckBox) findViewById(R.id.check3);
        check4 = (CheckBox) findViewById(R.id.check4);
        check5 = (CheckBox) findViewById(R.id.check5);
        check6 = (CheckBox) findViewById(R.id.check6);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        image6 = (ImageView) findViewById(R.id.image6);

        if(uid == null){
            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "로그인 상태가 아닙니다.",Toast.LENGTH_SHORT).show();
                }
            });
            btn_set.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "회원가입 후 이용해주세요.",Toast.LENGTH_SHORT).show();
                }
            });
            btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "회원가입 후 이용해주세요.",Toast.LENGTH_SHORT).show();
                }
            });
            btn_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder oDialog = new AlertDialog.Builder(MainActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
                    oDialog.setMessage("회원가입 하시겠습니까?")
                            .setTitle("회원가입 필수!!")
                            .setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(),"취소", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNeutralButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(),"회원가입 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setCancelable(false)
                            .show();
                }
            });


        }
        else {
            mFirebaseAuth = FirebaseAuth.getInstance();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(uid);

            btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(check1.isChecked() && !(image1 == null)){
                        image1.setImageDrawable(null);
                        check1.setText("");
                    }
                    if(check2.isChecked() && !(image1 == null)){
                        image2.setImageDrawable(null);
                        check2.setText("");
                    }
                    if(check3.isChecked() && !(image1 == null)){
                        image3.setImageDrawable(null);
                        check3.setText("");
                    }
                    if(check4.isChecked() && !(image1 == null)){
                        image4.setImageDrawable(null);
                        check4.setText("");
                    }
                    if(check5.isChecked() && !(image1 == null)){
                        image5.setImageDrawable(null);
                        check5.setText("");
                    }
                    if(check6.isChecked() && !(image1 == null)){
                        image6.setImageDrawable(null);
                        check6.setText("");
                    }
                    Toast.makeText(MainActivity.this,"상품을 삭제했습니다.",Toast.LENGTH_SHORT).show();
                }
            });
            btn_set.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BitmapDrawable img1 = (BitmapDrawable) getResources().getDrawable(R.drawable.apple);
                    BitmapDrawable img2 = (BitmapDrawable) getResources().getDrawable(R.drawable.pear);
                    BitmapDrawable img3 = (BitmapDrawable) getResources().getDrawable(R.drawable.orange);
                    BitmapDrawable img4 = (BitmapDrawable) getResources().getDrawable(R.drawable.peach);
                    BitmapDrawable img5 = (BitmapDrawable) getResources().getDrawable(R.drawable.banana);
                    BitmapDrawable img6 = (BitmapDrawable) getResources().getDrawable(R.drawable.persimmon);
                    BitmapDrawable[] arr = {img1, img2, img3, img4 , img5, img6};
                    Random random = new Random();
                    int i = random.nextInt(6);
                    switch (i){
                        case 0:
                            fruit_name = "사과";
                            break;
                        case 1:
                            fruit_name = "배";
                            break;
                        case 2:
                            fruit_name = "오렌지";
                            break;
                        case 3:
                            fruit_name = "복숭아";
                            break;
                        case 4:
                            fruit_name = "바나나";
                            break;
                        case 5:
                            fruit_name = "감";
                            break;
                    }
                    if(check1.isChecked()){
                        image1.setImageDrawable(arr[i]);
                        check1.setText(fruit_name);
                    }
                    if(check2.isChecked()){
                        image2.setImageDrawable(arr[i]);
                        check2.setText(fruit_name);
                    }
                    if(check3.isChecked()){
                        image3.setImageDrawable(arr[i]);
                        check3.setText(fruit_name);
                    }
                    if(check4.isChecked()){
                        image4.setImageDrawable(arr[i]);
                        check4.setText(fruit_name);
                    }
                    if(check5.isChecked()){
                        image5.setImageDrawable(arr[i]);
                        check5.setText(fruit_name);
                    }
                    if(check6.isChecked()){
                        image6.setImageDrawable(arr[i]);
                        check6.setText(fruit_name);
                    }
                    Toast.makeText(MainActivity.this, fruit_name +"을(를) 등록했습니다.",Toast.LENGTH_SHORT).show();
                }
            });
            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFirebaseAuth.signOut();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(MainActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });

            btn_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Users user = dataSnapshot.getValue(Users.class);
                                email = user.getEmail();
                                name = user.getName();
                                phone = user.getPhone();
                                address = user.getAddress();

                                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                intent.putExtra("email", email);
                                intent.putExtra("name", name);
                                intent.putExtra("phone", phone);
                                intent.putExtra("address", address);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e("MainActivity", String.valueOf(error.toException())); // 에러문 출력
                            }
                        });
                    }
            });
        }
    }
}