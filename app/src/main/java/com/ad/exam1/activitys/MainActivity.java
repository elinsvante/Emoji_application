package com.ad.exam1.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ad.exam1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickEmojiActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), EmojiActivity.class);
        startActivity(intent);
    }
}
