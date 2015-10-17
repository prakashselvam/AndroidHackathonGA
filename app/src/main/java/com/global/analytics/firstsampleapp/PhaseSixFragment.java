package com.global.analytics.firstsampleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Prakash on 17/10/15.
 */
public class PhaseSixFragment  extends Fragment {

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

        View v = inflater.inflate(R.layout.content_phase_six, container,false);


        NiceSpinner Debitcardtype  = (NiceSpinner) v.findViewById(R.id.Debitcardtype);
        List<String> dataset = new LinkedList<>(Arrays.asList("Debit card type",
                "VISA Debit", "MASTER Debit"));
        Debitcardtype.attachDataSource(dataset);



        if (mCurrentPage == 1) {

        }else if (mCurrentPage == 2) {

        }else if (mCurrentPage == 3) {

        }
        return v;
    }

}