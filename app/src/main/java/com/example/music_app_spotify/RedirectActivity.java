package com.example.music_app_spotify;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class RedirectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);

        Uri data = getIntent().getData();
        if (data != null) {
            // 处理授权回调
            Log.d("RedirectActivity", "Received authorization callback: " + data.toString());
        }
    }

}