package com.ad.exam1.data;

import android.content.Context;

import com.ad.exam1.R;
import com.ad.exam1.models.Emoji;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
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
        Type listOfMyClassObject = new TypeToken<ArrayList<Emoji>>() {}.getType();

        List<Emoji> emojiList = gson.fromJson(jsonString, listOfMyClassObject);

        return(emojiList);

    }

    private String getJsonString(Context context){
        InputStream is = context.getResources().openRawResource(R.raw.emojis);
        String jsonString = new Scanner(is).useDelimiter("\\A").next();

        return jsonString;
    }
}
