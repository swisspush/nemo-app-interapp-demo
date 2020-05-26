package com.example.interappdemo.ui.main;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.interappdemo.ui.main.call.InterAppCallModule;
import com.example.interappdemo.ui.main.fire.InterAppFireModule;
import com.example.interappdemo.ui.receive.ReceivingServiceModule;

import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoCallHandler;
import dagger.Module;
import dagger.Provides;

@Module(includes = {
        InterAppFireModule.class,
        InterAppCallModule.class,

        ReceivingServiceModule.class
})
public class InterAppModule {

    @Provides
    InterAppViewModelFactory viewModelFactory(Context context, InterAppHandler fireHandler, DeclarationDemoCallHandler callHandler) {
        return new InterAppViewModelFactory(context, fireHandler, callHandler);
    }

    @Provides
    ViewModel viewModel(Context context, InterAppHandler fireHandler, DeclarationDemoCallHandler callHandler) {
        return new InterAppViewModel(context, fireHandler, callHandler);
    }
}
