package com.gracepad.hh.common;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by appbits4 on 26/07/16.
 */
@SuppressLint("SimpleDateFormat")
public class DateConstant {

    public static String strFormatDateTime = "yyyy-MM-dd HH:mm:ss";
    public static String strFormatDate = "yyyy-MM-dd";
    public static String strFormatTime = "HH:mm";
    public static String strFormatFullTime = "HH:mm:ss";
    public static String strFormatTimeAPM = "hh:mm a";
    public static String strFormatDateDisplay = "MMM dd, yyyy";
    public static String strFormatDateTimeDisplay = "MMM dd, yyyy hh:mm a";

    public static SimpleDateFormat formatDateTime = new SimpleDateFormat(strFormatDateTime);
    public static SimpleDateFormat formatDate = new SimpleDateFormat(strFormatDate);
    public static SimpleDateFormat formatTime = new SimpleDateFormat(strFormatTime);
    public static SimpleDateFormat formatFullTime = new SimpleDateFormat(strFormatFullTime);
    public static SimpleDateFormat formatTimeAPM = new SimpleDateFormat(strFormatTimeAPM);

    public static Date getDate() {
        Date date = new Date();
        try {
            date = formatDate.parse(formatDate.format(new Date()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static String getStrDate() {
        return formatDate.format(new Date());
    }

    public static Date getDateTime() {
        Date date = new Date();
        try {
            date = formatDateTime.parse(formatDateTime.format(new Date()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static String getStrDateTime() {
        return formatDateTime.format(new Date());
    }

    public static String getStrTime() {
        Date date = new Date();
        try {
            date = formatTime.parse(formatTime.format(new Date()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return formatTime.format(date);
    }

    public static String getStrFullTime() {
        Date date = new Date();
        try {
            date = formatFullTime.parse(formatFullTime.format(new Date()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return formatFullTime.format(date);
    }

    public static String convertDateToString(Date date, String inputFormat) {
        SimpleDateFormat inputDTFormat = new SimpleDateFormat(inputFormat);
        inputDTFormat.setTimeZone(TimeZone.getDefault());
        inputDTFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String OutPutDate = inputDTFormat.format(date);
        return OutPutDate;
    }

    public static Date convertStringToDate(String date, String inputFormat) {
        SimpleDateFormat inputDTFormat = new SimpleDateFormat(inputFormat);
        inputDTFormat.setTimeZone(TimeZone.getDefault());
        inputDTFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date OutPutDate = inputDTFormat.parse(date);
            return OutPutDate;
        } catch (ParseException e) {
            MyUtils.Log(e.toString());
            return new Date();
        }
    }

    public static String changeDateFormat(String date, String strInput, String strOutput) {
        if (date == null || date.length() <= 0)
            return "";

        SimpleDateFormat inputDTFormat = new SimpleDateFormat(strInput);
        SimpleDateFormat outputDTFormat = new SimpleDateFormat(strOutput);

        inputDTFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        outputDTFormat.setTimeZone(TimeZone.getDefault());
        try {
            return outputDTFormat.format(inputDTFormat.parse(date));
        } catch (ParseException e) {
            MyUtils.Log(e.toString());
            return "";
        }
    }

    public static boolean checkDate(String strDate) {
        try {
            Date date1 = getDateTime();
            Date date2 = formatDateTime.parse(strDate);

//            MyUtils.Log(date1 + " : " + date2);
            if (date1.equals(date2)) {
                return true;
            } else if (date1.after(date2)) {
                return true;
            } else if (date1.before(date2)) {
                return false;
            }
        } catch (ParseException e1) {
            MyUtils.Log(e1.toString());
            return false;
        }
        return false;
    }

    public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime, String strInput) {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9])|:([0-5][0-9])$";
        try {
            String currentTime = getCurrentTime(strInput);
//            MyUtils.Log(initialTime + " : " + finalTime + " : " + currentTime);
//            MyUtils.Log(initialTime.matches(reg) + " : " + finalTime.matches(reg) + " : " + currentTime.matches(reg));
            if (initialTime.matches(reg) && finalTime.matches(reg) && currentTime.matches(reg)) {
                boolean valid = false;
                //Start Time
                SimpleDateFormat formatInTiming = new SimpleDateFormat(strInput);

                Date inTime = formatInTiming.parse(initialTime);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(inTime);

                //Current Time
                Date checkTime = formatInTiming.parse(currentTime);
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(checkTime);

                //End Time
                Date finTime = formatInTiming.parse(finalTime);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(finTime);

                if (finalTime.compareTo(initialTime) < 0) {
                    calendar1.add(Calendar.DATE, 1);
                    calendar2.add(Calendar.DATE, 1);
                    calendar3.add(Calendar.DATE, 1);
                }

                Date actualTime = calendar3.getTime();
                if ((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime()) == 0)
                        && actualTime.before(calendar2.getTime())) {
                    valid = true;
                }
                return valid;
            } else {
                return false;
//            throw new IllegalArgumentException("Not a valid time, expecting HH:MM:SS format");
            }
        } catch (ParseException e) {
            MyUtils.Log("ParseException : " + e.toString());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime, String checkingTime, String strInput) {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9])|:([0-5][0-9])$";
        try {
            if (initialTime.matches(reg) && finalTime.matches(reg) && checkingTime.matches(reg)) {
                boolean valid = false;
                //Start Time
                SimpleDateFormat formatInTiming = new SimpleDateFormat(strInput);

                Date inTime = formatInTiming.parse(initialTime);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(inTime);

                //Current Time
                Date checkTime = formatInTiming.parse(checkingTime);
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(checkTime);

                //End Time
                Date finTime = formatInTiming.parse(finalTime);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(finTime);

                if (finalTime.compareTo(initialTime) < 0) {
                    calendar1.add(Calendar.DATE, 1);
                    calendar2.add(Calendar.DATE, 1);
                    calendar3.add(Calendar.DATE, 1);
                }

                Date actualTime = calendar3.getTime();
                if ((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime()) == 0)
                        && actualTime.before(calendar2.getTime())) {
                    valid = true;
                }
                return valid;
            } else {
                return false;
//            throw new IllegalArgumentException("Not a valid time, expecting HH:MM:SS format");
            }
        } catch (ParseException e) {
            MyUtils.Log("ParseException : " + e.toString());
            e.printStackTrace();
            return false;
        }
    }

    public static String getCurrentTime(String strInput) {
        String date = "00:00:00";

        SimpleDateFormat formatInTiming = new SimpleDateFormat(strInput);

        date = formatInTiming.format(new Date());
        return date;
    }

    public static String getDiff(String strDate) {

        String strTime = null;

//        MyUtils.Log(getDateTime() + " : " + startDate + " : ======?>>>>");

        Date startDate = convertStringToDate(strDate, DateConstant.strFormatDateTime);
        long diffInMs = getDateTime().getTime() - startDate.getTime();

//        MyUtils.Log(diffInMs + " : ======?>>>>" + TimeUnit.MILLISECONDS.toSeconds(diffInMs));

        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs);
        if (diffInSec <= 0) {
            strTime = "1s ago";
        } else if (diffInSec < 60) {
            strTime = String.valueOf(diffInSec) + "s ago";
        } else if (diffInSec >= 60 && diffInSec < (60 * 60)) {
            strTime = String.valueOf(diffInSec / 60) + "m ago";
        } else if (diffInSec >= (60 * 60) && diffInSec < (60 * 60 * 24)) {
            strTime = String.valueOf(diffInSec / (60 * 60)) + "h ago";
        } else if (diffInSec >= (60 * 60 * 24) && diffInSec < (60 * 60 * 24 * 7)) {
            strTime = String.valueOf(diffInSec / (60 * 60 * 24)) + "d ago";
        } else if (diffInSec >= (60 * 60 * 24 * 7) && diffInSec < (60 * 60 * 24 * 365)) { //diffInSec < (60 * 60 * 24 * 30)) {
//            strTime = changeDateFormat(strDate, DateConstant.strFormatDateTime, DateConstant.strFormatDateTimeRestaurantList);
            strTime = String.valueOf(diffInSec / (60 * 60 * 24 * 7)) + "w ago";
        }
        return strTime;
    }
}
