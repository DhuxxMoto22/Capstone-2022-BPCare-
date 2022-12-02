package com.example.bloodcare;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class loginpage extends AppCompatActivity {
    EditText memail2, mpassword2;
    Button mloglogin2, msignupnow2;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            memail2 = findViewById(R.id.email2);
            mpassword2 = findViewById(R.id.logpassword);
            mloglogin2 = findViewById(R.id.loglogin);
            msignupnow2 = findViewById(R.id.signupnow);
            fAuth = FirebaseAuth.getInstance();

            mloglogin2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = memail2.getText().toString().trim();
                    String password = mpassword2.getText().toString().trim();
                    if (TextUtils.isEmpty(email)) {
                        memail2.setError("Email is Required. ");
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        mpassword2.setError("Password is Required. ");
                        return;
                    }
                    if (password.length() < 6) {
                        mpassword2.setError("Password Must be >= 6 Characters");
                        return;
                    }
                    //authenticate user

                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(loginpage.this, "Successfully Logged In. ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), homepage2.class));
                        } else {
                            Toast.makeText(loginpage.this, "Error Logging In! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });



                }
            });

            //go to next page
            msignupnow2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), signuppage.class));
                }
            });


            //go logout

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("some") != null) {
                Toast.makeText(getApplicationContext(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();
            }


        }

    }

}