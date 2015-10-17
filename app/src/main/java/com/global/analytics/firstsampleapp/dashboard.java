package com.global.analytics.firstsampleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class dashboard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        TextView user_name=(TextView) findViewById(R.id.user_name);
        TextView user_email=(TextView) findViewById(R.id.user_email);
        Button avatar=(Button) findViewById(R.id.avatar_btn);

        //Fill the Data ----- Prakash************

        String state="0";
        user_name.setText("Sanchit Mehta");
        user_email.setText("sanchit@gmail.com");
        String Amount="$ 25.00";
        String Date="25/09/2015";
        String salutation = "Mr";

        ////************************************


        if(salutation=="Ms" || salutation=="Mrs"){
            avatar.setBackgroundResource(R.drawable.avatar_f);
        }
        if(state=="0"){
            LinearLayout existing_comp =(LinearLayout) findViewById(R.id.existing_application);
            existing_comp.setVisibility(View.GONE);
            LinearLayout new_app =(LinearLayout) findViewById(R.id.new_application);
            existing_comp.setVisibility(View.VISIBLE);
        }else if(state=="1"){
            //pass
            TextView amount=(TextView) findViewById(R.id.cust_amount);
            TextView app_date=(TextView) findViewById(R.id.cust_date);
            amount.setText(Amount);
            app_date.setText(Date);
        }else if(state=="2"){
            ImageView progress =(ImageView) findViewById(R.id.app_progress_img);
            progress.setImageResource(R.drawable.forty);
            TextView amount=(TextView) findViewById(R.id.cust_amount);
            TextView app_date=(TextView) findViewById(R.id.cust_date);
            amount.setText(Amount);
            app_date.setText(Date);
        }else if(state=="3"){
            ImageView progress =(ImageView) findViewById(R.id.app_progress_img);
            progress.setImageResource(R.drawable.sixty);
            TextView amount=(TextView) findViewById(R.id.cust_amount);
            TextView app_date=(TextView) findViewById(R.id.cust_date);
            amount.setText(Amount);
            app_date.setText(Date);
        }else if(state=="4"){
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
}
