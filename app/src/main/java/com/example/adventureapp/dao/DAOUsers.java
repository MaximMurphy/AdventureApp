package com.example.adventureapp.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.adventureapp.model.Adventure;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DAOUsers {

    private DatabaseReference dbReference;
    private FirebaseAuth mAuth;
    private static String TAG = "DAOUsers";

    public DAOUsers(){
//        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference("Users");
    }

    public Task<Void> add(String user){
        return dbReference.push().setValue(user);
    }

    public ArrayList<Adventure> getAllAdventures(){
        ArrayList<Adventure> adventureArrayList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();

        dbReference.child("Adventure").equalTo(mAuth.getCurrentUser().getEmail()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> map = new HashMap<>();
                for(DataSnapshot s : snapshot.getChildren()){
                    Log.d(TAG, s.toString());
                    String user = null, adventureName = null , taskOne = null, taskTwo = null, taskThree = null;
                    for(Map.Entry<String, String> entry : ((HashMap<String, String>)s.getValue()).entrySet()){

                        if(entry.getKey().equals("adventureName")){
                            adventureName = entry.getValue();
                        } else if(entry.getKey().equals("user")){
                            user = entry.getValue();
                        } else if (entry.getKey().equals("taskOne")) {
                            taskOne = entry.getValue();
                        } else if (entry.getKey().equals("taskTwo")) {
                            taskTwo = entry.getValue();
                        } else if (entry.getKey().equals("taskThree")) {
                            taskThree = entry.getValue();
                        }
                    }
                    Log.d(TAG, user + " " + adventureName);
                    adventureArrayList.add(new Adventure(user, adventureName, taskOne, taskTwo, taskThree));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.d(TAG, adventureArrayList.toString());
        return adventureArrayList;
    }
}
