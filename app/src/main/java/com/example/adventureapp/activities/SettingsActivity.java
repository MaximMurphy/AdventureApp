package com.example.adventureapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.adventureapp.LanguageManager;
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
    private Button deleteAdventuresButton, englishButton, spanishButton;
    private static String TAG = "SettingsActivity";
    FirebaseAuth mAuth;
    DAOAdventure dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        deleteAdventuresButton = findViewById(R.id.deleteAdventuresButton);
        englishButton = findViewById(R.id.english_button);
        spanishButton = findViewById(R.id.spanish_button);
        mAuth = FirebaseAuth.getInstance();
        dao = new DAOAdventure();
        LanguageManager lang = new LanguageManager(this);
        deleteAdventuresButton.setOnClickListener(v -> {
            dao.deleteAll();
        });
        englishButton.setOnClickListener(v -> {
            String currentCode = getResources().getConfiguration().locale.toString();
            Log.d(TAG, currentCode);
            lang.updateResource("en");

            // Storing data into SharedPreferences
            SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            if(!currentCode.equals("en") && !currentCode.equals("en-US")){
                editor.putBoolean("didLanguageChange", true);
            } else {
                editor.putBoolean("didLanguageChange", false);
            }
            editor.commit();

            recreate();
        });
        spanishButton.setOnClickListener(v -> {
            String currentCode = getResources().getConfiguration().locale.toString();
            Log.d(TAG, currentCode);
            lang.updateResource("es");

            SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();

            if(!currentCode.equals("es")){
                editor.putBoolean("didLanguageChange", true);
            } else {
                editor.putBoolean("didLanguageChange", false);
            }
            editor.commit();

            recreate();
        });

    }
}