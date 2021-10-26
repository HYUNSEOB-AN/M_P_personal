package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private Button btn_login;
    private Button btn_register;
    private Button btn_none_login;
    private EditText et_id;
    private EditText et_pass;
    private CheckBox check;
    private Context mContext;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("과일농장");
        mContext = this;

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_none_login = (Button) findViewById(R.id.btn_none_login);
        et_id = (EditText) findViewById(R.id.et_id);
        et_pass = (EditText) findViewById(R.id.et_pass);
        check = (CheckBox) findViewById(R.id.check_box);

        firebaseAuth = firebaseAuth.getInstance();

        boolean boo = PreferenceManager.getBoolean(mContext, "check");
        if(boo){
            et_id.setText(PreferenceManager.getString(mContext, "E-mail"));
            et_pass.setText(PreferenceManager.getString(mContext, "Password"));
            check.setChecked(true);
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_id.getText().toString().trim();
                String pwd = et_pass.getText().toString().trim();

                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this,"이메일을 입력하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"비밀번호를 입력하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this,"로그인 되었습니다.",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "이메일 혹은 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_none_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this,"메인으로 이동합니다.",Toast.LENGTH_SHORT).show();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check.isChecked()){
                    PreferenceManager.setString(mContext, "E-mail", et_id.getText().toString());
                    PreferenceManager.setString(mContext, "Password", et_pass.getText().toString());
                    PreferenceManager.setBoolean(mContext, "check", check.isChecked());

                }
                else{
                    PreferenceManager.setBoolean(mContext, "check", check.isChecked());
                    PreferenceManager.clear(mContext);
                }
            }
        });
    }
}