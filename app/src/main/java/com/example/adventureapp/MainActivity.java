package com.example.adventureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button settingsButton;
    private Button startAdventureButton;
    private Button pastAdventuresButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAdventureButton = (Button) findViewById(R.id.startAdventureButton);
        startAdventureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openAdventureActivity();
            }
        });

        pastAdventuresButton = (Button) findViewById(R.id.pastAdventuresButton);
        pastAdventuresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openPastAdventuresActivity();
            }
        });

        settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openSettingsActivity();
            }
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
    }

    public void onDestroy() {

        super.onDestroy();

    }
}