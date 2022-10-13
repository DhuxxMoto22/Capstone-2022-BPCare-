package com.example.bloodcare;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signuppage extends AppCompatActivity {
    EditText mname1,mname2,musername,memail,mpassword,mcontact;
    Button signupbtn;
    FirebaseAuth fAuth;
    FirebaseUser fuser;
    ProgressBar mprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        mname1 = findViewById(R.id.name1);
        mname2 = findViewById(R.id.name2);
        musername = findViewById(R.id.username);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mcontact = findViewById(R.id.contact);
        signupbtn= findViewById(R.id.signupbtn);
        mprogressBar= findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        fuser = fAuth.getCurrentUser();


        signupbtn.setOnClickListener(v -> {
            String email = memail.getText() . toString() .trim();
            String password = mpassword.getText() . toString() .trim() ;
            if(TextUtils.isEmpty(email)) {
                memail.setError("Email is Required. ") ;
                return;
            }
            if(TextUtils.isEmpty(password)){
                mpassword.setError("Password is Required. ") ;
                return;
            }
            if(password.length() < 6){
                mpassword.setError("Password Must be >= 6 Characters") ;
                return;
            }

            mprogressBar.setVisibility(View.VISIBLE);

            //register user in firebase

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){

                    startActivity(new Intent(getApplicationContext(),loginpage.class));
                    Toast.makeText(signuppage.this, "Successfully Created An Account.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(signuppage.this, "Error Signing Up!" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

    }
}