package com.global.analytics.firstsampleapp;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Prakash on 17/10/15.
 */
public class PhaseSevenFragment  extends Fragment implements SeekBar.OnSeekBarChangeListener {

    int mCurrentPage;
    SeekBar mybar;
    TextView finalloanamount;
    View v;
    private loading_indicator lindicator;
    private SharedDataManager sharedDataManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Getting the arguments to the Bundle object */
        Bundle data = getArguments();
        /** Getting integer data of the key current_page from the bundle */
        mCurrentPage = data.getInt("current_page", 0);
        sharedDataManager = SharedDataManager.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.content_phase_seven, container, false);



        if (mCurrentPage == 1) {

        }else if (mCurrentPage == 2) {

        }else if (mCurrentPage == 3) {

        }
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        mybar = (SeekBar) v.findViewById(R.id.seekBar2);
        finalloanamount = (TextView) v.findViewById(R.id.finalloanamount);
        Animation animFadeIn = AnimationUtils.loadAnimation(this.getContext(),R.anim.fade_in_fast);
        String status="success";
        if(status.equals("success")){
            Animation animPushUp = AnimationUtils.loadAnimation(this.getContext(),R.anim.push_up);
            ImageView success = (ImageView) v.findViewById(R.id.approved_img);
            LinearLayout success_layout = (LinearLayout) v.findViewById(R.id.success_layout);
            success_layout.setAnimation(animFadeIn);
            success.setAnimation(animFadeIn);
            finalloanamount.setAnimation(animPushUp);
            mybar.setAnimation(animFadeIn);
            mybar.setOnSeekBarChangeListener(this);

        }else{
            LinearLayout success_layout_main = (LinearLayout) v.findViewById(R.id.success_layout_main);
            success_layout_main.setVisibility(View.GONE);
            Button reject_btn =(Button) v.findViewById(R.id.btn_rejected);
            ImageView reject_img = (ImageView) v.findViewById(R.id.reject_img);
            reject_btn.setVisibility(View.VISIBLE);
            reject_img.setVisibility(View.VISIBLE);
            reject_img.setAnimation(animFadeIn);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.v("Seek bar", Integer.toString(progress));
        int start = 100;
        int amount = start + ((progress * 14) / 10) * 10;
        System.out.println(finalloanamount);
        String amountString = Integer.toString(amount);
        finalloanamount.setText("£ "+amountString);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}