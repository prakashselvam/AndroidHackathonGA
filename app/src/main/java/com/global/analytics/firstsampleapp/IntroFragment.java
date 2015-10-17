package com.global.analytics.firstsampleapp;

/**
 * Created by Prakash on 15/10/15.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class IntroFragment extends Fragment{

    int mCurrentPage;

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

        Animation animFadein = AnimationUtils.loadAnimation(this.getContext(),R.anim.fade_in);
        View v = inflater.inflate(R.layout.intro_content_activity, container,false);
        TextView tv = (TextView ) v.findViewById(R.id.tv);
        tv.setText("You are viewing the page #" + mCurrentPage + "\n\n" + "Swipe Horizontally left / right");
        ImageView img = (ImageView) v.findViewById(R.id.imageView1);


        if (mCurrentPage == 1) {
            View circle=(View) v.findViewById(R.id.circle_1);
            circle.setBackgroundColor(getResources().getColor(R.color.dark_grey));
            img.setImageResource(R.drawable.splash_1);
            tv.setText("Answer to all your Loan Related troubles with a Single Tap");
        }else if (mCurrentPage == 2) {
            View circle=(View) v.findViewById(R.id.circle_2);
            circle.setBackgroundColor(getResources().getColor(R.color.dark_grey));
            img.setImageResource(R.drawable.splash_2);
            img.startAnimation(animFadein);
            tv.setText("Only a fewminutes to get the money credited !!");
        }else if (mCurrentPage == 3) {
            Animation animSlideUp = AnimationUtils.loadAnimation(this.getContext(),R.anim.slide_up_new);
            View circle = (View) v.findViewById(R.id.circle_3);
            circle.setBackgroundColor(getResources().getColor(R.color.dark_grey));
            final Button btn=(Button) v.findViewById(R.id.start_btn);
            btn.setVisibility(View.VISIBLE);
            btn.startAnimation(animSlideUp);
            img.setImageResource(R.drawable.splash_3);
            tv.setText("Is that all ? Naah, Lneding Stream is a Financial Passport !!");
        }
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.startAnimation(animFadein);
        return v;
    }

}