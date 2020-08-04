package com.mind.Seg_app;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mind.Seg_app.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   SegDbHelper db;
    private ImageButton btRegister;
    private TextView tvLogin;
    private EditText mEmail;
    private EditText mPassword;

    private Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmail     = findViewById(R.id.edt_loginEmail);
        tvLogin     = findViewById(R.id.tvLogin);
        mPassword     = findViewById(R.id.Edt_loginPassword);
        btRegister    =findViewById(R.id.btRegister);
        btnlogin  = findViewById(R.id.btn_login);
        btRegister.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        db=new SegDbHelper(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        if (v==btRegister) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(tvLogin, "tvLogin");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(intent, activityOptions.toBundle());
        }else if(v==btnlogin){


            String email= mEmail.getText().toString().trim();
            String password= mPassword.getText().toString().trim();

             boolean res=db.checkUser(email,password);
            if (res == true) {
                Toast.makeText(this, "successfully Logged in .", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);

            }
           else if(email==null||email==""||email.length()<3)
            {
                Toast.makeText(this, "Please Enter Correct Email.", Toast.LENGTH_SHORT).show();
            }
            else if(password==null||password==""||password.length()<1)
            {
                Toast.makeText(this, "Please Enter Strong Password.", Toast.LENGTH_SHORT).show();
            }else if(res==false){
                Toast.makeText(this, "the email or password  Uncorrected.", Toast.LENGTH_SHORT).show();

            }

        }

    }

}
