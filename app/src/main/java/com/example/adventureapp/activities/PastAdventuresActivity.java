package com.example.adventureapp.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adventureapp.AdventureAdapter;
import com.example.adventureapp.R;
import com.example.adventureapp.dao.DAOAdventure;
import com.example.adventureapp.model.Adventure;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PastAdventuresActivity extends AppCompatActivity {
    private static final String TAG = "PastAdventuresActivity";
    private DAOAdventure dao = new DAOAdventure();
    private RecyclerView recyclerView;

    AdventureAdapter adapter;
    ArrayList<Adventure> adventureArrayList;
    DatabaseReference ref;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_adventures);
        recyclerView = findViewById(R.id.pastAdventureFragmentContainer);
        adventureArrayList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("Adventure");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdventureAdapter(this, adventureArrayList);

        recyclerView.setAdapter(adapter);

        dao.getAllAdventures(adapter);
    }
}