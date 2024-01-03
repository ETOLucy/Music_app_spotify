package com.example.music_app_spotify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Song_play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play);
        // 找到 ImageView 对应的 View 对象
        ImageView imageView = findViewById(R.id.Careleft);

        // 为 ImageView 设置点击事件监听器
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在点击事件中添加返回主页的逻辑
                returnToHomePage();
            }
        });
    }

    // 返回主页的方法
    private void returnToHomePage() {
        // 创建 Intent 实例，用于启动主页的 Activity
        Intent intent = new Intent(this, MainActivity.class);

        // 设置标志位，清除之前的所有 Activity 栈，确保主页成为新的栈顶
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // 启动主页的 Activity
        startActivity(intent);

        // 结束当前的 Activity，以确保返回到主页后，点击返回按钮不会再回到当前页面
        finish();
    }
}