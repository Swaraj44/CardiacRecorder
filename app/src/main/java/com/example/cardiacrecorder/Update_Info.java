package com.example.cardiacrecorder;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class Update_Info extends AppCompatActivity {



    private EditText mEmail, mPass;
    private Button saveBtn,backBtn;

    private EditText edate, etime ,esystolic, ediastolic, eheart, ecomment;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        backBtn = findViewById(R.id.discard1);
        saveBtn = findViewById(R.id.save_btn1);
        //edate =findViewById(R.id.dt);
        //etime=findViewById(R.id.tm);
        esystolic=findViewById(R.id.sp1);
        ediastolic=findViewById(R.id.dp1);
        eheart=findViewById(R.id.hr1);
        // ecomment=findViewById(R.id.cm);


        mAuth = FirebaseAuth.getInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add();
                check();

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            Bundle bb=getIntent().getExtras();
            String email=bb.getString("xemail");
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update_Info.this,Dash_Board.class);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
                //onBackPressed();
            }
        });

    }


    public void check(){

        //////////////////////////////////////////////////////////

        String date, time ,systolic, diastolic, heart, comment;

        ///////////////////////////////////////////////////////

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        date = dateFormat.format(new Date());
        time = timeFormat.format(new Date());


        //date=edate.getText().toString();
        //time=etime.getText().toString();
        systolic=esystolic.getText().toString();
        diastolic=ediastolic.getText().toString();
        heart=eheart.getText().toString();
        //comment=ecomment.getText().toString();




        int systolicPressure = Integer.parseInt(systolic);
        int diastolicPressure = Integer.parseInt(diastolic);
        int heartRate = Integer.parseInt(heart);


        //////////////////////////////////////////////////////

        comment = "";

        if (systolicPressure >= 140 || diastolicPressure >= 90) {
            comment = "High blood pressure!";
        } else if (systolicPressure <= 90 || diastolicPressure <= 60) {
            comment = "Low blood pressure!";
        } else if (heartRate < 60 || heartRate > 100) {
            comment = "Irregular heart rate!";
        } else {
            comment = "Normal";
        }





        /////////////////////////////////////////////////////




        /////////////////////////////////////////////////////////////////////
        HashMap<String, String> userMap = new HashMap<>();

        //date, time ,systolic, diastolic, heart, comment


        Bundle bb1=getIntent().getExtras();
        String emailkey=bb1.getString("kemailkey");
        String key=bb1.getString("kkey");



        String valid_bit="v";

        userMap.put("date", date);
        userMap.put("time", time);
        userMap.put("systolic_pressure", systolic);
        userMap.put("diastolic_pressure", diastolic);
        userMap.put("heart_rate", heart);
        userMap.put("comment", comment);
        userMap.put("key", key);
        userMap.put("emailkey", emailkey);
        userMap.put("valid", valid_bit);


        /////////////////////////////////////////////////////////


        Updating(userMap,emailkey,key);

    }

    public void Updating(HashMap<String, String> userMap,String EmailKey,String Key){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference().child("CardiacRecorder").child("UsersHistory").child(EmailKey).child(Key);

        String recordKey = root.getKey(); // Retrieve the generated record key
        //userMap.put("key", recordKey);
        root.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Update_Info.this, "Data Updated!", Toast.LENGTH_SHORT).show();
            }
        });

    }




   }