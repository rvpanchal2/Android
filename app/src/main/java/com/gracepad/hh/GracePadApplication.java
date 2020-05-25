package com.gracepad.hh;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.provider.Settings;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.gracepad.hh.common.AppObserver;
import com.gracepad.hh.common.Constant;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 23/08/17.
 */
public class GracePadApplication extends Application {

    private static GracePadApplication sInstance;
    AppObserver observer;
    private RequestQueue mRequestQueue;

    public static Typeface tazSemiBold, tazLight, tazRegular, tazMedium, tazBold;

    @SuppressLint("HardwareIds")
    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        observer = new AppObserver(getApplicationContext());

        initializeFonts();

        try {
            Constant.strDeviceID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            Constant.strAppversion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            Constant.strPackageName = getPackageName();
            Constant.strAppName = getResources().getString(R.string.app_name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeFonts() {
        tazBold = Typeface.createFromAsset(getAssets(), "fonts/chivo_bold.ttf");
        tazLight = Typeface.createFromAsset(getAssets(), "fonts/chivo_light.ttf");
        tazMedium = Typeface.createFromAsset(getAssets(), "fonts/graphik_medium.otf");
        tazRegular = Typeface.createFromAsset(getAssets(), "fonts/chivo_regular.ttf");
        tazSemiBold = Typeface.createFromAsset(getAssets(), "fonts/graphik_semibold.otf");
    }

    //    TODO : NOTIFICATION MANAGEMENT
    private static boolean activityVisible;

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static int chatId = 0;

    public static void setChatId(int chatId) {
        GracePadApplication.chatId = chatId;
    }

    public static int chatId() {
        return chatId;
    }

    public AppObserver getObserver() {
        return observer;
    }

    public synchronized static GracePadApplication getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, int tag) {
        req.setShouldCache(false);
        req.setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        req.setTag(tag == 0 ? 111 : tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
//        MyUtils.Log("Cancel Request : " + tag);
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /*TODO : HEADERS FOR API CALLING*/
    public static Map<String, String> getHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Deviceuuid", Constant.strDeviceID);
        headers.put("Devicetype", "1");
        headers.put("PackageName", Constant.strPackageName);
//        MyUtils.Log(headers.toString());
        return headers;
    }

    public static Map<String, String> getWithoutKeyHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Deviceuuid", Constant.strDeviceID);
        headers.put("Devicetype", "1");
        headers.put("PackageName", Constant.strPackageName);
//        MyUtils.Log(headers.toString());
        return headers;
    }
}