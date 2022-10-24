package com.example.adventureapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adventureapp.AdventureAdapter;
import com.example.adventureapp.R;
import com.example.adventureapp.dao.DAOAdventure;
import com.example.adventureapp.fragments.PastAdventureFragment;
import com.example.adventureapp.model.Adventure;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        AdventureAdapter.ClickListener editListener = new AdventureAdapter.ClickListener() {
            @Override
            public void onItemClicked(Adventure adventure) {
                Intent intent = new Intent(getBaseContext(), EditAdventureActivity.class);
                startActivity(intent);
            }
        };

        AdventureAdapter.ClickListener deleteListener = new AdventureAdapter.ClickListener() {
            @Override
            public void onItemClicked(Adventure adventure) {
                dao.delete(adventure.getAdventureName());
            }
        };

//        adapter = new AdventureAdapter(this, adventureArrayList, editListener, deleteListener);
        adapter = new AdventureAdapter(this, adventureArrayList);

        recyclerView.setAdapter(adapter);

        dao.getAllAdventures(adapter);
    }
}