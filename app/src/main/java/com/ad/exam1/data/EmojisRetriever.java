package com.ad.exam1.data;

import android.content.Context;

import com.ad.exam1.R;
import com.ad.exam1.models.Emoji;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmojisRetriever {

    private static EmojisRetriever instance;

    /**
     * Private constructor, use: EmojisRetriever.getInstance() to retrieve an instance reference.
     */
    private EmojisRetriever(){}

    /**
     * Singleton creation pattern.
     * This allow access to this class in all other classes with out the need of
     * creating a separate instance.
     *
     * You get your instance bu calling: EmojisRetriever.getInstance()
     *
     * @return instance of EmojisRetriever
     */
    public static EmojisRetriever getInstance(){
        if(instance == null) {
            instance = new EmojisRetriever();
        }

        return instance;
    }

    /**
     *
     * @param context - application context needed to access application resources
     *
     * @return List containing instances of Emojis.
     */
    public List<Emoji> getEmojiList(Context context) {
        Gson gson = new Gson();

        String jsonString = getJsonString(context);

        //TODO: We need to convert the jsonString to a List<Emoji>
        // we can use Gson for this
        // https://github.com/google/gson
        // and then make the method return it.

        Emoji[] emojiList = gson.fromJson(jsonString, Emoji[].class);

        return(Arrays.asList(emojiList));

    }

    private String getJsonString(Context context){
        InputStream is = context.getResources().openRawResource(R.raw.emojis);
        String jsonString = new Scanner(is).useDelimiter("\\A").next();

        return jsonString;
    }
}
