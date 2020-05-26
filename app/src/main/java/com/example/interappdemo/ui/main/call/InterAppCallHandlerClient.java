package com.example.interappdemo.ui.main.call;

import java.util.logging.Level;
import java.util.logging.Logger;

import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoCallDeclaration;
import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoCallHandler;
import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoData;
import ch.post.it.sheldon.platform.api.apps.AppIdConverter;
import ch.post.it.sheldon.platform.api.apps.PortableAppId;
import ch.post.it.sheldon.promise.api.Promise;
import ch.post.it.sheldon.remoting.api.ModuleInstanceId;
import ch.post.it.sheldon.remoting.api.Remoting;
import ch.post.it.sheldon.remoting.core.api.EndpointId;
import java8.util.function.Supplier;

public class InterAppCallHandlerClient implements DeclarationDemoCallHandler {

    private static final Logger LOG = Logger.getLogger(InterAppCallHandlerClient.class.getSimpleName());

    private final static PortableAppId DESTINATION = PortableAppId.of("remoting_demo");

    private final Supplier<Remoting> remoting;
    private final Supplier<AppIdConverter> appIdConverter;

    public InterAppCallHandlerClient(Supplier<AppIdConverter> appIdConverter, Supplier<Remoting> remoting){
        this.appIdConverter = appIdConverter;
        this.remoting = remoting;
    }

    @Override
    public Promise<String> message(DeclarationDemoData message) {
        return appIdConverter.get().toNative(DESTINATION).thenCompose(nativeAppIdOptional -> {
            LOG.log(Level.INFO, "*** message {0} to {1} ", new Object[]{message, DESTINATION});
            if (nativeAppIdOptional.isPresent()) {
                EndpointId endpointId = remoting.get().toEndpointId(nativeAppIdOptional.get());
                return this.remoting.get().calls().call(
                        DeclarationDemoCallDeclaration.message(),
                        endpointId,
                        ModuleInstanceId.primary(),
                        message).thenApply(s -> {
                            LOG.log(Level.INFO, "*** got answer {0} / "+s, s);
                            return s;
                });
            }
            else {
                LOG.log(Level.INFO, "*** could not send message {0} to {1} (it seems not to be installed)",
                        new Object[]{message, DESTINATION});
                return Promise.resolve("No Message received");
            }
        });

    }
}
