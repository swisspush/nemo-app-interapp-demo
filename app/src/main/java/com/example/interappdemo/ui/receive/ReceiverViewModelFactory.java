package com.example.interappdemo.ui.receive;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * A custom ViewModelFactory is needed because our NoteViewModel has a non empty
 * argument constructor. So we need this here to supply the noteProvider argument
 * to the NativeNoteViewModel constructor
 */
public class ReceiverViewModelFactory implements ViewModelProvider.Factory {

    private final ReceiverHandler handler;

    public ReceiverViewModelFactory(ReceiverHandler handler) {
        this.handler = handler;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ReceiverViewModel.class)) {
            return (T) new ReceiverViewModel(handler);
        }
        throw new IllegalArgumentException("Unknown ReceiverViewModel class");
    }
}
