package com.example.interappdemo.di;

import android.app.Application;
import android.content.Context;

import com.example.interappdemo.ui.main.InterAppModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        SheldonRemotingModule.class,
        InterAppModule.class
})
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context context() {
        return application;
    }
}
