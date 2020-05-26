package com.example.interappdemo.ui.receive.declarations;

import com.example.interappdemo.declaration.InterappDemoActionDeclaration;
import com.example.interappdemo.ui.receive.ReceiverHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import ch.post.it.sheldon.core.api.datastructures.Disposable;
import ch.post.it.sheldon.remoting.api.ModuleDeclaration;
import java8.util.function.Supplier;

public class ReceiverFireModuleDeclaration {

    private static final Logger LOG = Logger.getLogger(ReceiverFireModuleDeclaration.class.getSimpleName());

    private static final ReceiverFireModuleDeclaration INSTANCE = new ReceiverFireModuleDeclaration();

    private ReceiverFireModuleDeclaration() {
    }

    public static ReceiverFireModuleDeclaration get() {
        return INSTANCE;
    }

    public ModuleDeclaration createModuleDeclaration(Supplier<ReceiverHandler> appHandler){
        return ModuleDeclaration.of(InterappDemoActionDeclaration.get().moduleId(), (instanceId, configurator) -> {
            configurator.addActionHandler(InterappDemoActionDeclaration.get(), (source, data) -> {
                LOG.log(Level.INFO, "*** Got event via remoting {0}", data);
                appHandler.get().putFireData(data);
            });
            return Disposable.empty();
        });
    }
}
