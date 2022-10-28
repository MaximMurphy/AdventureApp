package com.example.adventureapp.dao;

import com.example.adventureapp.model.Tasks;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOTasks {

    private DatabaseReference dbReference;
    private FirebaseAuth mAuth;
    private static String TAG = "DAOTasks";

    public DAOTasks(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(Tasks.class.getSimpleName());
    }

    public Task<Void> add(Tasks tasks){
        return dbReference.push().setValue(tasks);
    }

}
