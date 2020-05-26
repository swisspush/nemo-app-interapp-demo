package com.example.interappdemo.ui.receive;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.interappdemo.declaration.DemoData;
import com.example.interappdemo.ui.MainActivity;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoBroadcastDeclaration;
import ch.post.it.sheldon.promise.api.Promise;
import ch.post.it.sheldon.remoting.api.Broadcasts;
import ch.post.it.sheldon.remoting.api.Remoting;
import ch.post.it.sheldon.rspromise.api.RsPromise;
import ch.post.it.sheldon.time.api.Timer;
import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import java8.util.function.Supplier;

public class ReceiverHandler {

    private static final Logger LOG = Logger.getLogger(ReceiverHandler.class.getSimpleName());
    private static final String PREFIX = "data";

    private final Context context;
    private final Supplier<Remoting> remoting;
    private final BehaviorProcessor<HashMap<String, DemoData>> events = BehaviorProcessor.createDefault(new HashMap<>());

    public ReceiverHandler(Context context, Supplier<Remoting> remoting) {
        this.context = context;
        this.remoting = remoting;
    }

    // RECEIVE BROADCAST
    public Promise<String> broadcastData(){
        return RsPromise.get().toPromise(remoting.get().broadcasts().stream(DeclarationDemoBroadcastDeclaration.message())).thenApply(Broadcasts.BroadcastData::data);
    }

    public Flowable<HashMap<String, DemoData>> events(){
        return events;
    }

    public void putFireData(DemoData data){
        Toast.makeText(context, "Fire message: "+data.message(), Toast.LENGTH_SHORT).show();
        LOG.log(Level.INFO, "*** Got event via remoting {0}", data);
        long timestamp = Timer.get().currentTimeMillis();
        HashMap<String, DemoData> events = this.events.getValue();
        events.put(PREFIX.concat(Long.toString(timestamp)), data);

        if (data.startApp()){
            LOG.log(Level.INFO, "*** Start App due to Remoting");
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public Promise<String> putCallData(DemoData data){
        Toast.makeText(context, "Call message: "+data.message(), Toast.LENGTH_SHORT).show();
        long timestamp = Timer.get().currentTimeMillis();
        HashMap<String, DemoData> events = this.events.getValue();
        events.put(PREFIX.concat(Long.toString(timestamp)), data);

        return Promise.resolve("After a while crocodile!");
    }

    public void putBroadcastData(String message){
        Toast.makeText(context, "Broadcast message: "+message, Toast.LENGTH_SHORT).show();
        long timestamp = Timer.get().currentTimeMillis();
        DemoData data = new DemoData(message, false);
        HashMap<String, DemoData> events = this.events.getValue();
        events.put(PREFIX.concat(Long.toString(timestamp)), data);
    }
}
