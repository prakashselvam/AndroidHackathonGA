package com.global.analytics.firstsampleapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class forcardActivity extends Activity implements onTaskCompleted {
    private SharedDataManager sharedDataManager;
    private loading_indicator lindicator;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forcard);
        sharedDataManager = SharedDataManager.getInstance(this);
        //sharedDataManager.pager.setCurrentItem(6, true);
        Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true); // default: false

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    private void checkCard(){
        try {

                lindicator = new loading_indicator();
                lindicator.showIndicator(forcardActivity.this);
                String url = sharedDataManager.RemoteUrl;
                String postData = "";
                try {
                    postData = "<card>"+sharedDataManager.cardnumber+"</card>";
                    //postData = "username=" + URLEncoder.encode(userName, "UTF-8") + "&password=" + URLEncoder.encode(passWord, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                    postData = "<card>"+sharedDataManager.cardnumber+"</card>";
                }
                Log.v("Post Data", postData);
                String notificationName = "LoginCall";
                new RequestParser(this.getApplicationContext(), this).execute(url, postData, notificationName, true);
        }catch (Exception e){
            e.printStackTrace();
            if (lindicator != null) lindicator.hideIndicator();
        }
    }
    private void loginCheck(JSONObject jsonObject) {
        try {
                    sharedDataManager.pager.setCurrentItem(6,true);
                    finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            String resultDisplayStr;
            boolean success1 = false,success2 = false;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";
                sharedDataManager.cardnumber = scanResult.getRedactedCardNumber();
                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );

                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                    success1 = true;
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                    success2 = true;
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }
                if (success1 && success2) {
                    checkCard();
                }
            }
            else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultStr);
        }
        // else handle other activity results
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
    private void showAlertMessage(String msg, String title) {
        try {
            alertDialog = new AlertDialog.Builder(forcardActivity.this).create();
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
            switch (which) {
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
    public void onTaskCompleted(Drawable response, String notification) {

    }
}
