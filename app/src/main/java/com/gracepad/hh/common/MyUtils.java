package com.gracepad.hh.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.gracepad.hh.R;


public class MyUtils {

    public static ProgressDialog progressDialog;

    public static void Log(Object msg) {
        Log.e("App Log : ====>>>>> ", msg + "");
    }

    public static void showProgress(Activity act, String strMsg) {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                return;

            progressDialog = new ProgressDialog(act);
            progressDialog.setIndeterminate(true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage(strMsg);
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Showing Alert Message
                    try {
                        if (progressDialog != null && !progressDialog.isShowing())
                            progressDialog.show();
                    } catch (WindowManager.BadTokenException e) {
                        MyUtils.Log(e.toString());
                    }
                }
            });
        } catch (RuntimeException e) {
            MyUtils.Log("RuntimeException : " + e.toString());
        }
    }

    public static void dismissProgress() {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
        } catch (IllegalArgumentException e) {

        }
    }

    private static AlertDialog alertDialog;

    public static void showAlertDialog(Activity activity, String strMessage) {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();

        alertDialog = new AlertDialog.Builder(activity).create();

        // Setting Dialog Title
        alertDialog.setTitle(R.string.app_name);

        // Setting Dialog Message
        alertDialog.setMessage(strMessage);

        // Setting OK Button
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
            }
        });

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Showing Alert Message
                try {
                    if (alertDialog != null && !alertDialog.isShowing())
                        alertDialog.show();
                } catch (WindowManager.BadTokenException e) {
                    MyUtils.Log(e.toString());
                } catch (RuntimeException e) {
                    MyUtils.Log(e.toString());
                }
            }
        });
    }

    public static void showAlertDialogWithBack(final Activity activity, String strMessage) {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();

        alertDialog = new AlertDialog.Builder(activity).create();

        // Setting Dialog Title
        alertDialog.setTitle(R.string.app_name);

        // Setting Dialog Message
        alertDialog.setMessage(strMessage);

        // Setting OK Button
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                activity.onBackPressed();
            }
        });

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Showing Alert Message
                try {
                    if (alertDialog != null && !alertDialog.isShowing())
                        alertDialog.show();
                } catch (WindowManager.BadTokenException e) {
                    MyUtils.Log(e.toString());
                } catch (RuntimeException e) {
                    MyUtils.Log(e.toString());
                }
            }
        });
    }

    public static void showMaintenanceDialog(final Activity activity, String strMessage, final int nType, String strButtonText) {
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();

        alertDialog.setCancelable(false);

        // Setting Dialog Title
        alertDialog.setTitle(R.string.app_name);

        // Setting Dialog Message
        alertDialog.setMessage(strMessage);

        // Setting OK Button
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, strButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                if (nType == 1) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                } else if (nType == 2) {
                    final String appPackageName = activity.getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
            }
        });

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Showing Alert Message
                try {
                    if (alertDialog != null && !alertDialog.isShowing())
                        alertDialog.show();
                } catch (WindowManager.BadTokenException e) {
                    MyUtils.Log(e.toString());
                }
            }
        });
    }

    public static void showDoneToastDialog(Activity act, String msg, boolean isDone) {
        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_alert_toast, null);
        TextView txt = v.findViewById(R.id.txtCstmDlg);
        txt.setText(msg);
        ImageView img = v.findViewById(R.id.imgCstmDlg);
        if (isDone)
            img.setImageResource(R.drawable.svdone);
        else
            img.setImageResource(R.drawable.sverror);

        Toast toast = new Toast(act);
        toast.setView(v);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void setText(TextView txt, String msg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txt.setText(Html.fromHtml(msg, Html.FROM_HTML_MODE_LEGACY));
        } else {
            txt.setText(Html.fromHtml(msg));
        }
    }

    public static void setText(EditText txt, String msg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txt.setText(Html.fromHtml(msg, Html.FROM_HTML_MODE_LEGACY));
        } else {
            txt.setText(Html.fromHtml(msg));
        }
    }
}