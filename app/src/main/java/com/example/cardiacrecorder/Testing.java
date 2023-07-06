package com.example.cardiacrecorder;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Testing extends AppCompatActivity{




    // Registration logic
    private void registerUser(String email, String password) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Registration successful
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        sendEmailVerification(user);
                        // Handle the next steps, such as redirecting to another activity or showing a success message
                    } else {
                        // Registration failed
                        // Handle the failure, such as displaying an error message
                    }
                });
    }

    // Send email verification
    private void sendEmailVerification(FirebaseUser user) {
        user.sendEmailVerification()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Email sent successfully
                        // Handle the next steps, such as displaying a verification message or redirecting to a verification screen
                    } else {
                        // Email sending failed
                        // Handle the failure, such as displaying an error message
                    }
                });
    }



    // Code verification logic
    private void verifyCode(String verificationCode) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        firebaseAuth.checkActionCode(verificationCode)
                .addOnCompleteListener(task -> {
                    firebaseAuth.applyActionCode(verificationCode);

                    // Verification successful
                    // Proceed with the next steps, such as allowing the user to sign in or redirecting to a verified screen
                });
    }



















    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference rootST= db.getReference().child("EIMS").child("Result").child("Student");


    private MyAdapter2 adapter;
    private ArrayList<DATA> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


        //recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SHOW();

    }


    void SHOW(){


        list = new ArrayList<DATA>();
        //adapter = new MyAdapter(this ,list );

        recyclerView.setAdapter(adapter);

        rootST.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DATA model = dataSnapshot.getValue(DATA.class);


                    list.add(model);



                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }





}













