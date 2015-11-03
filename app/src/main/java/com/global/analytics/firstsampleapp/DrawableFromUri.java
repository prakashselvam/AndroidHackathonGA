package com.global.analytics.firstsampleapp;


        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.graphics.drawable.DrawableWrapper;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.AsyncTask;
        import android.util.Log;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.DataOutputStream;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

/**
 * Created by Prakash on 04/02/15.
 */
public class DrawableFromUri extends AsyncTask {

    private onTaskCompleted listener;
    private Context context;
    private String notification;
    public SharedDataManager sharedDataManager;
    public Boolean JsonParsingNeeded = true;
    private static String staticCookie = null;
    public DrawableFromUri(Context context, onTaskCompleted listener) {
        this.context = context;
        this.listener = (onTaskCompleted)listener;
        sharedDataManager = SharedDataManager.getInstance(context);
    }


    //check Internet connection.
    private void checkInternetConnection(){
        try {
            ConnectivityManager check = (ConnectivityManager) this.context.
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            if (check != null) {
                NetworkInfo[] info = check.getAllNetworkInfo();
                if (info != null) {
                    Boolean isConnected = false;
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            //Toast.makeText(context, "Internet is connected",
                            //        Toast.LENGTH_SHORT).show();
                            isConnected = true;
                            sharedDataManager.noInternetConnection = false;
                        }
                    }
                    if (!isConnected) {
                        noNetworkConnection();
                    }
                }

            } else {
                noNetworkConnection();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void noNetworkConnection()
    {
        try {
            sharedDataManager.noInternetConnection = true;
            String customMessage = "{'status':'error','message':'No Network Connection'}";
            JSONObject jsonObj = null;
            if (customMessage != null) {
                try {
                    jsonObj = new JSONObject(customMessage);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            listener.onTaskCompleted(jsonObj, "NOINTERNET");
            this.cancel(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void onPreExecute(){
        checkInternetConnection();
    }
    @Override
    protected Drawable doInBackground(Object[] arg0) {
        try{
            String link = (String)arg0[0];
            Bitmap x;

            HttpURLConnection connection = (HttpURLConnection) new URL(link).openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();

            x = BitmapFactory.decodeStream(input);
            return new BitmapDrawable(x);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @Override
    protected void onPostExecute(Object result) {
        Drawable web_response = null;
        try {
            web_response = (Drawable) result;
            listener.onTaskCompleted(web_response, notification);
            listener = null;
            sharedDataManager = null;
            context = null;
        }catch (Exception e){
            e.printStackTrace();
            listener.onTaskCompleted(web_response, notification);
        }
    }
}