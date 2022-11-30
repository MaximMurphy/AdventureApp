package com.example.adventureapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adventureapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class PhotoDisplayActivity extends AppCompatActivity {

    private TextView taskName;
    private ImageView photo;
    private String id, name;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_display);

        id = getIntent().getStringExtra("ADVENTURE_ID");
        name = getIntent().getStringExtra("TASK_NAME");

        photo = findViewById(R.id.photo);

        taskName = findViewById(R.id.adventureName);
        taskName.setText(name);

        storageReference = FirebaseStorage.getInstance().getReference().child(id + "/" + taskName.getText().toString());
        try {
            final File localFile = File.createTempFile(taskName.getText().toString(), "jpg");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(PhotoDisplayActivity.this, getString(R.string.photo_retrieved), Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            photo.setImageBitmap(bitmap);
                        }
                    }) .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PhotoDisplayActivity.this, getString(R.string.error_occured) + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}