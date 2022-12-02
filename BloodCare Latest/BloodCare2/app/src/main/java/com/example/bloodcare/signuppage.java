package com.example.bloodcare;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signuppage extends AppCompatActivity {
    EditText mname1,mname2,musername,memail,mpassword,mcontact;
    Button signupbtn;
    FirebaseAuth fAuth;
    FirebaseUser fuser;
    FirebaseFirestore fStore;
    ProgressBar mprogressBar;
    String userID;

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
        fStore = FirebaseFirestore.getInstance();
        fuser = fAuth.getCurrentUser();


        signupbtn.setOnClickListener(v -> {
            String email = memail.getText() . toString() .trim();
            String password = mpassword.getText() . toString() .trim() ;
            String username = musername.getText() . toString() .trim();
            String name1 = mname1.getText() . toString();
            String name2 = mname2.getText() . toString();
            String contact = mcontact.getText() . toString();
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

                    // send Email Verification Link

                    FirebaseUser ffuser = fAuth.getCurrentUser();
                    ffuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(signuppage.this, "Verification Email Has Been Sent ", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(e -> {
                        Log.d(TAG, "onFailure: Email not Sent" +e.getMessage());

                    });

                    Toast.makeText(signuppage.this, "Successfully Created An Account.", Toast.LENGTH_SHORT).show();
                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("User Database").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("Email", email);
                    user.put("Username", username);
                    user.put("First_name", name1);
                    user.put("Last_name", name2);
                    user.put("Phone", contact);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());
                        }
                    });
                    startActivity(new Intent(getApplicationContext(),loginpage.class));
                }else {
                    Toast.makeText(signuppage.this, "Error Signing Up! " +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

    }
}