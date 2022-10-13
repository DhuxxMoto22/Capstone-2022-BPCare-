package com.example.bloodcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bloodcare.databinding.ActivityHomepage2Binding;
import com.example.bloodcare.databinding.ActivityMainBinding;

public class homepage2 extends AppCompatActivity {

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
    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }
    }