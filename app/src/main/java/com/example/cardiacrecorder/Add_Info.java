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

import java.util.HashMap;


public class Add_Info extends AppCompatActivity {

    private EditText mEmail, mPass;
    private Button saveBtn;

    private EditText name11, email11, mobile11, dept11;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);


        saveBtn = findViewById(R.id.save_btn);
        name11 = findViewById(R.id.name21);
        email11 = findViewById(R.id.email_signin21);
        mobile11 = findViewById(R.id.mobi21);
        dept11 = findViewById(R.id.dept21);


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
        String dg=bb.getString("desg");

        String name, email, mobile, designation, dept;

        name = name11.getText().toString();
        email = email11.getText().toString();
        mobile = mobile11.getText().toString();
        designation = dg;
        dept = dept11.getText().toString();


        String ss="\\.";
        String[] ss1 = email.split(ss, 100);
        String emailkey="";

        for (String a : ss1)emailkey+=a;



        /////////////////////////////////////////////////////////////////////
        HashMap<String, String> userMap = new HashMap<>();

        userMap.put("name", name);
        userMap.put("email", email);
        userMap.put("mobile", mobile);
        userMap.put("designation", designation);
        userMap.put("dept", dept);




        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference().child("EIMS").child(designation).child(emailkey);

        root.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Add_Info.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}