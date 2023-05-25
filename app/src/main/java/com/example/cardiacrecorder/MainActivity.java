package com.example.cardiacrecorder;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private TextView mTextView;
    private Button signInBtn;


    String st;
    String ss;
    String sname, sid;
    int done = 0;
    int ck = 0;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.email_signin);
        mPass = findViewById(R.id.passsignin);

        signInBtn = findViewById(R.id.singin_btn);

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

    }

    private void loginUser() {
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

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
                                startActivity(ii);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                progressDialog.dismiss();
                mPass.setError("Empty Fields Are not Allowed");
            }
        } else if (email.isEmpty()) {
            progressDialog.dismiss();
            mEmail.setError("Empty Fields Are not Allowed");
        } else {
            progressDialog.dismiss();
            mEmail.setError("Please Enter Correct Email");
        }
    }


    public void SIGNUP(View view) {
        Intent in = new Intent(MainActivity.this, SignUp.class);
        startActivity(in);
        // Do something in response to button click
    }

}




