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
    SharedDataManager sharedDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sharedDataManager = SharedDataManager.getInstance(this);
        TextView user_name=(TextView) findViewById(R.id.user_name);
        TextView user_email=(TextView) findViewById(R.id.user_email);
        Button avatar=(Button) findViewById(R.id.avatar_btn);
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
}
