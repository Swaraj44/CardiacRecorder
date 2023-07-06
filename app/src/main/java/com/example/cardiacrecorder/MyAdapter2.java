package com.example.cardiacrecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    ArrayList<DATA> mList;
    Context context;
    private RecyclerViewClickListener itemClickListener;

    public MyAdapter2(Context context, ArrayList<DATA> mList,RecyclerViewClickListener itemClickListener){

        this.mList = mList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item , parent ,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DATA item = mList.get(position);


       // DATA item = dataList.get(position);
        holder.textDate.setText(item.getDate());
        holder.textTime.setText(item.getTime());
        holder.textSystolic.setText(item.getSystolic_pressure());
        holder.textDiastolic.setText(item.getDiastolic_pressure());
        holder.textHeartRate.setText(item.getHeart_rate());
        holder.textcomment.setText(item.getComment());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(item);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

       // TextView  name,email,mobile,designation,dept;

        TextView textDate;
        TextView textTime;
        TextView textSystolic;
        TextView textDiastolic;
        TextView textHeartRate;

        TextView textcomment;

        Button update, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textDate = itemView.findViewById(R.id.date);
            textTime = itemView.findViewById(R.id.time);
            textSystolic = itemView.findViewById(R.id.systolic);
            textDiastolic = itemView.findViewById(R.id.diastolic);
            textHeartRate = itemView.findViewById(R.id.heart);
            textcomment = itemView.findViewById(R.id.comment);

        }
    }
}

