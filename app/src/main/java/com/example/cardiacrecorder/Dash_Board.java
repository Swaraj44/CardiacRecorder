package com.example.cardiacrecorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Dash_Board extends AppCompatActivity {

    ImageView imageView;
    TextView tt;

    String xemail;
    private final FirebaseDatabase db = FirebaseDatabase.getInstance();

    //Button xadd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        tt = findViewById(R.id.textName);
        imageView = findViewById(R.id.imageProfile);
        FloatingActionButton xadd=findViewById(R.id.fabNewEntry);



        DatabaseReference rootST = db.getReference().child("CardiacRecorder").child("USERS");
        Bundle bb = getIntent().getExtras();
        String email_owner = bb.getString("email");
        //xemail=email_owner;
        rootST.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User_Info model = dataSnapshot.getValue(User_Info.class);
                    assert model != null;
                    String link = model.getLink();
                    String email = model.getEmail();
                    String nm = model.getName();
                    //tt.setText(nm);
                    if (Objects.equals(email, email_owner)) {
                        tt.setText(nm);
                        Picasso.get().load(link).into(imageView);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        xadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii1=new Intent(Dash_Board.this, Add_Info.class);
                ii1.putExtra("xemail", email_owner);
                startActivity(ii1);


            }
        });





    }
}