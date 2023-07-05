package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class RecordActivity extends AppCompatActivity {

    TextView esystolic, ediastolic, eheart;
    DATA data = (DATA) getIntent().getSerializableExtra("data_key");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Button updateBtn = findViewById(R.id.buttonUpdate);
        Button deleteBtn = findViewById(R.id.buttonDelete);
        esystolic = findViewById(R.id.inputSystolic);
        ediastolic = findViewById(R.id.inputDiastolic);
        eheart = findViewById(R.id.inputHeartRate);




        esystolic.setText(data.getSystolic_pressure());
        ediastolic.setText(data.getDiastolic_pressure());
        eheart.setText(data.getHeart_rate());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

    }

    private void update() {

        Bundle bb = getIntent().getExtras();
        String email = bb.getString("email");

        String date, time, systolic, diastolic, heart, comment;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        date = dateFormat.format(new Date());
        time = timeFormat.format(new Date());

        systolic = esystolic.getText().toString();
        diastolic = ediastolic.getText().toString();
        heart = eheart.getText().toString();

        int systolicPressure = Integer.parseInt(systolic);
        int diastolicPressure = Integer.parseInt(diastolic);
        int heartRate = Integer.parseInt(heart);




        if (systolicPressure >= 140 || diastolicPressure >= 90) {
            comment = "High blood pressure!";
        } else if (systolicPressure <= 90 || diastolicPressure <= 60) {
            comment = "Low blood pressure!";
        } else if (heartRate < 60 || heartRate > 100) {
            comment = "Irregular heart rate!";
        } else {
            comment = "Normal";
        }


        String ss = "\\.";
        String[] ss1 = email.split(ss, 100);
        String emailkey = "";

        for (String a : ss1) emailkey += a;




        DatabaseReference usersHistoryRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("CardiacRecorder")
                .child("UsersHistory")
                .child(emailkey);


        HashMap<String, Object> userMap = new HashMap<>();


        userMap.put("date", date);
        userMap.put("time", time);
        userMap.put("systolic_pressure", systolic);
        userMap.put("diastolic_pressure", diastolic);
        userMap.put("heart_rate", heart);
        userMap.put("comment", comment);

        usersHistoryRef.updateChildren(userMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RecordActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}
