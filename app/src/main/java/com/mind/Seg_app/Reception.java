package com.mind.Seg_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Reception extends AppCompatActivity {
  private TextView mShowName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);

        mShowName= findViewById(R.id.txt_shownameoremail);
        //
        SharedPreferences shrd_receve=getSharedPreferences("save_name", Context.MODE_PRIVATE);

        String emailString =shrd_receve.getString("email","");

        mShowName.setText(emailString);
    }
}