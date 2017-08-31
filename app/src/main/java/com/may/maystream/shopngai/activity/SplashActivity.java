package com.may.maystream.shopngai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.controller.ApplicationController;
import com.may.maystream.shopngai.helper.DatabaseHelper;

/**
 * Created by may on 8/18/2017.
 */

public class SplashActivity extends AppCompatActivity{
    private DatabaseHelper databaseHelper = null;
    private Handler handler;
    private Runnable runnable;
    private long delay_time;
    private long time = 3000L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        ApplicationController.setActivity(SplashActivity.this);
        databaseHelper = DatabaseHelper.getHelper(SplashActivity.this);
        handler = new Handler();

        runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    public void onResume() {
        super.onResume();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }
}
