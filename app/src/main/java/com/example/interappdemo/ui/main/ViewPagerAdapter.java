package com.example.interappdemo.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final FragmentManager fragmentManager;
    private final List<String> fragments;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, List<String> fragments) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentManager = fragmentManager;
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentManager.getFragmentFactory().instantiate(ClassLoader.getSystemClassLoader(), fragments.get(position));
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
