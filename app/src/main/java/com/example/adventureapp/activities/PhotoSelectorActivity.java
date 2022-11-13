package com.example.adventureapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adventureapp.R;

public class PhotoSelectorActivity extends AppCompatActivity {

    private TextView adventureName;
    private Button taskOne, taskTwo, taskThree;
    private String adventure,one, two, three, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_selector);

        id = getIntent().getStringExtra("ADVENTURE_ID");
        adventure = getIntent().getStringExtra("ADVENTURE_NAME");
        one = getIntent().getStringExtra("TASK_ONE");
        two = getIntent().getStringExtra("TASK_TWO");
        three = getIntent().getStringExtra("TASK_THREE");

        //Toast.makeText(PhotoSelectorActivity.this, "id:" + id, Toast.LENGTH_SHORT).show();

        adventureName = findViewById(R.id.adventureName);
        taskOne = findViewById(R.id.taskTwo);
        taskTwo = findViewById(R.id.taskThree);
        taskThree = findViewById(R.id.taskOne);

        adventureName.setText(adventure);
        taskOne.setText(one);
        taskTwo.setText(two);
        taskThree.setText(three);

        setOnClickListeners();
    }

    public void setOnClickListeners() {
        taskOne.setOnClickListener(v -> {
            Intent intent = new Intent(this, PhotoDisplayActivity.class);
            intent.putExtra("TASK_NAME", taskOne.getText().toString());
            intent.putExtra("ADVENTURE_ID", id);
            startActivity(intent);
        });
        taskTwo.setOnClickListener(v -> {
            Intent intent = new Intent(this, PhotoDisplayActivity.class);
            intent.putExtra("TASK_NAME", taskTwo.getText().toString());
            intent.putExtra("ADVENTURE_ID", id);
            startActivity(intent);
        });
        taskThree.setOnClickListener(v -> {
            Intent intent = new Intent(this, PhotoDisplayActivity.class);
            intent.putExtra("TASK_NAME", taskThree.getText().toString());
            intent.putExtra("ADVENTURE_ID", id);
            startActivity(intent);
        });

    }
}