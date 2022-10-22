package com.example.adventureapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOAdventure {

    private DatabaseReference dbReference;

    public DAOAdventure(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(Adventure.class.getSimpleName());
    }

    public Task<Void> add(Adventure adv){
//        if(adv == null){
//            throw exception;
//        }
        return dbReference.push().setValue(adv);
    }

    public Task<DataSnapshot> get(){
//        return dbReference.get();
        return null;
    }
}
