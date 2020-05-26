package com.example.interappdemo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.interappdemo.R;
import com.example.interappdemo.ui.main.broadcast.InterAppBroadcastFragment;
import com.example.interappdemo.ui.main.call.InterAppCallFragment;
import com.example.interappdemo.ui.main.fire.InterAppFireFragment;
import com.example.interappdemo.ui.receive.ReceiverFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import dagger.android.support.DaggerFragment;

public class MainFragment extends DaggerFragment {

    private static final Logger LOG = Logger.getLogger(MainFragment.class.getSimpleName());

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.fire_demo:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.call_demo:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.broadcast_demo:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.receiving_demo:
                    viewPager.setCurrentItem(3);
                    break;
            }
            return true;
        });
        //used to prevent navigation if current item is reselected
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                LOG.info(item.toString() + " on bottomNavView reselected");
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        List<String> fragments = Arrays.asList(
                InterAppFireFragment.class.getName(),
                InterAppCallFragment.class.getName(),
                InterAppBroadcastFragment.class.getName(),
                ReceiverFragment.class.getName());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }
}
