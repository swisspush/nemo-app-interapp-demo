package com.example.interappdemo.ui.main.fire;

import com.example.interappdemo.ui.main.InterAppHandler;

import javax.inject.Provider;
import javax.inject.Singleton;

import ch.post.it.sheldon.platform.api.apps.AppIdConverter;
import ch.post.it.sheldon.remoting.api.Remoting;
import dagger.Module;
import dagger.Provides;

@Module
public class InterAppFireModule {

    @Provides
    @Singleton
    InterAppHandler handler(Provider<AppIdConverter> appIdConverter, Provider<Remoting> remoting) {
        return new InterAppHandler(appIdConverter::get, remoting::get);
    }
}
