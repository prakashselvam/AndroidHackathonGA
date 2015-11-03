package com.global.analytics.firstsampleapp;

import android.graphics.drawable.Drawable;

import org.json.JSONObject;

/**
 * Created by Prakash on 04/02/15.
 */
public interface onTaskCompleted {
    void onTaskCompleted(JSONObject jsonObject, String notification);
    void onTaskCompleted(Drawable response, String notification);
}
