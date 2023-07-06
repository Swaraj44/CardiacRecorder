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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<DATA> mList;
    Context context;
    private RecyclerViewClickListener itemClickListener;

    public MyAdapter(Context context, ArrayList<DATA> mList,RecyclerViewClickListener itemClickListener){

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



        /*
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View myView = LayoutInflater.from(context).inflate(R.layout.edit_info, null);
                final AlertDialog dialog = new AlertDialog.Builder(context).setView(myView).create();
                dialog.setCancelable(false);



                TextView textSystolic1 = myView.findViewById(R.id.sp1);
                TextView textDiastolic1 = myView.findViewById(R.id.dp1);
                TextView textHeartRate1 = myView.findViewById(R.id.hr1);


                Button save= myView.findViewById(R.id.save_btn1);
                Button discard = myView.findViewById(R.id.discard1);


                // putting the current measure values

                textSystolic1.setText(item.getSystolic_pressure());
                textDiastolic1.setText(item.getDiastolic_pressure());
                textHeartRate1.setText(item.getHeart_rate());




                FirebaseAuth myAuth = FirebaseAuth.getInstance();
                String  onlineUserId = myAuth.getCurrentUser().getUid();
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("entries").child(onlineUserId);
                String id=measure.getId();
////////////////////////////////////////////////////////////////////////
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference root = db.getReference().child("CardiacRecorder").child("UsersHistory").child(emailkey).push();


                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a dd/MM/yyyy");
                        Date now = new Date();
                        String theDate=sdf.format(now);

                        Map<String,Object> map=new HashMap<>();
                        map.put("systolic",systolic.getText().toString().trim());
                        map.put("diastolic",diastolic.getText().toString().trim());
                        map.put("heartRate",heart.getText().toString().trim());
                        map.put("date",theDate);
                        map.put("comment",comment.getText().toString().trim());
                        map.put("id",id);


                        reference.child(id).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Entry has been Updated successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    String err = task.getException().toString();
                                    Toast.makeText(context, "Failed: " + err, Toast.LENGTH_SHORT).show();
                                }
                                loader.dismiss();
                            }
                        });
                        dialog.dismiss();
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog loader=new ProgressDialog(context);
                loader.setMessage("Deleting The Entry...");
                loader.setCanceledOnTouchOutside(true);
                loader.show();

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String onlineUserId = mAuth.getCurrentUser().getUid();
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("entries").child(onlineUserId);

                String id=measure.getId();
                reference.child(id).removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task <Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Entry has been Deleted successfully", Toast.LENGTH_SHORT).show();

                                } else {
                                    String err = task.getException().toString();
                                    Toast.makeText(context, "Failed: " + err, Toast.LENGTH_SHORT).show();
                                }
                                loader.dismiss();
                            }
                        });
            }
        });

        */

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


