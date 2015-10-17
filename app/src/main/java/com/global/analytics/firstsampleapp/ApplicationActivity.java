package com.global.analytics.firstsampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

/**
 * Created by Prakash on 16/10/15.
 */
public class ApplicationActivity extends AppCompatActivity {
    private View topIcons;
    private int MY_SCAN_REQUEST_CODE = 100;
    private SharedDataManager sharedDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        sharedDataManager = SharedDataManager.getInstance(this);
        /** Getting a reference to the ViewPager defined the layout file */
        sharedDataManager.pager = (ViewPager) findViewById(R.id.pager);
        topIcons = findViewById(R.id.topIcons);
        /** Getting fragment manager */
        FragmentManager fm = getSupportFragmentManager();

        /** Instantiating FragmentPagerAdapter */
        ApplicationFragmentAdapter pagerAdapter = new ApplicationFragmentAdapter(fm);

        /** Setting the pagerAdapter to the pager object */
        sharedDataManager.pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        sharedDataManager.pager.setAdapter(pagerAdapter);
        sharedDataManager.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0) topIcons.setVisibility(View.INVISIBLE);
                else{
                    //topIcons.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void gotoCardActivity(View view){
        sharedDataManager.pager.setCurrentItem(6, true);
//        Intent scanIntent = new Intent(this, CardIOActivity.class);
//
//        // customize these values to suit your needs.
//        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
//        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true); // default: false
//        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
//        scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true); // default: false
//
//        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
//        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            String resultDisplayStr;
            boolean success1 = false,success2 = false,success3 = false;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";

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
                    success3 = true;
                }
                if (success1 && success2 && success3) sharedDataManager.pager.setCurrentItem(6,true);
            }
            else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultStr);
        }
        // else handle other activity results
    }
    public void phaseonego(View view){
        

    }
    public void phasetwogo(View view){
        Animation animPushUp = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        sharedDataManager.pager.setAnimation(animPushUp);
        sharedDataManager.pager.setCurrentItem(2,true);
    }
    public void phasethreego(View view){
        Animation animPushUp = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        sharedDataManager.pager.setAnimation(animPushUp);
        sharedDataManager.pager.setCurrentItem(3,true);
    }
    public void phasefourgo(View view){
        Animation animPushUp = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        sharedDataManager.pager.setAnimation(animPushUp);
        sharedDataManager.pager.setCurrentItem(4,true);
    }
    public void phasefivego(View view){
        Animation animPushUp = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        sharedDataManager.pager.setAnimation(animPushUp);
        sharedDataManager.pager.setCurrentItem(5,true);
    }
    public void phasesevengo(View view){
        Animation animPushUp = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        sharedDataManager.pager.setAnimation(animPushUp);
        sharedDataManager.pager.setCurrentItem(7,true);
    }
    public void phaseeightgonow(View view){
        Animation animPushUp = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        sharedDataManager.pager.setAnimation(animPushUp);
        sharedDataManager.pager.setCurrentItem(8,true); }
    public void phaseninego(View view){
        Animation animPushUp = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        sharedDataManager.pager.setAnimation(animPushUp);
        sharedDataManager.pager.setCurrentItem(9,true);
    }
    public void phasetengo(View view){
        Animation animPushUp = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        sharedDataManager.pager.setAnimation(animPushUp);
        sharedDataManager.pager.setCurrentItem(10,true);
    }
    public void phasesixgo(View view){
        Animation animPushUp = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        sharedDataManager.pager.setAnimation(animPushUp);
        sharedDataManager.pager.setCurrentItem(6,true);
    }
    public void phasesevengohome(View view){
        finish();
    }

}