package com.example.bloodcare;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bloodcare.databinding.ActivityPrivacyBinding;

public class Privacy extends AppCompatActivity {

    TextView Pass;
    TextView Personal;
    TextView Privacy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        Pass=findViewById(R.id.PasswordS);
        Personal=findViewById(R.id.Personal);
        Privacy=findViewById(R.id.back0);


        Pass.setOnClickListener(new View.OnClickListener() {

            //go to Password and sec


            @Override
            public void onClick(View v) {
                Pass.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction1=getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.PrivSec,new passwordsecfragment()).commit();

            }
        });
        Personal.setOnClickListener(new View.OnClickListener() {

            //go to Personal

            @Override
            public void onClick(View v) {
                Personal.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction2=getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.PrivSec,new PersonalaccFragment()).commit();

            }


        });
        Privacy.setOnClickListener(new View.OnClickListener() {

            //go to Personal

            @Override
            public void onClick(View v) {
                Privacy.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction3=getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.PrivSec,new SettingsFragment()).commit();

            }


        });


    }


}