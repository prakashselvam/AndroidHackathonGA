package com.global.analytics.firstsampleapp;


import android.content.Context;
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
public class RequestParser extends AsyncTask {

    private onTaskCompleted listener;
    private Context context;
    private String notification;
    public SharedDataManager sharedDataManager;
    public Boolean JsonParsingNeeded = true;
    private static String staticCookie = null;
    public RequestParser(Context context, onTaskCompleted listener) {
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
    protected String doInBackground(Object[] arg0) {
        try{
            String link = (String)arg0[0];
            String urlParameters = (String)arg0[1];
            Log.v("Request :", urlParameters);
            notification = (String)arg0[2];
            JsonParsingNeeded = (Boolean)arg0[3];
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Remove this try catch before going live

//            try {
//                TrustManager[] trustAllCerts = new TrustManager[]{
//                        new X509TrustManager() {
//                            public X509Certificate[] getAcceptedIssuers() {
//                                X509Certificate[] myTrustedAnchors = new X509Certificate[0];
//                                return myTrustedAnchors;
//                            }
//
//                            @Override
//                            public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                            }
//
//                            @Override
//                            public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                            }
//                        }
//                };
//
//                SSLContext sc = SSLContext.getInstance("TLS");
//                sc.init(null, trustAllCerts, new SecureRandom());
//                conn.setDefaultSSLSocketFactory(sc.getSocketFactory());
//                conn.setDefaultHostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String arg0, SSLSession arg1) {
//                        return true;
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }



            conn = (HttpURLConnection) url.openConnection();
            if(staticCookie!=null && staticCookie.length()>0){
                conn.setRequestProperty("Cookie", staticCookie);
            }
            conn.setReadTimeout(sharedDataManager.REQUEST_TIMEOUT_TIME*1000);
            conn.setConnectTimeout(sharedDataManager.REQUEST_TIMEOUT_TIME * 1000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            conn.setRequestProperty("Content-Language", "en-US");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            //Send request
            DataOutputStream writer = new DataOutputStream(conn.getOutputStream ());
            writer.writeBytes (urlParameters);
            writer.flush ();
            writer.close ();
            // Get response
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (is, "UTF-8") );
            String data = null;
            String webPage = "";
            while ((data = reader.readLine()) != null){
                webPage += data + "\n";
            }
            reader.close();
            String cookie = conn.getHeaderField("set-cookie");
            if(cookie!=null && cookie.length()>0){
                staticCookie = cookie;
            }
            conn.disconnect();
            return webPage;
        }catch(Exception e){
            e.printStackTrace();
            return new String("{'status':'error','message':'Unable to connect to Server'}");
        }

    }
    @Override
    protected void onPostExecute(Object result) {
        String web_response = "";
        try {
            web_response = (String) result;
            Log.v("Raw Response", web_response);
            JSONObject jsonObj = null;
            if (JsonParsingNeeded) {
                try {
                    jsonObj = new JSONObject(web_response);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    if (CheckJsonKey.checkForKey(jsonObj, "status") && CheckJsonKey.checkForKey(jsonObj, "msg")) {
                        String status = jsonObj.getString("status");
                        String msg = jsonObj.getString("msg");
                        if (status.equalsIgnoreCase("error") && (msg.equalsIgnoreCase("Invalid Session.") ||
                                msg.equalsIgnoreCase("Invalid session/required post data not present in the request."))) {
                            notification = "INVALID_SESSION";
                        }
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                listener.onTaskCompleted(jsonObj, notification);
            }
            sharedDataManager = null;
            listener = null;
            context = null;
        }catch (Exception e){
            e.printStackTrace();
            //listener.onTaskCompleted(web_response, notification);
        }
    }
}