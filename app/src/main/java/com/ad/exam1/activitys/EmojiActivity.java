package com.ad.exam1.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ad.exam1.NewEmojiActivity;
import com.ad.exam1.R;
import com.ad.exam1.adapters.EmojiAdapter;
import com.ad.exam1.data.EmojisRetriever;
import com.ad.exam1.models.Emoji;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmojiActivity extends AppCompatActivity {

    private List<Emoji> myEmojiList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);

        EmojisRetriever emojisRetriever = EmojisRetriever.getInstance();
        List<Emoji> emojisList = emojisRetriever.getEmojiList(getApplicationContext());
        myEmojiList = emojisList;

        bindViews();
        listView.setAdapter((ArrayAdapter)getAdapter());
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
