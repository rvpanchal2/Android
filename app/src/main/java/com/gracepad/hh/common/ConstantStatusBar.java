package com.gracepad.hh.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.gracepad.hh.R;

public class ConstantStatusBar {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
//            Drawable background = activity.getResources().getDrawable(R.drawable.grd_statusbar);
            Drawable background = activity.getResources().getDrawable(R.color.theme_color);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
//            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        int height;
        Resources myResources = activity.getResources();
        int idStatusBarHeight = myResources.getIdentifier("status_bar_height", "dimen", "android");
        if (idStatusBarHeight > 0) {
            height = activity.getResources().getDimensionPixelSize(idStatusBarHeight);
        } else {
            height = 0;
        }
        return height;
    }
}
