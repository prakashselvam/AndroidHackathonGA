package com.global.analytics.firstsampleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Prakash on 16/10/15.
 */
public class ApplicationFragmentAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    /** Constructor of the class */
    public ApplicationFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    /** This method will be invoked when a page is requested to create */
    @Override
    public Fragment getItem(int arg0) {
        if (arg0 == 0) {
            PhaseOneFragment myFragment = new PhaseOneFragment();
            Bundle data = new Bundle();
            data.putInt("current_page", arg0 + 1);
            myFragment.setArguments(data);
            return myFragment;
        }
        else if (arg0 == 1) {
            PhaseTwoFragment myFragment = new PhaseTwoFragment();
            Bundle data = new Bundle();
            data.putInt("current_page", arg0 + 1);
            myFragment.setArguments(data);
            return myFragment;
        }
        else if (arg0 == 2) {
            PhaseThreeFragment myFragment = new PhaseThreeFragment();
            Bundle data = new Bundle();
            data.putInt("current_page", arg0 + 1);
            myFragment.setArguments(data);
            return myFragment;
        }
        return null;
    }

    /** Returns the number of pages */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}