package com.example.interappdemo.declaration;

import ch.post.it.sheldon.promise.api.Promise;

public interface InterappDemoCallHandler {
    Promise<String> message(DemoData message);
}
