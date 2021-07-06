package com.natsa.natsa20_mobile.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private static final String TOKEN = "token";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String KTP = "ktp";
    private static final String NO_HP = "no_hp";
    private static final String PHOTO_URL = "profile_photo_url";


    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setToken(Context context, String token) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public static String getToken(Context context) {
        return getSharedPreference(context).getString(TOKEN, null);
    }

    public static void setId(Context context, String id) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(ID, id);
        editor.apply();
    }

    public static int getId(Context context) {
        return getSharedPreference(context).getInt(ID, 0);
    }

    public static void setName(Context context, String name) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(NAME, name);
        editor.apply();
    }

    public static String getName(Context context) {
        return getSharedPreference(context).getString(NAME, null);
    }

    public static void setEmail(Context context, String email) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public static String getEmail(Context context) {
        return getSharedPreference(context).getString(EMAIL, null);
    }

    public static void setUsername(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(EMAIL, username);
        editor.apply();
    }

    public static String getUsername(Context context) {
        return getSharedPreference(context).getString(USERNAME, null);
    }

    public static void setKtp(Context context, String ktp) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KTP, ktp);
        editor.apply();
    }

    public static String getKtp(Context context) {
        return getSharedPreference(context).getString(KTP, null);
    }

    public static void setNoHp(Context context, String noHp) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(NO_HP, noHp);
        editor.apply();
    }

    public static String getNoHp(Context context) {
        return getSharedPreference(context).getString(NO_HP, null);
    }

    public static void setPhotoUrl(Context context, String photoUrl) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(PHOTO_URL, photoUrl);
        editor.apply();
    }

    public static String getPhotoUrl(Context context) {
        return getSharedPreference(context).getString(PHOTO_URL, null);
    }

    public static void setUser(Context context, String token, int id, String name, String email,
                               String username, String ktp, String noHp, String photoUrl) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(TOKEN, token);
        editor.putInt(ID, id);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(USERNAME, username);
        editor.putString(KTP, ktp);
        editor.putString(NO_HP, noHp);
        editor.putString(PHOTO_URL, photoUrl);
        editor.apply();
    }

    public static void setUser(Context context, int id, String name, String email,
                               String username, String ktp, String noHp, String photoUrl) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(ID, id);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(USERNAME, username);
        editor.putString(KTP, ktp);
        editor.putString(NO_HP, noHp);
        editor.putString(PHOTO_URL, photoUrl);
        editor.apply();
    }

    public static void removeUser(Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(TOKEN);
        editor.remove(ID);
        editor.remove(NAME);
        editor.remove(EMAIL);
        editor.remove(USERNAME);
        editor.remove(KTP);
        editor.remove(PHOTO_URL);
        editor.apply();
    }

    public static Boolean isLogin(Context context) {
        return getToken(context) != null;
    }
}
