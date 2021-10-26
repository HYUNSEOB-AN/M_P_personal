package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {
    private EditText et_email;
    private EditText et_pass;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_address;
    private Button btn_register;
    private RadioGroup rg;
    int state = 2;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        et_email = (EditText) findViewById(R.id.et_email);
        rg = (RadioGroup) findViewById(R.id.rg);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_address = (EditText) findViewById(R.id.et_address);
        btn_register = (Button) findViewById(R.id.btn_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign Up");


        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_email.getText().toString().trim();
                String pwd = et_pass.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();

                final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);

                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(id).matches())
                {
                    Toast.makeText(RegisterActivity.this,"이메일 형식이 아닙니다",Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                    return;
                }

                if(!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone ))
                {
                    Toast.makeText(RegisterActivity.this,"올바른 핸드폰 번호가 아닙니다.",Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                    return;
                }
                if(pwd.length()<6)
                {
                    Toast.makeText(RegisterActivity.this,"비밀번호 형식을 지켜주세요.",Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                    return;
                }
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch(i){
                            case R.id.rg_accept:
                                state = 0;
                                break;
                            case R.id.rg_decline:
                                state = 1;
                                break;
                            default:
                                state = 2;
                                break;
                        }
                    }
                });
                if(state == 1){
                    Toast.makeText(RegisterActivity.this,"개인정보 동의를 수락해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(state == 2){
                    Toast.makeText(RegisterActivity.this, "약관을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(id, pwd)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    mDialog.dismiss();

                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    String email = user.getEmail();
                                    String uid = user.getUid();
                                    String name = et_name.getText().toString().trim();
                                    String phone = et_phone.getText().toString().trim();
                                    String address = et_address.getText().toString().trim();

                                    DatabaseReference mDatabase;
                                    mDatabase = FirebaseDatabase.getInstance().getReference();
                                    info info_palab = new info(email, name, phone, address);
                                    mDatabase.child(uid).setValue(info_palab);

                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "이미 가입된 사용자 입니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        });
    }
}