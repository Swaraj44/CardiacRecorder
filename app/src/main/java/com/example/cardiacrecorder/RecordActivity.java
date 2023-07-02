package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;


public class RecordActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        Button cancelBtn = findViewById(R.id.buttonCancel);
        cancelBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Dash_Board.class));
            finish();
        });
    }
}