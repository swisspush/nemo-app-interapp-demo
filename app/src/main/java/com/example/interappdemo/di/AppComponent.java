package com.example.interappdemo.di;

import com.example.interappdemo.InterappDemoApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        BindingModule.class
})
@Singleton
@SuppressWarnings("WeakerAccess")
public interface AppComponent extends AndroidInjector<InterappDemoApplication> {
}