package com.example.interappdemo.declaration;

import ch.post.it.sheldon.remoting.api.ActionDeclaration;
import ch.post.it.sheldon.remoting.api.ActionType;
import ch.post.it.sheldon.remoting.api.DataSerialization;
import ch.post.it.sheldon.remoting.api.ModuleId;
import ch.post.it.sheldon.remoting.core.api.Security;
import java8.util.Lists;

public class InterappDemoActionDeclaration {

    private static final ModuleId MODULE_ID = ModuleId.of("inter_app_demo_action");
    private static final ActionType ACTION_TYPE = ActionType.of("message");

    private static final ActionDeclaration<DemoData> MESSAGE =
            ActionDeclaration.of(
                    MODULE_ID,
                    ACTION_TYPE,
                    DataSerialization.of(DemoData.SERIALIZATION),
                    Security.MULTIPLE_DOMAINS,
                    Lists.of("com.example"));

    public static ActionDeclaration<DemoData> get() {
        return MESSAGE;
    }
}
