package com.example.interappdemo.declaration;

import ch.post.it.sheldon.core.api.datastructures.serialization.ScalarSerialization;
import ch.post.it.sheldon.remoting.api.BroadcastDeclaration;
import ch.post.it.sheldon.remoting.api.DataSerialization;
import ch.post.it.sheldon.remoting.core.api.BroadcastType;

public class InterappDemoBroadcastDeclaration {

    private static final BroadcastType BROADCAST_TYPE = BroadcastType.of("interapp_demo_broadcast");

    private static final BroadcastDeclaration<String> MESSAGE =
            BroadcastDeclaration.of(BROADCAST_TYPE,
                    DataSerialization.of(ScalarSerialization.string()));

    public static BroadcastDeclaration<String> message() {
        return MESSAGE;
    }
}
