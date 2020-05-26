package com.example.interappdemo.ui.receive;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.example.interappdemo.declaration.DemoData;

import java.util.HashMap;

import javax.inject.Inject;

public class ReceiverViewModel extends ViewModel {

    private final ReceiverHandler interAppHandler;

    @Inject
    ReceiverViewModel(ReceiverHandler handler) {
        this.interAppHandler = handler;

        // RECEIVE BROADCAST
        handler.broadcastData().thenAccept(handler::putBroadcastData);
    }

    public LiveData<HashMap<String, DemoData>> events(){
        return LiveDataReactiveStreams.fromPublisher(interAppHandler.events());
    }
}
