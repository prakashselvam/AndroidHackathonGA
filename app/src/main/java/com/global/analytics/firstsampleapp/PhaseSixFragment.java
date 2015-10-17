package com.global.analytics.firstsampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Prakash on 17/10/15.
 */
public class PhaseSixFragment  extends Fragment {

    int mCurrentPage;
    View v;
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

        v = inflater.inflate(R.layout.content_phase_six, container,false);
        NiceSpinner Debitcardtype = (NiceSpinner) v.findViewById(R.id.Debitcardtype);
        List<String> dataset5 = new LinkedList<>(Arrays.asList("Debit Card Type","VISA Card",
                "Master Card"));
        Debitcardtype.attachDataSource(dataset5);


        if (mCurrentPage == 1) {

        }else if (mCurrentPage == 2) {

        }else if (mCurrentPage == 3) {

        }
        return v;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        unbindDrawables(v);
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