package com.global.analytics.firstsampleapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Properties;


/**
 * Created by Prakash on 13/10/15.
 */
public class SharedDataManager {
    private static SharedDataManager ourInstance = new SharedDataManager();
    public int REQUEST_TIMEOUT_TIME = 60;
    public Boolean noInternetConnection = false;
    public Properties LoginProperty;
    public Context context;
    public String RemoteUrl = "https://test.anyfunds.co.uk/mock";
    public String Username;
    public static SharedDataManager getInstance(Context refcontext) {
        ourInstance.context = refcontext;
        return ourInstance;
    }

    private SharedDataManager() {
    }
    public boolean checkIfFirstTime(){
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(
                    context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            boolean exists = sharedPref.getBoolean("firstLaunch", false);
            if (exists) return false;
            else {
                editor.putBoolean("firstLaunch", true);
                editor.commit();
                return true;
            }
        }catch (Exception e){e.printStackTrace();}
        return false;
    }


}
