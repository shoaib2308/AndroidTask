package com.singularity.androidtask.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.sql.Timestamp;

public class Constant {

    public static final String BASE_URL = "https://api.snglty.com/";

    /*
    Get Unix Time
    */
    public static String getUnixTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() / 1000L);
        long unixTime1 = timestamp.getTime();
        String unixTime = Long.toString(unixTime1);
        return unixTime;
    }

    /*
    Check internet conncetion available or not
    */
    public static boolean isConnected(Context context) {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }


}
