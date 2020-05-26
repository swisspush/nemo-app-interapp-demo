package com.example.interappdemo.declaration;

import ch.post.it.sheldon.components.api.ComponentId;
import ch.post.it.sheldon.core.api.datastructures.serialization.ScalarSerialization;
import ch.post.it.sheldon.remoting.api.CallDeclaration;
import ch.post.it.sheldon.remoting.api.CallType;
import ch.post.it.sheldon.remoting.api.DataSerialization;
import ch.post.it.sheldon.remoting.api.ModuleId;
import ch.post.it.sheldon.remoting.core.api.Security;
import ch.post.it.sheldon.time.api.Duration;
import java8.util.Lists;

public final class InterappDemoCallDeclaration {

    private static final ComponentId COMPONENT_ID = ComponentId.of("interapp_demo_call@1.0");
//    private static final ModuleId MODULE_ID = COMPONENT_ID.moduleId();
    // This is the timeout we give an application to respond to our call
    private static final Duration TIMEOUT = Duration.ofSeconds(2);

    // Call Declarations
    private static final CallDeclaration<DemoData, String> GET_MESSAGE =
            CallDeclaration.of(
                    COMPONENT_ID.moduleId(),
                    CallType.of("get_message"),
                    DataSerialization.of(DemoData.SERIALIZATION),
                    DataSerialization.of(ScalarSerialization.string()),
                    TIMEOUT,
                    Security.MULTIPLE_DOMAINS,
                    Lists.of("com.example"));

    private InterappDemoCallDeclaration() {
    }

    public static ModuleId moduleId() {
        return COMPONENT_ID.moduleId();
    }

    public static CallDeclaration<DemoData, String> message(){
        return GET_MESSAGE;
    }
}
