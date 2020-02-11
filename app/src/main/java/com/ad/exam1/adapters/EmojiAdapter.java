package com.ad.exam1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ad.exam1.R;
import com.ad.exam1.models.Emoji;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EmojiAdapter extends ArrayAdapter<List> {

    private final Context context;
    private List<Emoji> emojiList;
    private int i;

    public EmojiAdapter (Context appContext, List emojis) {
        super(appContext, -1, emojis);
        context = appContext;
        this.emojiList = emojis;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView nameEmoji = rowView.findViewById(R.id.textViewEmojiName);
        ImageView imageEmoji = rowView.findViewById(R.id.imageViewEmoji);

        Emoji emoji = emojiList.get(position);
        nameEmoji.setText(emoji.getName());
        loadPhotoFromInternet(emoji.getUrl(), imageEmoji);
        return rowView;
    }

    private void loadPhotoFromInternet(String url, ImageView imageEmoji) {
        Picasso.get()
                .load(url)
                .into(imageEmoji);
    }
}
