package com.example.cardiacrecorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class Dash_Board extends AppCompatActivity   {


    //////////////////////////

    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    //private DatabaseReference rootST= db.getReference().child("EIMS").child("Result").child("Student");

    DatabaseReference rootSS;
    //private MyAdapter adapter;
    private MyAdapter2 adapter2;
   // private ArrayList<DATA> list;
    //private ArrayList<DATA> list2;


    ImageView imageView, signOut;
    Button newEntry;
    TextView tt;
    //TextView tt42;
    FloatingActionButton xadd;
    String email_owner;

    ProgressBar progressBar;

    //String xemail;
    //private final FirebaseDatabase db = FirebaseDatabase.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

       progressBar = findViewById(R.id.progressBar);


        tt = findViewById(R.id.textName);
        imageView = findViewById(R.id.imageProfile);
        xadd=findViewById(R.id.fabNewEntry);
        signOut = findViewById(R.id.imageSignOut);


        DatabaseReference rootST = db.getReference().child("CardiacRecorder").child("USERS");
        Bundle bb = getIntent().getExtras();
        email_owner = bb.getString("email");
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


        //////////////////////////////////////////////////


        recyclerView = findViewById(R.id.recordRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SHOW();

        setListener();


        /////////////////////////////////////////////////
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void signOut() {
        showToast("signing out....");
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private void setListener() {
       signOut.setOnClickListener(v -> signOut());


       xadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii1=new Intent(Dash_Board.this, Add_Info.class);
                ii1.putExtra("xemail", email_owner);
                startActivity(ii1);


            }
        });




        //newEntry.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RecordActivity.class)));
    }





    void SHOW(){


        progressBar.setVisibility(View.VISIBLE);



       ArrayList<DATA> list2 = new ArrayList<DATA>();



        adapter2 = new MyAdapter2(this ,list2 );
       recyclerView.setAdapter(adapter2);





        String ss="\\.";
        String[] ss1 = email_owner.split(ss, 100);
        String emailkey="";

        for (String a : ss1)emailkey+=a;








        rootSS = db.getReference().child("CardiacRecorder").child("UsersHistory").child(emailkey);



        rootSS.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DATA model = dataSnapshot.getValue(DATA.class);

                     String valid ;
                    valid= model.getValid();
                    // Log.d("the model",model.toString());


                   if (valid.equals("v")) {
                      list2.add(model);

                    }

                    //Toast.makeText(Dash_Board.this, vv, Toast.LENGTH_SHORT).show();




                }
               adapter2.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


}