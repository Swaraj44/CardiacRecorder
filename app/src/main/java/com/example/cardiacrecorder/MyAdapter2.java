package com.example.cardiacrecorder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

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

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(context, Update_Info.class);
                intent.putExtra("kemailkey", item.getEmailkey());
                intent.putExtra("kkey", item.getKey());
                context.startActivity(intent);

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String, String> userMap = new HashMap<>();
                String valid_bit="i";

                userMap.put("date"," ");
                userMap.put("time", " ");
                userMap.put("systolic_pressure", " ");
                userMap.put("diastolic_pressure", " ");
                userMap.put("heart_rate", " ");
                userMap.put("comment", " ");
                userMap.put("key", item.getKey());
                userMap.put("emailkey", item.getEmailkey());
                userMap.put("valid", valid_bit);

                String emailkey11=item.getEmailkey(),key11=item.getKey();


                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference root = db.getReference().child("CardiacRecorder").child("UsersHistory").child(emailkey11).child(key11);

                root.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "Data Deleted!", Toast.LENGTH_SHORT).show();
                        //mList.clear(); // Clear the existing dataset
                                   // Add the new dataset
                        //notifyDataSetChanged();
                        Intent intent = new Intent(context, Dash_Board.class);
                        intent.putExtra("email", item.getEmailkey());
                        context.startActivity(intent);
                    }
                });



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

            update = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}

