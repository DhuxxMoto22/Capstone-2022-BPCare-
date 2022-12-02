package com.example.bloodcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodcare.cfinder.ClinicFragment;
import com.example.bloodcare.databinding.ActivityHomepage2Binding;

public class homepage2 extends AppCompatActivity {

    private View profile;
    private View clinic;

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
                case R.id.settings:
                    replaceFragment(new SettingsFragment());
                    break ;
            }
            return true ;
        });
        clinic = findViewById(R.id.clinic);
        clinic.setOnClickListener(v -> {
            Intent intent=new Intent(homepage2.this, ClinicActivity.class);
            startActivity(intent);
        });

        profile = findViewById(R.id.profile);
        profile.setOnClickListener(v -> {
            Intent intent=new Intent(homepage2.this, ProfileAct.class);
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