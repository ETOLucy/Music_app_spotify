package com.example.music_app_spotify;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View rootView = findViewById(android.R.id.content);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSearchComponentsIfVisible();
            }
        });

        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理按钮点击事件，可以添加播放音乐的逻辑
//                playMusic();
            }
        });

    }

    public void onRectangleClick(View view) {
        EditText searchEditText = findViewById(R.id.searchEditText);
        ImageButton searchButton = findViewById(R.id.searchButton);
        View rectangle_2 = findViewById(R.id.rectangle_2);
        RelativeLayout frame = findViewById(R.id.frame);
        TextView search = findViewById(R.id.search);

        // 如果输入框不可见，点击搜索按钮后显示输入框和搜索按钮
        if (searchEditText.getVisibility() == View.INVISIBLE) {
            searchEditText.setVisibility(View.VISIBLE);
            searchButton.setVisibility(View.VISIBLE);
            rectangle_2.setVisibility(View.INVISIBLE);
            frame.setVisibility(View.INVISIBLE);
            search.setVisibility(View.INVISIBLE);

            // 显示输入法键盘
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        } else {
            // 如果输入框可见，隐藏输入框和搜索按钮
            searchEditText.setVisibility(View.INVISIBLE);
            searchButton.setVisibility(View.INVISIBLE);
            rectangle_2.setVisibility(View.VISIBLE);
            frame.setVisibility(View.VISIBLE);
            search.setVisibility(View.VISIBLE);

            // 隐藏输入法键盘
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
            }
        }
    }


    private void hideSearchComponentsIfVisible() {
        EditText searchEditText = findViewById(R.id.searchEditText);
        ImageButton searchButton = findViewById(R.id.searchButton);
        View rectangle_2 = findViewById(R.id.rectangle_2);
        RelativeLayout frame = findViewById(R.id.frame);
        TextView search = findViewById(R.id.search);

        if (searchEditText.getVisibility() == View.VISIBLE) {
            // 如果输入框可见，隐藏输入框和搜索按钮
            searchEditText.setVisibility(View.INVISIBLE);
            searchButton.setVisibility(View.INVISIBLE);
            rectangle_2.setVisibility(View.VISIBLE);
            frame.setVisibility(View.VISIBLE);
            search.setVisibility(View.VISIBLE);
            // 隐藏输入法键盘
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
            }
        }
    }


}
