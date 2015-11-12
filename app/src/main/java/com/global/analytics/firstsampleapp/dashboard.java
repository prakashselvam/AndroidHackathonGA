package com.global.analytics.firstsampleapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class dashboard extends Activity implements onTaskCompleted {
    SharedDataManager sharedDataManager;
    Button avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sharedDataManager = SharedDataManager.getInstance(this);
        TextView user_name=(TextView) findViewById(R.id.user_name);
        TextView user_email=(TextView) findViewById(R.id.user_email);
        avatar=(Button) findViewById(R.id.avatar_btn);
        String state = "4";
        String Amount="$ 75.00";
        String Date="25/09/2015";
        String salutation = "Mr";
        //Fill the Data ----- Prakash************
        if (sharedDataManager.applicationData.pullSuccess){
            user_name.setText(sharedDataManager.applicationData.first_name+" "
                    +sharedDataManager.applicationData.last_name);
            user_email.setText(sharedDataManager.applicationData.email_id);
            state = sharedDataManager.applicationData.state;
            Amount = sharedDataManager.applicationData.ReqLoanAmt;
            Date="25/09/2015";
            state = sharedDataManager.applicationData.state;
            salutation = sharedDataManager.applicationData.salutation;
        }
        else {
            user_name.setText("Guest User");
            user_email.setText("Guest@guest.com");
            state = "0";
            Amount = "";
            Date="";
            salutation = "Ms";
        }



        ////************************************


        if(salutation=="Ms" || salutation=="Mrs" || salutation=="Miss"){
            avatar.setBackgroundResource(R.drawable.avatar_f);
        }
        else {
            avatar.setBackgroundResource(R.drawable.avatar_m);
        }
        if (sharedDataManager.applicationData.profilePic != null){
            new DrawableFromUri(this.getApplicationContext(), this).execute(
                    sharedDataManager.applicationData.profilePic);
        }
        if(state.equals("0")){
            LinearLayout existing_application_view =(LinearLayout) findViewById(R.id.existing_application);
            existing_application_view.setVisibility(View.GONE);
            LinearLayout  new_application_view = (LinearLayout) findViewById(R.id.new_application);
            new_application_view.setVisibility(View.VISIBLE);
        }else if(state.equals("1")){
            TextView amount=(TextView) findViewById(R.id.cust_amount);
            TextView app_date=(TextView) findViewById(R.id.cust_date);
            amount.setText(Amount);
            app_date.setText(Date);
        }else if(state.equals("2")){
            ImageView progress =(ImageView) findViewById(R.id.app_progress_img);
            progress.setImageResource(R.drawable.forty);
            TextView amount=(TextView) findViewById(R.id.cust_amount);
            TextView app_date=(TextView) findViewById(R.id.cust_date);
            amount.setText(Amount);
            app_date.setText(Date);
        }else if(state.equals("3")){
            ImageView progress =(ImageView) findViewById(R.id.app_progress_img);
            progress.setImageResource(R.drawable.sixty);
            TextView amount=(TextView) findViewById(R.id.cust_amount);
            TextView app_date=(TextView) findViewById(R.id.cust_date);
            amount.setText(Amount);
            app_date.setText(Date);
        }else if(state.equals("4")){
            ImageView progress =(ImageView) findViewById(R.id.app_progress_img);
            progress.setImageResource(R.drawable.eighty);
            TextView amount=(TextView) findViewById(R.id.cust_amount);
            TextView app_date=(TextView) findViewById(R.id.cust_date);
            amount.setText(Amount);
            app_date.setText(Date);
        }


    }
    public void start_application(View v){
        Intent intent = new Intent(this,ApplicationActivity.class);
        startActivity(intent);

    }

    @Override
    public void onTaskCompleted(JSONObject jsonObject, String notification) {

    }

    @Override
    public void onBackPressed() {
        return;
    }
    public void logout (View v){

        finish();
    }
    @Override
    public void onTaskCompleted(Drawable response, String notification) {
        avatar.setBackground(response);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        sharedDataManager = null;
        unbindDrawables(this.getCurrentFocus());
        System.gc();
    }
    private void unbindDrawables(View view)
    {
        try {
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
                }
                ((ViewGroup) view).removeAllViews();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
