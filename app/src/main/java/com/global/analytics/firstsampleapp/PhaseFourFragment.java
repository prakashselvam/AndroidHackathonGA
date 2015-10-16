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
 * Created by Prakash on 16/10/15.
 */
public class PhaseFourFragment extends Fragment {

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

        View v = inflater.inflate(R.layout.content_phase_four, container,false);

        NiceSpinner IncomeSource = (NiceSpinner) v.findViewById(R.id.IncomeSource);
        List<String> dataset = new LinkedList<>(Arrays.asList("Primary Source of Income",
                "Full time employment", "Part time employment", "Self-employed", "Unemployed"
                ,"Temporary employment"));
        IncomeSource.attachDataSource(dataset);

        NiceSpinner EmploymentLength = (NiceSpinner) v.findViewById(R.id.EmploymentLength);
        List<String> dataset1 = new LinkedList<>(Arrays.asList("Employment Length","1 to 3 Months",
                "4 to 6 Months", "7 to 12 Months", "1 Year to 2 Years","3 Year to 6 Years"));
        EmploymentLength.attachDataSource(dataset1);

        NiceSpinner HowOften = (NiceSpinner) v.findViewById(R.id.HowOften);
        List<String> dataset3 = new LinkedList<>(Arrays.asList("How Often Do You Get Paid?","Monthly",
                "Weekly", "Twice in a Month","Every two weeks"));
        HowOften.attachDataSource(dataset3);


        if (mCurrentPage == 1) {

        }else if (mCurrentPage == 2) {

        }else if (mCurrentPage == 3) {

        }
        return v;
    }

}