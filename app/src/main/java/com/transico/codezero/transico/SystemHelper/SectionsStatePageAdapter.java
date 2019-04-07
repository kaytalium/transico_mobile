package com.transico.codezero.transico.SystemHelper;

import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SectionsStatePageAdapter extends FragmentStatePagerAdapter implements ViewTreeObserver.OnScrollChangedListener {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private View.OnScrollChangeListener mScrollChangeListener = null;

    public SectionsStatePageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public void addOnScrollChangedlistner(View.OnScrollChangeListener scrollChangeListener){
        mScrollChangeListener = scrollChangeListener;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public void onScrollChanged() {

    }
}
