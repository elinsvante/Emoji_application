package com.ad.exam1.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ad.exam1.R;
import com.ad.exam1.adapters.EmojiAdapter;
import com.ad.exam1.data.EmojisRetriever;
import com.ad.exam1.models.Emoji;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EmojiActivity extends AppCompatActivity {

    private List<Emoji> myEmojiList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);

        Intent intent = getIntent();
        if (intent.hasExtra("emojiName") && intent.hasExtra("imagePath")) {
            String emojiName = intent.getStringExtra("emojiName");
            String emojiImagePath = intent.getStringExtra("imagePath");
            if (emojiName != null && emojiImagePath != null) {
                addNewEmoji(emojiName, emojiImagePath);
            }
        }
        else {
            getEmojiList();
        }

        bindViews();
        listView.setAdapter((ArrayAdapter)getAdapter());
    }

    private void addNewEmoji(String emojiName, String imageEmojiPath) {
        Emoji emoji = new Emoji(emojiName, imageEmojiPath);
        getEmojiList();
        myEmojiList.add(emoji);
    }

    private void getEmojiList() {
        EmojisRetriever emojisRetriever = EmojisRetriever.getInstance();
        myEmojiList = new ArrayList<>();
        myEmojiList =  emojisRetriever.getEmojiList(getApplicationContext());
    }

    private void bindViews() {

        listView = findViewById(R.id.listViewEmoji);
    }


    private Adapter getAdapter() {
        return new EmojiAdapter(getApplicationContext(),  myEmojiList);
    }

    public void onClickAddEmoji (View view) {
        Intent intent = new Intent(getApplicationContext(), NewEmojiActivity.class);
        startActivity(intent);
    }
}
