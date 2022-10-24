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
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<Void> add(Adventure adv){
        return dbReference.push().setValue(adv);
    }


    //TODO: MAKE THIS NOT DELETE ALL ENTRIES WITH THE SAME NAME
    //Maybe add a unique identifier to the adventure object?
    public void delete(String adventureName){
        dbReference.orderByChild("user").equalTo(mAuth.getCurrentUser().getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot adventure : snapshot.getChildren()) {
                    HashMap<String, String> map = (HashMap<String, String>) adventure.getValue();

                    for(Map.Entry<String, String> entry : map.entrySet()){
                        if(entry.getKey().equals("adventureName") && entry.getValue().equals(adventureName)){
                            adventure.getRef().removeValue();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled", error.toException());
            }
        });
    }

    public void getAllAdventures(AdventureAdapter adapter){
        adventureArrayList = new ArrayList<>();
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
