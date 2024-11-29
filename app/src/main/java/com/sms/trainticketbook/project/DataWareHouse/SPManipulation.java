package com.sms.trainticketbook.project.DataWareHouse;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Guanzhu Li on 1/2/2017.
 */
// name +  email + mobile + pwd;
// design it as singleton
public class SPManipulation {
    public static final String PREFS_NAME = "USER";
    public static final String PREFS_USER_KEY = "USER_INFOR";
    public static final String PREFS_USER_ISLOGIN = "isLogin";
    public static final String PREFS_USER_USERID = "USERID";


    Context mContext;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    private static SPManipulation mInstance = null;

    // constructor
    private SPManipulation(Context context) {
        this.mContext = context;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1

    }

    public static SPManipulation getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SPManipulation(context);
        }
        return mInstance;
    }

    // using shared preference to store the user mobile and user name
    public void save(Context context, String text) {
        editor = settings.edit(); //2
        editor.putString(PREFS_USER_KEY, text); //3
        editor.commit(); //4
    }

    public void saveIsLogin(boolean text) {
        editor = settings.edit(); //2
        editor.putBoolean(PREFS_USER_ISLOGIN, text); //3
        editor.commit(); //4
    }

    public void save(Context context, String text, String prefs_key) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(prefs_key, text); //3
        editor.commit(); //4
    }



    public boolean getIsUserlogin() {
        return settings.getBoolean(PREFS_USER_ISLOGIN, false);
    }

    public void saveUSERId(String text) {
        editor = settings.edit(); //2
        editor.putString(PREFS_USER_USERID, text); //3
        editor.commit(); //4
    }

    public String getUSERID() {
        return settings.getString(PREFS_USER_USERID, "NA");
    }

    public void clearSharedPreference(Context context) {
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context, String prefs_key) {
        editor = settings.edit();
        editor.remove(prefs_key);
        editor.commit();
    }
}
