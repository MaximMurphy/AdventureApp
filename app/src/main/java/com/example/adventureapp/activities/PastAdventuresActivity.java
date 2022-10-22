package com.example.adventureapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.example.adventureapp.R;
import com.example.adventureapp.dao.DAOAdventure;
import com.example.adventureapp.fragments.AdventureDisplayFragment;
import com.example.adventureapp.fragments.PastAdventureFragment;
import com.example.adventureapp.model.Adventure;

import java.util.ArrayList;

public class PastAdventuresActivity extends AppCompatActivity {
    private static final String TAG = "PastAdventuresActivity";
    private DAOAdventure dao = new DAOAdventure();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_adventures);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        ArrayList<Adventure> list = dao.getAllAdventures();
        Log.d(TAG, list.toString());

        for (Adventure adventure : list){
            Log.d(TAG, "Adventure processed");
            PastAdventureFragment frag = new PastAdventureFragment().newInstance(adventure.getUser());

            fragmentTransaction.add(R.id.pastAdventureFragmentContainer, frag, adventure.getUser());
        }

        fragmentTransaction.commit();
    }
}