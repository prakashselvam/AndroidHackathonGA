package com.global.analytics.firstsampleapp;

import java.util.Properties;

/**
 * Created by Prakash on 13/10/15.
 */
public class SharedDataManager {
    private static SharedDataManager ourInstance = new SharedDataManager();
    public int REQUEST_TIMEOUT_TIME = 60;
    public Boolean noInternetConnection = false;
    public Properties LoginProperty;
    public String RemoteUrl = "https://test.anyfunds.co.uk/mock";
    public String Username;
    public static SharedDataManager getInstance() {
        return ourInstance;
    }

    private SharedDataManager() {
    }
}
