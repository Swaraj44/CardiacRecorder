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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class Add_Info extends AppCompatActivity {



    private EditText mEmail, mPass;
    private Button saveBtn,backBtn;

    private EditText edate, etime ,esystolic, ediastolic, eheart, ecomment;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        backBtn = findViewById(R.id.back_btn);
        saveBtn = findViewById(R.id.save_btn);
        //edate =findViewById(R.id.dt);
        //etime=findViewById(R.id.tm);
        esystolic=findViewById(R.id.sp);
        ediastolic=findViewById(R.id.dp);
        eheart=findViewById(R.id.hr);
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
               Intent intent = new Intent(Add_Info.this,Dash_Board.class);
                intent.putExtra("email", email);
              startActivity(intent);
                finish();
                //onBackPressed();
            }
        });

    }


    public void check(){

        //////////////////////////////////////////////////////////

        Bundle bb=getIntent().getExtras();
        String email=bb.getString("xemail");

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




        String ss="\\.";
        String[] ss1 = email.split(ss, 100);
        String emailkey="";

        for (String a : ss1)emailkey+=a;



        int x = Count(emailkey);
        x++;
        String kkey = Integer.toString(x);


        /////////////////////////////////////////////////////////////////////
        HashMap<String, String> userMap = new HashMap<>();

        //date, time ,systolic, diastolic, heart, comment

        String valid_bit="v";

        userMap.put("date", date);
        userMap.put("time", time);
        userMap.put("systolic_pressure", systolic);
        userMap.put("diastolic_pressure", diastolic);
        userMap.put("heart_rate", heart);
        userMap.put("comment", comment);
        userMap.put("key", kkey);
        userMap.put("emailkey", emailkey);
        userMap.put("valid", valid_bit);


        /////////////////////////////////////////////////////////


        ADDING(userMap,emailkey);

    }

    public void ADDING(HashMap<String, String> userMap,String EmailKey){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference().child("CardiacRecorder").child("UsersHistory").child(EmailKey).push();

        String recordKey = root.getKey(); // Retrieve the generated record key
        userMap.put("key", recordKey);
        root.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Add_Info.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public int Count(String key){

        final int[] x = {0};


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference rootSS = db.getReference().child("CardiacRecorder").child("UsersHistory").child(key);



        rootSS.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                    x[0]++;


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return x[0];
    }


    //Value adding function!

    /*
    public void add() {


        Bundle bb=getIntent().getExtras();
        String email=bb.getString("xemail");

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




        String ss="\\.";
        String[] ss1 = email.split(ss, 100);
        String emailkey="";

        for (String a : ss1)emailkey+=a;



        /////////////////////////////////////////////////////////////////////
        HashMap<String, String> userMap = new HashMap<>();

        //date, time ,systolic, diastolic, heart, comment

        userMap.put("date", date);
        userMap.put("time", time);
        userMap.put("systolic_pressure", systolic);
        userMap.put("diastolic_pressure", diastolic);
        userMap.put("heart_rate", heart);
        userMap.put("comment", comment);




        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference().child("CardiacRecorder").child("UsersHistory").child(emailkey).push();
        String recordKey = root.getKey();
        root.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Add_Info.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });



    }

    */
}