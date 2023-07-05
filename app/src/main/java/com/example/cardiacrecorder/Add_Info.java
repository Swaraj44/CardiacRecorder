package com.example.cardiacrecorder;

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


public class Add_Info extends AppCompatActivity {

    private EditText mEmail, mPass;
    private Button saveBtn;

    private EditText edate, etime ,esystolic, ediastolic, eheart, ecomment;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);


        saveBtn = findViewById(R.id.save_btn);
        //edate =findViewById(R.id.dt);
        //etime=findViewById(R.id.tm);
        esystolic=findViewById(R.id.sp);
        ediastolic=findViewById(R.id.dp);
        eheart=findViewById(R.id.hr);
        ecomment=findViewById(R.id.cm);


        mAuth = FirebaseAuth.getInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();

            }
        });

    }

    //Value adding function!

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
        comment=ecomment.getText().toString();






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
        userMap.put("systolic", systolic);
        userMap.put("diastolic", diastolic);
        userMap.put("heart", heart);
        userMap.put("comment", comment);




        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference().child("CardiacRecorder").child("UsersHistory").child(emailkey).push();

        root.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Add_Info.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });



    }
}