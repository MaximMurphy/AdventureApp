package com.example.adventureapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adventureapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailAddressEt, passwordEt, confirmPasswordEt;
    private Button signUpButton;
    private TextView alreadyUserLogInTV;
    private ProgressBar loadingPB;
    private final String TAG = "REGISTER_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        emailAddressEt = findViewById(R.id.emailAddressET);
        passwordEt = findViewById(R.id.passwordET);
        confirmPasswordEt = findViewById(R.id.confirmPasswordET);
        signUpButton = findViewById(R.id.signUpButton);
        alreadyUserLogInTV = findViewById(R.id.alreadyUserLogInTV);
        loadingPB = findViewById(R.id.loadingPB);
        alreadyUserLogInTV.setOnClickListener(v -> {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
        });
        signUpButton.setOnClickListener(v -> {
            loadingPB.setVisibility(View.VISIBLE);
            String email = emailAddressEt.getText().toString();
            String password = passwordEt.getText().toString();
            String confirmPassword = confirmPasswordEt.getText().toString();

            if(!password.equals(confirmPassword)){
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confirmPassword)){
                Toast.makeText(this, "Please enter your information", Toast.LENGTH_SHORT).show();
            } else {
                createAccount(email, password);
            }
        });
    }

    private void createAccount(String email, String password){
        Log.d(TAG, "createAccount");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    loadingPB.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        Intent i = new Intent(this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                    // ...
                });
    }
}