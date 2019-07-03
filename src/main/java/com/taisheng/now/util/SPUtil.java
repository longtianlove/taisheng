package com.taisheng.now.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.taisheng.now.SampleAppLike;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by long on 2016/4/18.
 */
public class SPUtil {


    //是否进入主页
    public static final String HOME_PAGE = "home";
    public static final String APP_VERSION = "app_version";
    //uid
    public static final String UID = "uid";


    public static String getUid() {
        return getString(UID);
    }

    public static void putUid(String value) {
        putString(UID, value);
    }



    public static String getAPP_VERSION() {
        return getString(APP_VERSION, 0 + "");
    }

    public static void putAPP_VERSION(String value) {
        putString(APP_VERSION, value);
    }


    /**
     * 获取
     *
     * @return
     */
    public static boolean getHomePage() {
        return getBoolean(HOME_PAGE);
    }

    /**
     * 保存
     *
     * @return
     */
    public static void putHome(boolean isHome) {
        putBoolean(HOME_PAGE, isHome);
    }






    private static SharedPreferences getSP() {
        return SampleAppLike.mcontext.getSharedPreferences("taisheng_sp", Context.MODE_PRIVATE);
    }

    private static void putBoolean(String key, boolean value) {
        getSP().edit().putBoolean(key, value).commit();
    }

    private static void putInt(String key, int value) {
        getSP().edit().putInt(key, value).commit();
    }

    private static void putFloat(String key, float value) {
        getSP().edit().putFloat(key, value).commit();
    }

    private static void putLong(String key, long value) {
        getSP().edit().putLong(key, value).commit();
    }

    private static void putString(String key, String value) {
        getSP().edit().putString(key, value).commit();
    }

    private static void putStringSet(String key, Set<String> value) {
        getSP().edit().putStringSet(key, value).commit();
    }

    private static boolean getBoolean(String key, boolean defaultValue) {
        return getSP().getBoolean(key, defaultValue);
    }

    private static boolean getBoolean(String key) {
        return getSP().getBoolean(key, false);
    }

    private static int getInt(String key, int defaultValue) {
        return getSP().getInt(key, defaultValue);
    }

    private static int getInt(String key) {
        return getSP().getInt(key, 0);
    }

    private static float getFloat(String key, float defaultValue) {
        return getSP().getFloat(key, defaultValue);
    }

    private static float getFloat(String key) {
        return getSP().getFloat(key, 0);
    }

    private static long getLong(String key, long defaultValue) {
        return getSP().getLong(key, defaultValue);
    }

    private static long getLong(String key) {
        return getSP().getLong(key, -1);
    }

    private static String getString(String key, String defaultValue) {
        return getSP().getString(key, defaultValue);
    }

    private static String getString(String key) {
        return getSP().getString(key, "");
    }

    private static Set<String> getStringSet(String key, Set<String> defaultValue) {
        return getSP().getStringSet(key, defaultValue);
    }

    private static Set<String> getStringSet(String key) {
        return getSP().getStringSet(key, null);
    }

    private static Serializable getSerializable(String key) {
        Serializable serializable = null;
        try {
            SharedPreferences sharedPreferences = getSP();
            String serializableString = sharedPreferences.getString(key, "");
            byte[] mobileBytes = Base64.decode(serializableString.getBytes(), Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            try {
                serializable = (Serializable) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serializable;
    }

    private static void putSerializable(String key, Serializable value) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(value);
            SharedPreferences sharedPreferences = getSP();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String mobilesString = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            editor.putString(key, mobilesString);
            editor.commit();
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}