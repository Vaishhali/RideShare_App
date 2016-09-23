package com.sixs.rideshareapp.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by RASHMI on 19-09-2016.
 */
public class SharedPreference {

    public static void putMobileNumber(Context con, String data)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        prefs.edit().putString("1", data).commit();
    }

    public static String getMobileNumber(Context con, String defaultValue)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        String data = prefs.getString("1", defaultValue);
        return data;
    }

    public static void putUserData(Context con, String data)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        prefs.edit().putString("userData", data).commit();
    }

    public static String getUserData(Context con)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        String data = prefs.getString("userData", "");
        return data;
    }
}
