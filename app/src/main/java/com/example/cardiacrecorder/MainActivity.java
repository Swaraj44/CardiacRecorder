package com.example.cardiacrecorder;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private EditText mEmail, mPass;
    public Button signInBtn;
    String ss;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.inputEmail);
        mPass = findViewById(R.id.inputPassword);
        signInBtn = findViewById(R.id.buttonSignIn);
        TextView createNewAccount = findViewById(R.id.createNewAccount);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Signing in...");
                progressDialog.show();
                ss = mEmail.getText().toString();
                //search(ss);
                loginUser();
            }
        });

        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

    }


    private void loginUser() {
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();
        loading(true);
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!pass.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Login Successfully !!", Toast.LENGTH_SHORT).show();
                                Intent ii = new Intent(MainActivity.this, Dash_Board.class);
                                ii.putExtra("email", email);
                                //Toast.makeText(MainActivity.this, "Tested  1 !!", Toast.LENGTH_SHORT).show();

                                startActivity(ii);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                loading(false);
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                loading(false);
                progressDialog.dismiss();
                mPass.setError("Empty Fields Are not Allowed");
            }
        } else if (email.isEmpty()) {
            loading(false);
            progressDialog.dismiss();
            mEmail.setError("Empty Fields Are not Allowed");
        } else {
            loading(false);
            progressDialog.dismiss();
            mEmail.setError("Please Enter Correct Email");
        }
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            signInBtn.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);

        } else {
            signInBtn.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);

        }
    }

}




