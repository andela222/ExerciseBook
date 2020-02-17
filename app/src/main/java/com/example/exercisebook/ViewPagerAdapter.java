package com.example.exercisebook;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ViewPagerAdapter extends FragmentPagerAdapter{
    private int mNumOfTabs;
    private long userId;
    private SparseArray<Fragment> registeredFragments = new SparseArray<>();

    public ViewPagerAdapter(FragmentManager fm, int numOfTabs, long userId) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
        this.userId = userId;
    }
    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putLong("userId", userId);

        switch (position) {
            case 0:
                ExerciseDayFragment fragment = new ExerciseDayFragment();
                fragment.setArguments(bundle);
                registeredFragments.put(position, fragment);
                return fragment;
            case 1:
                MeasurementDayFragment fragment2 = new MeasurementDayFragment();
                fragment2.setArguments(bundle);
                registeredFragments.put(position, fragment2);
                return fragment2;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "History";
            case 1:
                return "Measurements";
            default:
                return null;
        }
    }


    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
