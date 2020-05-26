package com.example.interappdemo;

import android.app.Application;

import com.example.interappdemo.di.AppModule;
import com.example.interappdemo.di.DaggerAppComponent;

import javax.inject.Inject;

import ch.post.it.sheldon.core.android.looper.LooperStaticInitializationStep;
import ch.post.it.sheldon.core.jvm.anydata.AnyDataStaticInitializationStep;
import ch.post.it.sheldon.promise.jvm.PromiseStaticInitializationStep;
import ch.post.it.sheldon.time.android.TimeStaticInitializationStep;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class InterappDemoApplication extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build()
                .inject(this);

        TimeStaticInitializationStep.get().initialize();
        AnyDataStaticInitializationStep.get().initialize();
        PromiseStaticInitializationStep.get().initialize();
        LooperStaticInitializationStep.get().initialize();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
