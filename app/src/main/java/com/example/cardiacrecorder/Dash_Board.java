package com.example.cardiacrecorder;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Dash_Board extends AppCompatActivity {

    private Button b1, b2;
    ImageView imageView;
    TextView tt;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        b1 = findViewById(R.id.textView1112);
        b2 = findViewById(R.id.text1112);

        tt = findViewById(R.id.textView18);


        ////////////////////////////////////////////////

        imageView = findViewById(R.id.imageView232);

        ///////////////////////////////////////////////////////////////////////


        DatabaseReference rootST = db.getReference().child("CardiacRecorder").child("USERS");


        Bundle bb = getIntent().getExtras();
        String email_owner = bb.getString("email");

        rootST.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User_Info model = dataSnapshot.getValue(User_Info.class);

                    String link = model.getLink();
                    String email = model.getEmail();
                    String nm=model.getName();

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


        //////////////////////////////////////////////////

        /*
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Teachers_Pannel_Main.this,Put_Result.class);
                startActivity(in);
            }
        });

    public void BK(View view)
    {
        Intent in=new Intent(Teachers_Pannel_Main.this,MainActivity2.class);
        startActivity(in);
        finish();
        // Do something in response to button click
    }

*/

    }
}