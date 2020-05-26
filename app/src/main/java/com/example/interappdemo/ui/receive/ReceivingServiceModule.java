package com.example.interappdemo.ui.receive;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.interappdemo.ui.receive.declarations.ReceiverCallModuleDeclaration;
import com.example.interappdemo.ui.receive.declarations.ReceiverFireModuleDeclaration;

import javax.inject.Provider;
import javax.inject.Singleton;

import ch.post.it.sheldon.remoting.api.ModuleDeclaration;
import ch.post.it.sheldon.remoting.api.Remoting;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class ReceivingServiceModule {

    @Provides
    ReceiverViewModelFactory viewModelFactory(ReceiverHandler handler) {
        return new ReceiverViewModelFactory(handler);
    }

    @Provides
    ViewModel viewModel(ReceiverHandler handler) {
        return new ReceiverViewModel(handler);
    }

    @Provides
    @Singleton
    ReceiverHandler handler(Context context, Provider<Remoting> remoting) {
        return new ReceiverHandler(context, remoting::get);
    }

    @IntoSet
    @Provides
    @Singleton
    ModuleDeclaration fireModuleDeclaration(Provider<ReceiverHandler> handler){
        return ReceiverFireModuleDeclaration.get().createModuleDeclaration(handler::get);
    }

    @IntoSet
    @Provides
    @Singleton
    ModuleDeclaration callModuleDeclaration(Provider<ReceiverHandler> handler) {
        return ReceiverCallModuleDeclaration.get().createModuleDeclaration(handler::get);
    }

}
