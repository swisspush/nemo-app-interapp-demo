package com.example.interappdemo.ui.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoActionDeclaration;
import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoBroadcastDeclaration;
import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoData;
import ch.post.it.sheldon.platform.api.apps.AppIdConverter;
import ch.post.it.sheldon.platform.api.apps.PortableAppId;
import ch.post.it.sheldon.promise.api.Promise;
import ch.post.it.sheldon.promise.api.Unit;
import ch.post.it.sheldon.remoting.api.Remoting;
import ch.post.it.sheldon.remoting.core.api.EndpointId;
import java8.util.function.Supplier;

public class InterAppHandler {

    private static final Logger LOG = Logger.getLogger(InterAppHandler.class.getSimpleName());
    private static final PortableAppId DESTINATION = PortableAppId.of("remoting_demo");

    private final Supplier<AppIdConverter> appIdConverter;
    private final Supplier<Remoting> remoting;
    private final List<Promise<Unit>> promises = new ArrayList<>();


    public InterAppHandler(Supplier<AppIdConverter> appIdConverter, Supplier<Remoting> remoting) {
        this.appIdConverter= appIdConverter;
        this.remoting = remoting;
    }

    public void sendEvent(String message, boolean startApp){
        LOG.log(Level.INFO, "sendEvent {0} / {1}", new Object[]{message, startApp});
        appIdConverter.get().toNative(DESTINATION).thenAccept(destinationApp -> {
            if (destinationApp.isPresent()){
                EndpointId destination = remoting.get().toEndpointId(destinationApp.get());
                DeclarationDemoData payload = new DeclarationDemoData(message, startApp);
                remoting.get().actions().fire(DeclarationDemoActionDeclaration.get(), destination, payload);
            }
        });
    }

    public void sendBroadcast(String message){
        remoting.get().broadcasts().fire(DeclarationDemoBroadcastDeclaration.message(), message);
    }

    public void dispose() {
        Promise.get().all(promises).thenRun(() -> { });
    }
}
