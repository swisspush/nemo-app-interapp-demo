package com.example.interappdemo.ui.receive.declarations;

import com.example.interappdemo.declaration.InterappDemoCallDeclaration;
import com.example.interappdemo.ui.receive.ReceiverHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import ch.post.it.sheldon.core.api.datastructures.Disposable;
import ch.post.it.sheldon.remoting.api.ModuleDeclaration;
import java8.util.function.Supplier;

public class ReceiverCallModuleDeclaration {
    private static final Logger LOG = Logger.getLogger(ReceiverCallModuleDeclaration.class.getSimpleName());

    private static final ReceiverCallModuleDeclaration INSTANCE = new ReceiverCallModuleDeclaration();

    private ReceiverCallModuleDeclaration() {
    }

    public static ReceiverCallModuleDeclaration get() {
        return INSTANCE;
    }

    public ModuleDeclaration createModuleDeclaration(Supplier<ReceiverHandler> handler){
        return ModuleDeclaration.of(InterappDemoCallDeclaration.moduleId(), (instanceId, configurator) -> {
            LOG.log(Level.INFO, "Got call via remoting ");
            configurator.addCallHandler(InterappDemoCallDeclaration.message(),
                    (sender, data) -> handler.get().putCallData(data));
            return Disposable.empty();
        });
    }
}
