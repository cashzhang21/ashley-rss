package com.cashzhang.nozdormu.adapter;

import android.util.Log;

import com.cashzhang.nozdormu.fragment.CollectionsFragment;
import com.cashzhang.nozdormu.fragment.ContentFragment;
import com.cashzhang.nozdormu.fragment.LeftFragment;
import com.cashzhang.nozdormu.fragment.StreamsFragment;
import com.cashzhang.nozdormu.fragment.FeedsFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by cz21 on 2018/5/23.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private final static String TAG = FragmentAdapter.class.getSimpleName();
    private List<Fragment> mFragments;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        // TODO Auto-generated constructor stub
        mFragments=fragments;
    }
    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: " + position);
        switch (position) {
            case 0:
                return LeftFragment.newInstance();
            case 1:
                return CollectionsFragment.newInstance();
            case 2:
                return FeedsFragment.newInstance();
            case 3:
                return StreamsFragment.newInstance();
            case 4:
                return ContentFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}