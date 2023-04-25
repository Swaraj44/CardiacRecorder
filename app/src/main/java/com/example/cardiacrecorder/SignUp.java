package com.example.cardiacrecorder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;


public class SignUp extends AppCompatActivity {

    private EditText mEmail , mPass;
    private Button signUpBtn;

    private ImageView imageView;

    private ActivityResultLauncher<String> mGetContent;
    private Uri imageUri;

    private String link121=" ",Ekey;

    private EditText name11,email11,mobile11,designation11,dept11,id1;

    private FirebaseAuth mAuth;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmail = findViewById(R.id.email_signin);
        mPass = findViewById(R.id.passsignin);
        signUpBtn = findViewById(R.id.singup_btn);


        name11=findViewById(R.id.name1);
        email11=findViewById(R.id.email_signin);
        mobile11=findViewById(R.id.mobi1);




        imageView = findViewById(R.id.imageView9);

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result != null){
                    imageView.setImageURI(result);
                    imageUri = result;
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/jpeg");
            }
        });


        mAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IMG();
                createUser();
            }
        });

    }

    //Value adding function!

    public void add(){


        String name,email,mobile,designation,dept,id,lk;

        name=name11.getText().toString();

        mobile=mobile11.getText().toString();
        email=email11.getText().toString();
        lk=link121;



        String ss="\\.";
        String[] ss1 = email.split(ss, 100);
        String emailkey="";
        for (String a : ss1)emailkey+=a;
        Ekey=emailkey;






        /////////////////////////////////////////////////////////////////////
        HashMap<String, String> userMap = new HashMap<>();

        userMap.put("name", name);
        userMap.put("email", email);
        userMap.put("mobile", mobile);
        userMap.put("link", lk);





        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference().child("CardiacRecorder").child("USERS").child(emailkey);






        root.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(SignUp.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }






    private void createUser(){
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                Toast.makeText(SignUp.this, "Registered Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this ,MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(SignUp.this, "Registration Error !!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                progressDialog.dismiss();
                mPass.setError("Empty Fields Are not Allowed");
            }
        }

        else if(email.isEmpty()){
            progressDialog.dismiss();
            mEmail.setError("Empty Fields Are not Allowed");
        }else{
            progressDialog.dismiss();
            mEmail.setError("Pleas Enter Correct Email");
        }
    }



    public void IMG(){



        /////////////////////////////////////////////////////////////

        progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();


        //String name=name11.getText().toString();
        String email=email11.getText().toString();



        String ss="\\.";
        String[] ss1 = email.split(ss, 100);
        String emailkey="";
        for (String a : ss1)emailkey+=a;
        Ekey=emailkey;

        StorageReference SRef = FirebaseStorage.getInstance().getReference().child("Folder_CC").child(Ekey);

        SRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    SRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {


                        @Override
                        public void onSuccess(Uri uri) {
                            //////////////////////////////////////////////////////////////////////////////////

                            String url = String.valueOf(uri);

                            link121=url;

                            add();
                           // Toast.makeText(SignUp.this, link1, Toast.LENGTH_SHORT).show();
                            //////////////////////////////////////////////////////////////////////////////////

                        }
                    });
                }
            }
        });



    }

}
