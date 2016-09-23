package com.sixs.rideshareapp.Util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by RASHMI on 15-09-2016.
 */
public class Utils {

    //URLs to register.php and confirm.php file
    public static final String API_URL = "http://52.44.119.128/rideshareapi";
    public static final String MOBILE_NUMBER_REGISTER_URL = "/rideshareregistration/approveuser";
    public static final String OTP_REGISTER_URL = "/rideshareregistration";
    public static final String CONFIRM_URL = "http://simplifiedcoding.16mb.com/AndroidOTP/confirm.php";

    public static final String TAG_RESPONSE = "success";

    public static final int EDIT_TRIP = 100;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void killApplication(Activity activity) {
        activity.finish();
        System.exit(0);
    }
}
