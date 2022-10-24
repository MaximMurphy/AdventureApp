package com.example.adventureapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.adventureapp.R;
import com.example.adventureapp.dao.DAOAdventure;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {
    private Button deleteAdventuresButton;
    private static String TAG = "SettingsActivity";
    FirebaseAuth mAuth;
    DAOAdventure dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        deleteAdventuresButton = findViewById(R.id.deleteAdventuresButton);
        mAuth = FirebaseAuth.getInstance();
        dao = new DAOAdventure();
        deleteAdventuresButton.setOnClickListener(v -> {
            dao.deleteAll();
        });
    }
}