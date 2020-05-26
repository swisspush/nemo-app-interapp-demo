package com.example.interappdemo.di;

import ch.post.it.sheldon.async.android.AsyncModule;
import ch.post.it.sheldon.binser.android.BinSerModule;
import ch.post.it.sheldon.components.android.ComponentsModule;
import ch.post.it.sheldon.extensions.android.ExtensionsConsumerModule;
import ch.post.it.sheldon.platform.android.PlatformModule;
import ch.post.it.sheldon.remoting.android.RemotingServiceModule;
import ch.post.it.sheldon.remoting.core.android.RemotingCoreModule;
import ch.post.it.sheldon.time.android.TimeModule;
import dagger.Module;

@Module(includes = {
        RemotingCoreModule.class,
        RemotingServiceModule.class,

        AsyncModule.class,
        BinSerModule.class,
        ComponentsModule.class,
        ExtensionsConsumerModule.class,
        PlatformModule.class,
        TimeModule.class
})
public class SheldonRemotingModule {

}
