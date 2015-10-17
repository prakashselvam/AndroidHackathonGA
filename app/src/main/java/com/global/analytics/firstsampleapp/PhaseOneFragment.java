package com.global.analytics.firstsampleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
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

import org.w3c.dom.Text;

/**
 * Created by Prakash on 16/10/15.
 */
public class PhaseOneFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    int mCurrentPage;
    SeekBar mybar;
    TextView textboxloanamount;
    Animation animFadeFast;
    ImageView imageMoneyRepresentation;
    int currentimg = R.drawable.zero_plus;
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
        animFadeFast = AnimationUtils.loadAnimation(this.getContext(),R.anim.fade_in_fast);
        Animation enterAnimation = AnimationUtils.loadAnimation(this.getContext(),R.anim.activity_move);
        imageMoneyRepresentation = (ImageView) v.findViewById(R.id.imageMoneyRepresentation);
        imageMoneyRepresentation.setAnimation(animFadeFast);
        mybar.setOnSeekBarChangeListener(this);
        TextView amount=(TextView) v.findViewById(R.id.textboxloanamount);
        amount.setAnimation(enterAnimation);
        return v;
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.v("Seek bar",Integer.toString(progress));
        int start = 100;
        int amount = start + ((progress * 14) / 10) * 10;
        String amountString = Integer.toString(amount);
        textboxloanamount.setText("£ "+amountString);
        if (amount>0&&amount<500){
            //TODO set image here
            if(currentimg != R.drawable.zero_plus) {
                Animation animFadeFast = AnimationUtils.loadAnimation(this.getContext(),R.anim.fade_in_fast);
                imageMoneyRepresentation.setImageResource(R.drawable.zero_plus);
                imageMoneyRepresentation.setAnimation(animFadeFast);
                currentimg = R.drawable.zero_plus;
            }
        }else if (amount>=500&&amount<1000){
            if(currentimg != R.drawable.five_hundred_plus) {
                Animation animFadeFast = AnimationUtils.loadAnimation(this.getContext(),R.anim.fade_in_fast);
                ImageView iv = (ImageView) getActivity().findViewById(R.id.imageMoneyRepresentation);
                imageMoneyRepresentation.setAnimation(animFadeFast);
                iv.setImageResource(R.drawable.five_hundred_plus);
            }
            currentimg = R.drawable.five_hundred_plus;
        }else if(amount>=1000&&amount<1500){
            if(currentimg != R.drawable.thousand_plus) {
                Animation animFadeFast = AnimationUtils.loadAnimation(this.getContext(),R.anim.fade_in_fast);
                ImageView iv = (ImageView) getActivity().findViewById(R.id.imageMoneyRepresentation);
                iv.setImageResource(R.drawable.thousand_plus);
                imageMoneyRepresentation.setAnimation(animFadeFast);
                currentimg = R.drawable.thousand_plus;
            }
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}