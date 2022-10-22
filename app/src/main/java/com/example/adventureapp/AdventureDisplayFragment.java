package com.example.adventureapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AdventureDisplayFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "AdventureDisplayFragment";
    private Button task1Button, task2Button, task3Button, finishButton;
    private DAOAdventure dao;
    public AdventureDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreateView");
        View v = inflater.inflate(R.layout.fragment_adventure_display, container, false);
        task1Button = (Button) v.findViewById(R.id.task1Button);
        task2Button = (Button) v.findViewById(R.id.task2Button);
        task3Button = (Button) v.findViewById(R.id.task3Button);
        finishButton = (Button) v.findViewById(R.id.finishButton);
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
                Adventure a = new Adventure("nolan", "cbus");
                dao.add(a).addOnSuccessListener(suc -> {
                    Log.d(TAG, TAG + " finish db interaction");
                }).addOnFailureListener(err -> {
                    Log.e(TAG, TAG + " failure on db interaction");
                });
        }
    }
}