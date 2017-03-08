package com.example.jammy.ReserveSystem.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Method;

/**
 *
 * 用sharepreference 保存轻量数据在本地
 * Created by ares on 15/11/18.
 */
public class SPUtil {


    /**
     * 根据bean存数据
     * @param context
     * @param preferencesName
     * @param javaBean
     */
    public static void saveData(Context context, String preferencesName,Object javaBean){

        if (context == null) {
            return;
        }

        SharedPreferences MyPreferences = context.getSharedPreferences(preferencesName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = MyPreferences.edit();
        Method[] methods = javaBean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    //toLowerCase变小写
                    field = field.toLowerCase().charAt(0) + field.substring(1);

                    Object value = method.invoke(javaBean, (Object[]) null);
                    editor.putString(field, null == value ? "" : value.toString());
                }
            } catch (Exception e) {
            }
        }
        editor.commit();
    }

    /**
     * 保存数据
     *
     * @param context
     * @param preferencesName
     * @param key
     * @param value
     */
    public static void saveData(Context context, String preferencesName, String key, Object value) {
        if (context == null) {
            return;
        }
        SharedPreferences MyPreferences = context.getSharedPreferences(preferencesName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = MyPreferences.edit();

        if (value instanceof String)
        {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer)
        {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean)
        {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float)
        {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long)
        {
            editor.putLong(key, (Long) value);
        } else
        {
            editor.putString(key, value.toString());
        }


        editor.commit();
    }


    /**
     * 取出数据
     * @param context
     * @param preferencesName
     * @param key
     * @param defaultObject 默认值
     * @return
     */
    public static Object getData(Context context, String preferencesName, String key,Object defaultObject) {
        if (context == null) {
            return "";
        }
        SharedPreferences sp = context.getSharedPreferences(preferencesName, Activity.MODE_PRIVATE);


        if (defaultObject instanceof String)
        {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer)
        {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean)
        {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float)
        {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long)
        {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;


    }

    /**
     * 清理数据
     *
     * @param context
     * @param preferencesName
     * @return
     */
    public static  void cleartData(Context context, String preferencesName) {
        SharedPreferences preferences = context.getSharedPreferences(preferencesName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }


    /**
     * 移除某个key的值
     * @param context
     * @param preferencesName
     * @param key
     */
    public static void removeDataWithKey(Context context, String preferencesName,String key){
        SharedPreferences preferences = context.getSharedPreferences(preferencesName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();

    }



}
