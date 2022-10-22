package com.example.adventureapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.example.adventureapp.R;
import com.example.adventureapp.fragments.AdventureDisplayFragment;
import com.example.adventureapp.fragments.PastAdventureFragment;

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
        AdventureDisplayFragment fm3 = new AdventureDisplayFragment();
        fragmentTransaction.add(R.id.pastAdventureFragmentContainer, (Fragment) fm2, "HELLO");
//        fragmentTransaction.attach(fm3);
        fragmentTransaction.add(R.id.pastAdventureFragmentContainer, (Fragment) fm3, "HELLO2");
        fragmentTransaction.commit();
    }
}