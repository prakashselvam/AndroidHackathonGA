package com.global.analytics.firstsampleapp;

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
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Prakash on 16/10/15.
 */
public class PhaseOneFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    int mCurrentPage;
    SeekBar mybar;
    TextView textboxloanamount;
    ImageView imageMoneyRepresentation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Getting the arguments to the Bundle object */
        Bundle data = getArguments();

        /** Getting integer data of the key current_page from the bundle */
        mCurrentPage = data.getInt("current_page", 0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.content_phase_one, container,false);
        mybar = (SeekBar) v.findViewById(R.id.seekBar1);
        textboxloanamount = (TextView) v.findViewById(R.id.textboxloanamount);
        imageMoneyRepresentation = (ImageView) v.findViewById(R.id.imageMoneyRepresentation);
        mybar.setOnSeekBarChangeListener(this);
        return v;
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.v("Seek bar",Integer.toString(progress));
        int start = 100;
        int amount = start + ((progress * 14) / 10) * 10;
        String amountString = Integer.toString(amount);
        textboxloanamount.setText("£ "+amountString);
        if (amount>300){
            //TODO set image here
            imageMoneyRepresentation.setImageResource(R.drawable.howmuch);
        }else if (amount>600){

        }else if (amount>900){

        }else if(amount>1200) {

        }else if(amount>1400){

        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}