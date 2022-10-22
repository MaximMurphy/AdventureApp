package com.example.adventureapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.adventureapp.R;

public class AdventureActivity extends AppCompatActivity {
    private static final String TAG = "AdventureActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, TAG + " onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);
    }
}