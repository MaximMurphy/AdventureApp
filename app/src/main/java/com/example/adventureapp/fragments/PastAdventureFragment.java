package com.example.adventureapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.adventureapp.R;
import com.example.adventureapp.model.Adventure;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PastAdventureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastAdventureFragment extends Fragment {
    private static String TAG = "PastAdventureFragment";
    private TextView adventureName;
    private Button editButton, deleteButton;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        adventureName = getView().findViewById(R.id.adventureNameTextView);
        editButton = getView().findViewById(R.id.editButton);
        deleteButton = getView().findViewById(R.id.deleteButton);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            adventureName.setText(mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, TAG + " on create view");
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_past_adventure, container, false);

        if (container != null) {
            container.removeAllViews();
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_adventure, container, false);
    }
}