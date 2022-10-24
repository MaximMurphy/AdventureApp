package com.example.adventureapp.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.adventureapp.AdventureAdapter;
import com.example.adventureapp.model.Adventure;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DAOAdventure {

    private DatabaseReference dbReference;
    private FirebaseAuth mAuth;
    private ArrayList<Adventure> adventureArrayList;
    private static String TAG = "DAOAdventure";

    public DAOAdventure(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(Adventure.class.getSimpleName());
        adventureArrayList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<Void> add(Adventure adv){
        return dbReference.push().setValue(adv);
    }

    public void getAllAdventures(AdventureAdapter adapter){

        dbReference.orderByChild("user").equalTo(mAuth.getCurrentUser().getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    Log.d(TAG, s.toString());
                    String user = null, adventureName = null;
                    for (Map.Entry<String, String> entry : ((HashMap<String, String>) s.getValue()).entrySet()) {
                        if (entry.getKey().equals("adventureName")) {
                            adventureName = entry.getValue();
                        } else if (entry.getKey().equals("user")) {
                            user = entry.getValue();
                        }
                    }
                    Log.d(TAG, user + " " + adventureName);
                    adventureArrayList.add(new Adventure(user, adventureName));
                    Log.d(TAG, adventureArrayList.toString());
                }
                adapter.setAdventuresList(adventureArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
