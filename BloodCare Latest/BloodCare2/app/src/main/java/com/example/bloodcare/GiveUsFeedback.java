package com.example.bloodcare;

import android.media.Rating;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bloodcare.databinding.ActivityGiveUsFeedbackBinding;

public class GiveUsFeedback extends AppCompatActivity {
TextView Tvfeedback;
RatingBar Ratingb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_us_feedback);

        Tvfeedback = findViewById(R.id.RText);
        Ratingb = findViewById(R.id.RatingB);
        Ratingb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==0 || rating==0.5)
                {
                    Tvfeedback.setText("Very Dissatisfied");
                }
                else if (rating==1 || rating==1.5)
                {
                    Tvfeedback.setText("Dissatisfied");
                }
                else if (rating==2 || rating==2.5 )
                {
                    Tvfeedback.setText("Good");
                }
                else if (rating==3 || rating==3.5)
                {
                    Tvfeedback.setText("Very Good");
                }
                else if (rating==4 || rating==4.5)
                {
                    Tvfeedback.setText("Satisfied");
                }
                else
                {
                    Tvfeedback.setText("Very Satisfied");
                }
            }

        });
    }
}
