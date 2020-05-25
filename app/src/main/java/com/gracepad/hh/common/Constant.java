package com.gracepad.hh.common;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Constant {

    public static int nHeight, nWidth, nAppversionCode;
    public static String strPackageName, strDeviceID, strAppversion, strAppName, strDeviceToken;
    public static String strProvider = "com.gracepad.hh.provider";

    public static void setHeightWidth(Activity act) {
        if (nHeight <= 0 || nWidth <= 0) {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            act.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            nHeight = displaymetrics.heightPixels;
            nWidth = displaymetrics.widthPixels;
        }
    }

    public static void hideKeyboard(Context context, EditText edt) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edt.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        edt.setCursorVisible(true);
        edt.setFocusable(false);
        edt.setFocusableInTouchMode(true);
    }

    public static void showKeyboard(Context context, EditText edt) {
        edt.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edt, InputMethodManager.SHOW_IMPLICIT);
        edt.setCursorVisible(true);
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null)
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {

        }
    }

    public static int dpToPx(Activity act, int dp) {
        DisplayMetrics displayMetrics = act.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(Activity act, int px) {
        DisplayMetrics displayMetrics = act.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static int dipToPx(Activity act, int dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, act.getResources().getDisplayMetrics());
        return px;
    }
}