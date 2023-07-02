package com.example.cardiacrecorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardiacrecorder.DATA;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<DATA> dataList;

    public MyAdapter(List<DATA> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DATA item = dataList.get(position);
        holder.textDate.setText(item.getDate());
        holder.textTime.setText(item.getTime());
        holder.textSystolic.setText(item.getSystolic_pressure());
        holder.textDiastolic.setText(item.getDiastolic_pressure());
        holder.textHeartRate.setText(item.getHeart_rate());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textTime;
        TextView textSystolic;
        TextView textDiastolic;
        TextView textHeartRate;

        public ViewHolder(View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.textDate);
            textTime = itemView.findViewById(R.id.textTime);
            textSystolic = itemView.findViewById(R.id.textSystolic);
            textDiastolic = itemView.findViewById(R.id.textDiastolic);
            textHeartRate = itemView.findViewById(R.id.textHeartRate);
        }
    }
}
