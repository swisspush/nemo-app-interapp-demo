package com.example.interappdemo.di;

import com.example.interappdemo.ui.MainActivity;
import com.example.interappdemo.ui.main.MainFragment;
import com.example.interappdemo.ui.main.broadcast.InterAppBroadcastFragment;
import com.example.interappdemo.ui.main.call.InterAppCallFragment;
import com.example.interappdemo.ui.main.fire.InterAppFireFragment;
import com.example.interappdemo.ui.receive.ReceiverFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
@SuppressWarnings("WeakerAccess")
public abstract class BindingModule {
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @ContributesAndroidInjector
    abstract InterAppFireFragment interAppFireFragment();

    @ContributesAndroidInjector
    abstract InterAppCallFragment interAppCallFragment();

    @ContributesAndroidInjector
    abstract InterAppBroadcastFragment interAppBroadcastFragment();

    @ContributesAndroidInjector
    abstract ReceiverFragment receiverFragment();
}
