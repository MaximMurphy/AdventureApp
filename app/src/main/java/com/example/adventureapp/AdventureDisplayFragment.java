package com.example.adventureapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AdventureDisplayFragment extends Fragment {
    private static final String TAG = "AdventureDisplayFragment";

    public AdventureDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreateView");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adventure_display, container, false);
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, TAG + " onDestroyView");
        super.onDestroyView();
    }
}