package com.global.analytics.firstsampleapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prakash on 19/02/15.
 */
public class CheckJsonKey {
    public static Boolean checkForKey(JSONObject jsonObject, String key)
    {
        try {
            jsonObject.get(key);
            return true;
        }
        catch (JSONException e){
            return false;
        }
        catch (Exception e){
            return false;
        }
    }
}
