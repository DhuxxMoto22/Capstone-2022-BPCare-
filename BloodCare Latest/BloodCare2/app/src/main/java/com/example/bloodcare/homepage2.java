package com.example.bloodcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bloodcare.databinding.ActivityHomepage2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class homepage2 extends AppCompatActivity {
    TextView mmsprofusername;
    FirebaseAuth fAuthss;
    FirebaseFirestore fStoress;
    String userIdss;

    private View profile;

    ActivityHomepage2Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomepage2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home :
                    replaceFragment(new HomeFragment());
                    break ;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break ;
                case R.id.clinic:
                    replaceFragment(new ClinicFragment());
                    break ;
                case R.id.history:
                    replaceFragment(new HistoryFragment());
                    break ;
            }
            return true ;
        });
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(v -> {
            Intent intent=new Intent(homepage2.this, Testing.class);
            startActivity(intent);
        });
    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }
    }