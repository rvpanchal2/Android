package com.gracepad.hh.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.gracepad.hh.object.ProfileObject;

public class StoreUserData {

    private static final String PREFS_NAME = "CurrentUserApp";
    private static final String FAVORITES = "User";

    public StoreUserData() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveUser(Context context, ProfileObject favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES))
            removeUser(context);

        editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);
        editor.putString(FAVORITES, jsonFavorites);
        editor.apply();
    }

    public void removeUser(Context context) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(FAVORITES);
        editor.apply();
    }

    public ProfileObject getUsers(Context context) {
        SharedPreferences settings;
        ProfileObject favorites = new ProfileObject();

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            favorites = gson.fromJson(jsonFavorites, ProfileObject.class);
        }

        return favorites;
    }
}
