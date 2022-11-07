package com.example.adventureapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adventureapp.activities.HomePageActivity;
import com.example.adventureapp.activities.TaskPhotoActivity;
import com.example.adventureapp.model.Adventure;
import com.example.adventureapp.dao.DAOAdventure;
import com.example.adventureapp.R;
import com.example.adventureapp.model.Tasks;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class AdventureDisplayFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "AdventureDisplayFrag";
    private Button task1Button, task2Button, task3Button, finishButton;
    private Tasks adventureTasks;
    private List<String> randomThreeTasks;
    private EditText adventureNameET;
    private DAOAdventure dao;
    private FirebaseAuth mAuth;
    public AdventureDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreateView");
        View v = inflater.inflate(R.layout.fragment_adventure_display, container, false);
        adventureNameET = v.findViewById(R.id.adventureNameEditText);
        adventureTasks = new Tasks();
        randomThreeTasks = adventureTasks.getThreeRandomTasks();
        task1Button = v.findViewById(R.id.task1Button);
        task1Button.setText(randomThreeTasks.get(0));
        task2Button = v.findViewById(R.id.task2Button);
        task2Button.setText(randomThreeTasks.get(1));
        task3Button = v.findViewById(R.id.task3Button);
        task3Button.setText(randomThreeTasks.get(2));
        finishButton = v.findViewById(R.id.finishButton);
        mAuth = FirebaseAuth.getInstance();

        dao = new DAOAdventure();

        task1Button.setOnClickListener(u -> {
            Log.i(TAG, TAG + " - taskPhotoButton onCreate");
            openTaskPhoto(task1Button.getText().toString());
        });
        task2Button.setOnClickListener(u -> {
            Log.i(TAG, TAG + " - taskPhotoButton onCreate");
            openTaskPhoto(task2Button.getText().toString());
        });
        task3Button.setOnClickListener(u -> {
            Log.i(TAG, TAG + " - taskPhotoButton onCreate");
            openTaskPhoto(task3Button.getText().toString());
        });
        finishButton.setOnClickListener(this);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, TAG + " onDestroyView");
        super.onDestroyView();
    }

    public void openTaskPhoto(String taskName){
        Intent intent = new Intent(getContext(), TaskPhotoActivity.class);
        intent.putExtra("TASK_NAME", taskName);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.finishButton:
                Log.d(TAG, TAG + " finish onclick");

                String adventureName = adventureNameET.getText().toString();
                String taskOne = task1Button.getText().toString();
                String taskTwo = task2Button.getText().toString();
                String taskThree = task3Button.getText().toString();

                Adventure a = new Adventure(mAuth.getCurrentUser().getEmail(), adventureName, taskOne, taskTwo, taskThree);
                if(adventureName == null || TextUtils.isEmpty(adventureName)){
                    Toast.makeText(getActivity(), "Please enter adventure name", Toast.LENGTH_SHORT).show();
                } else {
                    dao.add(a).addOnSuccessListener(suc -> {
                        Log.d(TAG, TAG + " finish db interaction");
                    }).addOnFailureListener(err -> {
                        Log.e(TAG, TAG + " failure on db interaction");
                    });
                }
                Intent intent = new Intent(getContext(), HomePageActivity.class);
                startActivity(intent);
        }
    }
}