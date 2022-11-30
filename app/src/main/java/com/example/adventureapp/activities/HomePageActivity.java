package com.example.adventureapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.adventureapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button settingsButton, startAdventureButton, pastAdventuresButton, signOutButton;
    private static final String TAG = "HomePageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, TAG + " - onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth = FirebaseAuth.getInstance();
        signOutButton = findViewById(R.id.signOutButton);
        startAdventureButton = findViewById(R.id.startAdventureButton);
        pastAdventuresButton = findViewById(R.id.pastAdventuresButton);
        settingsButton = findViewById(R.id.settingsButton);

        setOnClickListeners();
    }

    public void setOnClickListeners(){
        Log.d(TAG, " setClickListeners");
        startAdventureButton.setOnClickListener(v -> {
            Log.i(TAG, TAG + " - startAdventureButton onCreate");
            openAdventureActivity();
        });

        pastAdventuresButton.setOnClickListener(v -> {
            Log.i(TAG, TAG + " - pastAdventureButton onCreate");
            openPastAdventuresActivity();
        });

        settingsButton.setOnClickListener(v -> {
            Log.i(TAG, TAG + " - settingsButton onCreate");
            openSettingsActivity();
        });

        signOutButton.setOnClickListener(v -> {
            Log.i(TAG, TAG + " - settingsButton onCreate");
            signOut();
        });
    }

    public void openAdventureActivity(){
        Intent intent = new Intent (this, AdventureActivity.class);
        startActivity(intent);
    }

    public void openPastAdventuresActivity(){
        Intent intent = new Intent (this, PastAdventuresActivity.class);
        startActivity(intent);
    }

    public void openSettingsActivity(){
        Intent intent = new Intent (this, SettingsActivity.class);
        startActivity(intent);
        Log.d(TAG, "after settings activity");
//        this.recreate();
    }

    public void signOut() {
        mAuth.signOut();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void onDestroy() {
        Log.d(TAG, " onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        boolean s1 = sh.getBoolean("didLanguageChange", false);
        Log.d(TAG, String.valueOf(s1));
        if(s1){
            recreate();
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("didLanguageChange", false);
            editor.commit();
        }
    }
}