package com.example.interappdemo.ui.main.call;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.interappdemo.R;
import com.example.interappdemo.ui.main.InterAppViewModel;
import com.example.interappdemo.ui.main.InterAppViewModelFactory;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class InterAppCallFragment extends DaggerFragment {

    @Inject
    InterAppViewModelFactory viewModelFactory;

    @Nullable
    private InterAppViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.interapp_content_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            viewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(InterAppViewModel.class);
        }

        // Title
        TextView title = view.findViewById(R.id.title);
        title.setText("Call DEMO");

        // Message
        TextInputLayout eventTextLayout = view.findViewById(R.id.event_text);
        EditText eventText = eventTextLayout.getEditText();
        if (eventText != null) {
            eventText.setText("Test Call from Interapp Demo");
        }

        // Start App
        CheckBox startApp = view.findViewById(R.id.start_app);
        startApp.setVisibility(View.GONE);

        // Button Send
        MaterialButton buttonSend = view.findViewById(R.id.btn_send);
        buttonSend.setOnClickListener(v -> {
            if (viewModel != null) {
                String message = "No Message";
                if (eventText != null) {
                    message = eventText.getText().toString();
                }
                viewModel.callData(message);
            }
        });
    }
}