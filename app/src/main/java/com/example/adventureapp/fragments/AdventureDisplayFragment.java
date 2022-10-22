package com.example.adventureapp.fragments;

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

import com.example.adventureapp.model.Adventure;
import com.example.adventureapp.dao.DAOAdventure;
import com.example.adventureapp.R;
import com.google.firebase.auth.FirebaseAuth;


public class AdventureDisplayFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "AdventureDisplayFragment";
    private Button task1Button, task2Button, task3Button, finishButton;
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
        task1Button = v.findViewById(R.id.task1Button);
        task2Button = v.findViewById(R.id.task2Button);
        task3Button = v.findViewById(R.id.task3Button);
        finishButton = v.findViewById(R.id.finishButton);
        mAuth = FirebaseAuth.getInstance();

        dao = new DAOAdventure();

        task1Button.setOnClickListener(this);
        task2Button.setOnClickListener(this);
        task3Button.setOnClickListener(this);
        finishButton.setOnClickListener(this);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, TAG + " onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.task1Button:
                break;
            case R.id.task2Button:
                break;
            case R.id.task3Button:
                break;
            case R.id.finishButton:
                Log.d(TAG, TAG + " finish onclick");

                String adventureName = adventureNameET.getText().toString();
                Adventure a = new Adventure(mAuth.getCurrentUser().getEmail(), adventureName);
                if(adventureName == null || TextUtils.isEmpty(adventureName)){
                    Toast.makeText(getActivity(), "Please enter adventure name", Toast.LENGTH_SHORT).show();
                } else {
                    dao.add(a).addOnSuccessListener(suc -> {
                        Log.d(TAG, TAG + " finish db interaction");
                    }).addOnFailureListener(err -> {
                        Log.e(TAG, TAG + " failure on db interaction");
                    });
                }
        }
    }
}