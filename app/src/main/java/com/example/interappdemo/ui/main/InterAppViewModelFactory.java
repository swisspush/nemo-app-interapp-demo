package com.example.interappdemo.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoCallHandler;

/**
 * A custom ViewModelFactory is needed because our NoteViewModel has a non empty
 * argument constructor. So we need this here to supply the noteProvider argument
 * to the NativeNoteViewModel constructor
 */
public class InterAppViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;
    private final InterAppHandler fireHandler;
    private final DeclarationDemoCallHandler callHandler;

    public InterAppViewModelFactory(Context context, InterAppHandler fireHandler, DeclarationDemoCallHandler callHandler) {
        this.context = context;
        this.fireHandler = fireHandler;
        this.callHandler = callHandler;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InterAppViewModel.class)) {
            return (T) new InterAppViewModel(context, fireHandler, callHandler);
        }
        throw new IllegalArgumentException("Unknown InterAppViewModel class");
    }
}
