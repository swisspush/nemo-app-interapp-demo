package com.example.interappdemo.declaration;

import ch.post.it.sheldon.core.api.datastructures.serialization.ObjectSerialization;
import ch.post.it.sheldon.core.api.datastructures.serialization.ScalarSerialization;
import ch.post.it.sheldon.core.api.datastructures.tuples.Tuples;
import ch.post.it.sheldon.core.api.serialization.Serialization;
import ch.post.it.sheldon.time.api.Timer;

public class DemoData implements Comparable<DemoData> {

    private final String message;
    private final Boolean startApp;
    private final long timestamp;

    private static final String KEY_MESSAGE = "message";
    private static final String KEY_START_APP = "startApp";
    private static final String KEY_TIMESTAMP = "timestamp";

    public static final Serialization<DemoData> SERIALIZATION =
            ObjectSerialization.get().create(
                    KEY_MESSAGE, ScalarSerialization.string(),
                    KEY_START_APP, ScalarSerialization.bool(),
                    KEY_TIMESTAMP, ScalarSerialization.i64(),
                    tuple -> new DemoData(tuple.get1(), tuple.get2(), tuple.get3()),
                    data -> Tuples.create(data.message(), data.startApp(), data.timestamp())
            );

    public DemoData(String message, Boolean startApp) {
        this.message = message;
        this.startApp = startApp;
        this.timestamp = Timer.get().currentTimeMillis();
    }

    public DemoData(String message, Boolean startApp, long timestamp) {
        this.message = message;
        this.startApp = startApp;
        this.timestamp = timestamp;
    }

    public String message() {
        return message;
    }

    public Boolean startApp() {
        return startApp;
    }

    public long timestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "DemoData{" +
                "message=" + message +
                ", start_app=" + startApp +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public int compareTo(DemoData f) {
        return Long.compare(f.timestamp, timestamp);
    }
}
