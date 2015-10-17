package com.global.analytics.firstsampleapp;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Prakash on 16/10/15.
 */
public class PhaseTwoFragment extends Fragment {
    View v;
    int mCurrentPage;
    SharedDataManager sharedDataManager;
    EditText FirstName,LastName, emailid, mobileNumber;
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

        Animation animFadein = AnimationUtils.loadAnimation(this.getContext(), R.anim.fade_in);
        v = inflater.inflate(R.layout.content_phase_two, container,false);
        FirstName = (EditText)v.findViewById(R.id.FirstName);
        LastName = (EditText)v.findViewById(R.id.LastName);
        emailid = (EditText)v.findViewById(R.id.emailid);
        mobileNumber  = (EditText)v.findViewById(R.id.mobileNumber);
        sharedDataManager = SharedDataManager.getInstance(this.getActivity());
        NiceSpinner title = (NiceSpinner) v.findViewById(R.id.Title);
        List<String> dataset = new LinkedList<>(Arrays.asList("Salutation--","Mr", "Miss", "Mrs", "Ms"));
        title.attachDataSource(dataset);
        title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sharedDataManager.applicationData.salutationindex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        NiceSpinner MaritalStatus = (NiceSpinner) v.findViewById(R.id.MaritalStatus);
        List<String> dataset1 = new LinkedList<>(Arrays.asList("Marital Status--","Single", "Married",
                "Living Together", "Divorced","Seperated","Widowed"));
        MaritalStatus.attachDataSource(dataset1);
        MaritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sharedDataManager.applicationData.marital_statusindex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        NiceSpinner birthDate = (NiceSpinner) v.findViewById(R.id.birthDate);
        List<String> dataset2 = new LinkedList<>(Arrays.asList("DD","0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"));
        birthDate.attachDataSource(dataset2);
        birthDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sharedDataManager.applicationData.dob_dayindex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        NiceSpinner birthMonth = (NiceSpinner) v.findViewById(R.id.birthMonth);
        List<String> dataset3 = new LinkedList<>(Arrays.asList("MM","Jan", "Feb","Mar","Apr","May",
                "Jun","Jul","Aug","Sep","Oct","Nov","Dec"));
        birthMonth.attachDataSource(dataset3);
        birthMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sharedDataManager.applicationData.dob_monthindex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        NiceSpinner birthYear = (NiceSpinner) v.findViewById(R.id.birthYear);
        List<String> dataset4 = new LinkedList<>(Arrays.asList("YYYY","1950", "1951", "1952",
                "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962",
                "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972",
                "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982",
                "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992",
                "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002",
                "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012",
                "2013", "2014"));
        birthYear.attachDataSource(dataset4);
        birthYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sharedDataManager.applicationData.dob_yearindex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (sharedDataManager.applicationData.pullSuccess){
            title.setSelectedIndex(sharedDataManager.applicationData.salutationindex);
            MaritalStatus.setSelectedIndex(sharedDataManager.applicationData.marital_statusindex);
            birthDate.setSelectedIndex(sharedDataManager.applicationData.dob_dayindex);
            birthMonth.setSelectedIndex(sharedDataManager.applicationData.dob_monthindex);
            birthYear.setSelectedIndex(sharedDataManager.applicationData.dob_yearindex);
            FirstName.setText(sharedDataManager.applicationData.first_name);
            LastName.setText(sharedDataManager.applicationData.last_name);
            emailid.setText(sharedDataManager.applicationData.email_id);
            mobileNumber.setText(sharedDataManager.applicationData.mobile_no);
        }
        Button next = (Button)v.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedDataManager.applicationData.first_name = FirstName.getText().toString();
                sharedDataManager.applicationData.last_name = LastName.getText().toString();
                sharedDataManager.applicationData.email_id = emailid.getText().toString();
                sharedDataManager.applicationData.mobile_no = mobileNumber.getText().toString();
                sharedDataManager.applicationData.state = "1";
                MyClass obj = new MyClass(getActivity());
                obj.saveObject(sharedDataManager.applicationData);
                Animation animPushUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left);
                sharedDataManager.pager.setAnimation(animPushUp);
                sharedDataManager.pager.setCurrentItem(2, true);
            }
        });
        if (mCurrentPage == 1) {

        }else if (mCurrentPage == 2) {

        }else if (mCurrentPage == 3) {

        }
        return v;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        sharedDataManager = null;
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