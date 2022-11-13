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

    public Task<Void> add(String id, Adventure adv){
        return dbReference.child(id).setValue(adv);
    }

    public void deleteAll(){
        dbReference.orderByChild("user").equalTo(mAuth.getCurrentUser().getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()){
                    userSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled", error.toException());
            }
        });
    }

    public void delete(int position){
        dbReference.orderByChild("user").equalTo(mAuth.getCurrentUser().getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int counter = 0;
                for (DataSnapshot adventure : snapshot.getChildren()) {
                    if(counter == position){
                        adventure.getRef().removeValue();
                    }
                    counter++;
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
                    String id = null, user = null, adventureName = null, taskOne = null, taskTwo = null, taskThree = null;
                    for (Map.Entry<String, String> entry : ((HashMap<String, String>) s.getValue()).entrySet()) {
                        if (entry.getKey().equals("adventureName")) {
                            adventureName = entry.getValue();
                        } else if (entry.getKey().equals("id")) {
                            id = entry.getValue();
                        } else if (entry.getKey().equals("user")) {
                            user = entry.getValue();
                        }else if (entry.getKey().equals("taskOne")) {
                            taskOne = entry.getValue();
                        }else if (entry.getKey().equals("taskTwo")) {
                            taskTwo = entry.getValue();
                        }else if (entry.getKey().equals("taskThree")) {
                            taskThree = entry.getValue();
                        }

                    }
                    Log.d(TAG, user + " " + adventureName);
                    adventureArrayList.add(new Adventure(id, user, adventureName, taskOne, taskTwo, taskThree));
                    Log.d(TAG, adventureArrayList.toString());
                }
                adapter.setAdventuresList(adventureArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateAdventure(String newName, int position){
        dbReference.orderByChild("user").equalTo(mAuth.getCurrentUser().getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int counter = 0;
                for (DataSnapshot adventure : snapshot.getChildren()) {
                    if(counter == position){
                        Log.d(TAG, "set value");
                        adventure.getRef().setValue(new Adventure(adventure.child("id").getValue(String.class), mAuth.getCurrentUser().getEmail(), newName,
                                adventure.child("taskOne").getValue(String.class),
                                adventure.child("taskTwo").getValue(String.class),
                                adventure.child("taskThree").getValue(String.class)));
                    }
                    counter++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled", error.toException());
            }
        });
    }
}
