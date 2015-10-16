package com.global.analytics.firstsampleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Prakash on 16/10/15.
 */
public class PhaseThreeFragment extends Fragment {

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

        View v = inflater.inflate(R.layout.content_phase_three, container,false);

        NiceSpinner county = (NiceSpinner) v.findViewById(R.id.county);
        List<String> dataset = new LinkedList<>(Arrays.asList("County", "Angus", "Berkshire", "Bristol", "Cheshire"));
        county.attachDataSource(dataset);

        NiceSpinner timeataddress = (NiceSpinner) v.findViewById(R.id.timeataddress);
        List<String> dataset1 = new LinkedList<>(Arrays.asList("Time at Address","Single", "Married",
                "Living Together", "Divorced","Seperated","Widowed"));
        timeataddress.attachDataSource(dataset1);

        NiceSpinner typeofocupancy = (NiceSpinner) v.findViewById(R.id.typeofocupancy);
        List<String> dataset2 = new LinkedList<>(Arrays.asList("Type of Ocupancy","Owned","Rental",
                "Living with parents","Other"));
        typeofocupancy.attachDataSource(dataset2);




        if (mCurrentPage == 1) {

        }else if (mCurrentPage == 2) {

        }else if (mCurrentPage == 3) {

        }
        return v;
    }

}
