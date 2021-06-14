package com.natsa.natsa20_mobile.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private static final String TOKEN = "token";

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setToken(Context context, String token){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }
    public static String getToken(Context context){
        return getSharedPreference(context).getString(TOKEN,null);
    }
}
