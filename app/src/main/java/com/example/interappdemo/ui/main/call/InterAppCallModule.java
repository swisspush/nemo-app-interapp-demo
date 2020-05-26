package com.example.interappdemo.ui.main.call;

import javax.inject.Provider;
import javax.inject.Singleton;

import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoCallHandler;
import ch.post.it.sheldon.platform.api.apps.AppIdConverter;
import ch.post.it.sheldon.remoting.api.Remoting;
import dagger.Module;
import dagger.Provides;

@Module
public class InterAppCallModule {

    @Provides
    @Singleton
    DeclarationDemoCallHandler handler(Provider<AppIdConverter> appIdConverter, Provider<Remoting> remoting) {
        return new InterAppCallHandlerClient(appIdConverter::get, remoting::get);
    }
}
