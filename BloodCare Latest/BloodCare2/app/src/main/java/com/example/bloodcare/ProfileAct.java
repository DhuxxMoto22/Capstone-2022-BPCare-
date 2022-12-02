package com.example.bloodcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileAct extends AppCompatActivity {
    TextView mmprofusername;
    FirebaseAuth fAuths;
    FirebaseFirestore fStores;
    String userIds;

    private Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileact);

        backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(v -> {
            Intent intent=new Intent(ProfileAct.this, homepage2.class);
            startActivity(intent);
        });

        fAuths = FirebaseAuth.getInstance();
        fStores = FirebaseFirestore.getInstance();
        mmprofusername = findViewById(R.id.profusertest);

        userIds = fAuths.getCurrentUser().getUid();

        DocumentReference documentReference = fStores.collection("User Database").document(userIds);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                mmprofusername.setText(documentSnapshot.getString("Username"));
            }
        });
    }
}