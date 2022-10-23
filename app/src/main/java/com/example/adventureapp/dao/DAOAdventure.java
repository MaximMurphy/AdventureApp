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

public class DAOAdventure {

    private DatabaseReference dbReference;
    private FirebaseAuth mAuth;
    private static String TAG = "DAOAdventure";

    public DAOAdventure(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(Adventure.class.getSimpleName());
    }

    public Task<Void> add(Adventure adv){
        return dbReference.push().setValue(adv);
    }

    public ArrayList<Adventure> getAllAdventures(){
        ArrayList<Adventure> list = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();

//        dbReference.child("Adventure").orderByChild("user").equalTo(mAuth.getCurrentUser().getEmail()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    for (DataSnapshot userSnapshot : snapshot.getChildren()){
//                        list.add(new Adventure(mAuth.getCurrentUser().getEmail(), userSnapshot.getRef().getKey()));
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e(TAG, "onCancelled", error.toException());
//            }
//        });

        dbReference.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });


        return list;
    }
}
