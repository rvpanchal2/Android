package com.gracepad.hh.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ImageConstant {
    public static final int MIN_REQUIRED_SIZE = 200;
    public static final int REQUIRED_PROFILE_SIZE = 800;
    public static final int REQUIRED_SIZE = 1080;
    public static final int REQUIRED_VIDEO_THUMB_SIZE = 512;
    public static final int REQUIRED_QUALITY = 90;

    public static String getCachePath(Activity act, File file) {
        return act.getCacheDir().getAbsolutePath() + "/" + file.getName();
    }

    public static String getVideoThumbName(String strPath) {
        return String.format("%s.jpg", strPath.subSequence(strPath.lastIndexOf("/"), strPath.lastIndexOf(".")));
    }

    public static String getVideoCachePath(Activity act, String strPath) {
        return String.format("%s%s", act.getCacheDir().getAbsolutePath(), getVideoThumbName(strPath));
    }

    public static String saveToInternalStorage(Activity act, Bitmap bitmapImage, String filePath) {
        File directory = new File(getVideoCachePath(act, filePath));
//        MyUtils.Log("directory Exist : " + directory.exists() + " : " + directory.getPath());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(getVideoCachePath(act, filePath));
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        MyUtils.Log("directory Exist after : " + directory.exists() + " : " + directory.getPath());
        return directory.getAbsolutePath();
    }

    public static byte[] getFileDataFromDrawable(File file) {
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bytes;
    }

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
//        String currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static File createVideoFile(Context context) throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "VID_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DCIM);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".mp4",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
//        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);

            bitmap = mediaMetadataRetriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());
        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

    public static void copy(File src, File dst) throws IOException {
        try (InputStream in = new FileInputStream(src)) {
            try (OutputStream out = new FileOutputStream(dst)) {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
        }
    }
}