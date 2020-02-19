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
    static final int REQUEST_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);

        getEmojiList();

        bindViews();
        listView.setAdapter((ArrayAdapter)getAdapter());
    }

    private void addNewEmoji(String emojiName, String imageEmojiPath) {
        Emoji emoji = new Emoji(emojiName, imageEmojiPath);
        myEmojiList.add(emoji);
        getEmojiList();
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
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String emojiName = data.getStringExtra("emojiName");
                String emojiImagePath = data.getStringExtra("imagePath");
                addNewEmoji(emojiName, emojiImagePath);
            }
        }
    }
}
