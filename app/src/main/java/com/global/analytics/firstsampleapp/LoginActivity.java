package com.global.analytics.firstsampleapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Properties;

public class LoginActivity extends AppCompatActivity implements onTaskCompleted{

    private EditText txf1,txf2;
    private SharedDataManager sharedDataManager;
    private loading_indicator lindicator;
    private AlertDialog alertDialog;
    public AssetsPropertyReader assetsPropertyReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        assetsPropertyReader = new AssetsPropertyReader(this);
        sharedDataManager = SharedDataManager.getInstance();
        txf1 = (EditText)findViewById(R.id.editText);
        txf2 = (EditText)findViewById(R.id.editText2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    public void onClickButton (View view){
        try {
            String userName = txf1.getText().toString().trim();
            String passWord = txf2.getText().toString();
            sharedDataManager.Username = userName;
            if (userName.length() > 0 && passWord.length() > 0) {
                lindicator = new loading_indicator();
                lindicator.showIndicator(LoginActivity.this);
                String url = sharedDataManager.RemoteUrl + "/authenticate/";
                String postData = "";
                try {
                    postData = "username=" + URLEncoder.encode(userName, "UTF-8") + "&password=" + URLEncoder.encode(passWord, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                    postData = "username=" + userName + "&password=" + passWord;
                }
                Log.v("Post Data", postData);
                String notificationName = "LoginCall";
                new RequestParser(this.getApplicationContext(), this).execute(url, postData, notificationName, true);
            } else {
                lindicator.hideIndicator();
                showAlertMessage("Email and password cannot be empty.", "Enter Credentials");
            }
        }catch (Exception e){
            e.printStackTrace();
            if (lindicator!=null) lindicator.hideIndicator();
            showAlertMessage("Unable to process login, Please try again later.", "Error");
        }
    }
    private void loginCheck(JSONObject jsonObject) {
        try {
            String result = jsonObject.getString("status");
            if (result.equals("success")) {
                sharedDataManager.LoginProperty = new Properties();
                sharedDataManager.LoginProperty.setProperty("Status", "Success");
                assetsPropertyReader.writePropertiesToDocs("ViewConfig.properties", sharedDataManager.LoginProperty);
                Intent intent = new Intent(this, MainActivity.class).putExtra("username","Prakash");
                startActivity(intent);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            txf2.setText("Settings clicked");
            return true;
        }
        else if (id == R.id.action_settings1) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
    private void showAlertMessage(String msg, String title) {
        try {
            alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(msg);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new alertDialogOnClickListener());
            //alertDialog.setIcon(R.drawable.icon);
            alertDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
class alertDialogOnClickListener implements AlertDialog.OnClickListener {
    public void onClick(DialogInterface dialog, int which) {
        Log.v("First", String.valueOf(which));
        switch(which){
            case DialogInterface.BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                dialog.dismiss();
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                dialog.dismiss();
                break;
        }
    }
}
    @Override
    public void onTaskCompleted(JSONObject jsonObject, String notification) {
        lindicator.hideIndicator();
        switch (notification)
        {
            case "LoginCall":
            {
                loginCheck(jsonObject);
                break;
            }
            case "NOINTERNET":
            {
                //Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show();
                showAlertMessage("No Internet Connection.","Please Check");
                break;
            }
            case "INVALID_SESSION":
            {
                showAlertMessage("Unable to connect to server. Please try after sometimes",
                        "Login Failed");
                break;
            }
            default:
            {
                showAlertMessage("Unable to connect to server. Please try after sometimes",
                        "Login Failed");
                break;
            }

        }
    }

    @Override
    public void onTaskCompleted(String response, String notification) {
        return;
    }
}
