package com.example.interappdemo.ui;

import android.os.Bundle;

import com.example.interappdemo.R;
import com.example.interappdemo.ui.main.MainFragment;

import javax.inject.Inject;

import ch.post.it.sheldon.time.api.Timer;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
