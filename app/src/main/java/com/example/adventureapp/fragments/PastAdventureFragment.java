package com.example.adventureapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adventureapp.R;
import com.example.adventureapp.model.Adventure;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PastAdventureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastAdventureFragment extends Fragment implements View.OnClickListener{
    private static String TAG = "PastAdventureFragment";
    private TextView adventureName;
    private Button editButton, deleteButton;

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public PastAdventureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PastAdventureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastAdventureFragment newInstance(String param1) {
        PastAdventureFragment fragment = new PastAdventureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " onCreate");
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, TAG + " on create view");

        View v = inflater.inflate(R.layout.fragment_past_adventure, container, false);

        if (container != null) {
            container.removeAllViews();
        }

        adventureName = v.findViewById(R.id.adventureNameTextView);
        editButton = v.findViewById(R.id.editButton);
        deleteButton = v.findViewById(R.id.deleteButton);
        adventureName.setText(mParam1);

        editButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.editButton:
                Log.d(TAG, TAG + " edit onclick");
                break;
            case R.id.deleteButton:
                Log.d(TAG, TAG + " delete onclick");

//                String adventureName = adventureNameET.getText().toString();
//                Adventure a = new Adventure(mAuth.getCurrentUser().getEmail(), adventureName);
//                if(adventureName == null || TextUtils.isEmpty(adventureName)){
//                    Toast.makeText(getActivity(), "Please enter adventure name", Toast.LENGTH_SHORT).show();
//                } else {
//                    dao.add(a).addOnSuccessListener(suc -> {
//                        Log.d(TAG, TAG + " finish db interaction");
//                    }).addOnFailureListener(err -> {
//                        Log.e(TAG, TAG + " failure on db interaction");
//                    });
//                }
                break;
        }
    }
}