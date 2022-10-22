package com.example.adventureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class PastAdventuresActivity extends AppCompatActivity {
    private static final String TAG = "PastAdventuresActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_adventures);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        PastAdventureFragment fm2 = new PastAdventureFragment();
        PastAdventureFragment fm3 = new PastAdventureFragment();
        fragmentTransaction.add(R.id.pastAdventureFragmentContainer, (Fragment) fm2, "HELLO");
        fragmentTransaction.add(R.id.pastAdventureFragmentContainer, (Fragment) fm3, "HELLO2");
        fragmentTransaction.commit();
    }
}