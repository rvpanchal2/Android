package com.gracepad.hh.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    // TODO : WEEKLY GOAL DIALOG PREF.
    public static SharedPreferences general(Activity act) {
        return act.getSharedPreferences("general", Context.MODE_PRIVATE);
    }

    public static void saveIsLocationPicked(Activity act, boolean weeklyGoal) {
        SharedPreferences sharedPreferences = general(act);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLocationPicked", weeklyGoal);
        editor.apply();
    }

    public static boolean getIsLocationPicked(Activity act) {
        SharedPreferences sharedPreferences = general(act);
        return sharedPreferences.getBoolean("isLocationPicked", false);
    }
}
