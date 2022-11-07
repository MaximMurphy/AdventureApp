package com.example.adventureapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.adventureapp.R;

public class TaskPhotoActivity extends AppCompatActivity {
    private Button takePhotoButton;
    private TextView task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_photo);

        String taskName = getIntent().getStringExtra("TASK_NAME");

        task = findViewById(R.id.taskDescription);
        task.setText(taskName);

        takePhotoButton = findViewById(R.id.takePhotoButton);

        setOnClickListeners();
    }

    public void setOnClickListeners() {
        takePhotoButton.setOnClickListener(v -> {
            //open camera
        });
    }


}

