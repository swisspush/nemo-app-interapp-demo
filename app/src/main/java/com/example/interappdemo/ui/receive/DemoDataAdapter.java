package com.example.interappdemo.ui.receive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interappdemo.R;
import com.example.interappdemo.declaration.DemoData;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DemoDataAdapter extends RecyclerView.Adapter<DemoDataAdapter.ViewHolder> {

    private final List<DemoData> dataList;

    public DemoDataAdapter(List<DemoData> dataList){
        this.dataList = dataList;
    }

    @Override
    @NonNull
    public DemoDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.event_item, parent, false);

        return new DemoDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DemoDataAdapter.ViewHolder viewHolder, int position) {
        DemoData data = this.dataList.get(position);

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
