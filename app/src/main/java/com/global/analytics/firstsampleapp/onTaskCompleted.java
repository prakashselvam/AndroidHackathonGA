package com.global.analytics.firstsampleapp;

import org.json.JSONObject;

/**
 * Created by Prakash on 04/02/15.
 */
public interface onTaskCompleted {
    void onTaskCompleted(JSONObject jsonObject, String notification);
    void onTaskCompleted(String response, String notification);
}
