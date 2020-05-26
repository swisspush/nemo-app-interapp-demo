package com.example.interappdemo.ui.receive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interappdemo.R;
import com.example.interappdemo.declaration.DemoData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class ReceiverFragment extends DaggerFragment {

    @Inject
    ReceiverViewModelFactory viewModelFactory;

    @Nullable
    private ReceiverViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.receiver_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            viewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(ReceiverViewModel.class);

            // Event List
            RecyclerView eventsView = view.findViewById(R.id.events);
            viewModel.events().observe(getViewLifecycleOwner(), dataList -> {
                List<DemoData> data = new ArrayList<>(dataList.values());
                Collections.sort(data);
                DemoDataAdapter adapter = new DemoDataAdapter(data);
                eventsView.setAdapter(adapter);
            });
        }
    }
}