package com.example.cardiacrecorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Dash_Board extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<DATA> dataList;

    ImageView imageView, signOut;
    Button newEntry;
    TextView tt;
    private final FirebaseDatabase db = FirebaseDatabase.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        tt = findViewById(R.id.textName);
        imageView = findViewById(R.id.imageProfile);
        newEntry = findViewById(R.id.fabNewEntry);
        signOut = findViewById(R.id.imageSignOut);


        DatabaseReference rootST = db.getReference().child("CardiacRecorder").child("USERS");
        Bundle bb = getIntent().getExtras();
        String email_owner = bb.getString("email");
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        String currentDate = dateFormat.format(new Date());
        String currentTime = timeFormat.format(new Date());

        dataList = new ArrayList<>();
        dataList.add(new DATA(currentDate, currentTime, "120", "80", "70"));
        // Add more items to the list as needed

        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.recordRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);

        setListener();
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

        newEntry.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RecordActivity.class)));
    }


}