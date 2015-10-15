package com.global.analytics.firstsampleapp;

import android.support.v4.app.FragmentPagerAdapter;
/**
 * Created by Prakash on 15/10/15.
 */
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;

public class IntroFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;

    /** Constructor of the class */
    public IntroFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /** This method will be invoked when a page is requested to create */
    @Override
    public Fragment getItem(int arg0) {

        IntroFragment myFragment = new IntroFragment();
        Bundle data = new Bundle();
        data.putInt("current_page", arg0+1);
        myFragment.setArguments(data);
        return myFragment;
    }

    /** Returns the number of pages */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}