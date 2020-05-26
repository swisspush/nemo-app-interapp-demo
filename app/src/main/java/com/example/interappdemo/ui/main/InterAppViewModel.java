package com.example.interappdemo.ui.main;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoCallHandler;
import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoData;

public class InterAppViewModel extends ViewModel {
    private static final Logger LOG = Logger.getLogger(InterAppViewModel.class.getSimpleName());

    private final Context context;
    private final InterAppHandler interAppHandler;
    private final DeclarationDemoCallHandler callHandler;

    @Inject
    InterAppViewModel(Context context, InterAppHandler interAppHandler, DeclarationDemoCallHandler callHandler) {
        this.context = context;
        this.interAppHandler = interAppHandler;
        this.callHandler = callHandler;
    }

    public void fireData(String message, boolean startApp){
        LOG.log(Level.INFO,"*** Fired message: {0} / Start App {1}", new Object[]{message, startApp});
        Toast.makeText(context, "Fired message: "+message, Toast.LENGTH_SHORT).show();
        this.interAppHandler.sendEvent(message, startApp);
    }

    // SEND BROADCAST
    public void brodcastData(String message){
        LOG.log(Level.INFO,"*** Broadcast message: {0}", new Object[]{message});
        Toast.makeText(context, "Broadcast message: "+message, Toast.LENGTH_SHORT).show();
        this.interAppHandler.sendBroadcast(message);
    }

    public void callData(String message){
        LOG.log(Level.INFO,"*** Called message: {0}", new Object[]{message});
        Toast.makeText(context, "Called message: "+message, Toast.LENGTH_SHORT).show();
        DeclarationDemoData data = new DeclarationDemoData(message, false);
        this.callHandler.message(data).thenAccept(answer -> {
            LOG.log(Level.INFO,"*** Received answer {0}", new Object[]{answer});
            Toast.makeText(context, "Received answer: "+answer, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onCleared() {
        interAppHandler.dispose();
        super.onCleared();
    }
}
