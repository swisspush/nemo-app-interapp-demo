package com.example.interappdemo.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interappdemo.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ch.post.it.nemo.demo.remotingdeclaration.api.DeclarationDemoData;

public class InterAppDataAdapter extends RecyclerView.Adapter<InterAppDataAdapter.ViewHolder> {

    private final List<DeclarationDemoData> dataList;

    public InterAppDataAdapter(List<DeclarationDemoData> dataList){
        this.dataList = dataList;
    }

    @Override
    @NonNull
    public InterAppDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.interapp_item, parent, false);

        return new InterAppDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InterAppDataAdapter.ViewHolder viewHolder, int position) {
        DeclarationDemoData data = this.dataList.get(position);

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN);
        TextView time = viewHolder.timestamp;
        time.setText(formatter.format(data.timestamp()));

        TextView message = viewHolder.message;
        message.setText(data.message());

        TextView startApp = viewHolder.startApp;
        String startAppText = "Start App: ";
        startApp.setText(startAppText.concat(data.startApp().toString()));
    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView message;
        public TextView startApp;
        public TextView timestamp;

        public ViewHolder(View itemView) {
            super(itemView);

            this.message = itemView.findViewById(R.id.list_item_title);
            this.startApp = itemView.findViewById(R.id.list_item_text);
            this.timestamp = itemView.findViewById(R.id.list_item_overline);
        }
    }
}
