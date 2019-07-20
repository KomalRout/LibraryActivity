package com.example.libraryactivity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        new Handler().postDelayed(new Runnable() {
            ProgressBar progressBar = findViewById(R.id.progress);
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,BookListActivity.class));
                finish();
            }
        }, 3000);
    }
}
