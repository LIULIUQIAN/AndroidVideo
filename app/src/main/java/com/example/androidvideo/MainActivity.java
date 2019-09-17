package com.example.androidvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends AppCompatActivity {


    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    startActivity(new Intent(MainActivity.this,GuideActivity.class));
                    finish();
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("config",MODE_PRIVATE);

        if (sharedPreferences.getBoolean("isFint",true)){
            handler.sendEmptyMessageDelayed(1,2000);
            sharedPreferences.edit().putBoolean("isFint",false).commit();
        }else {
            handler.sendEmptyMessageDelayed(2,2000);
        }
    }
}
