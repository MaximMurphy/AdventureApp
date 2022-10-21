package com.example.adventureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailAddressEt, passwordEt;
    private Button signInButton;
    private TextView notUserTV;
    private static final String TAG = "LOGINACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        emailAddressEt = findViewById(R.id.emailAddressET);
        passwordEt = findViewById(R.id.passwordET);
        signInButton = findViewById(R.id.signInButton);
        notUserTV = findViewById(R.id.notUserTV);
        notUserTV.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });
        signInButton.setOnClickListener(v -> {
            String email = emailAddressEt.getText().toString();
            String password = passwordEt.getText().toString();

            if(email == null || password == null || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(this, "Please enter your information", Toast.LENGTH_SHORT).show();
            } else {
                signIn(email, password);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //Placeholder function
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null){
            Intent i = new Intent(this, HomePageActivity.class);
            startActivity(i);
            this.finish();
        }
//        if (user != null) {
//            status.setText(getString(R.string.emailpassword_status_fmt,
//                    user.getEmail(), user.isEmailVerified()));
//            detail.setText(getString(R.string.firebase_status_fmt, user.getUid()));
//
//            emailPasswordButtons.setVisibility(View.GONE);
//            emailPasswordFields.setVisibility(View.GONE);
//            signedInButtons.setVisibility(View.VISIBLE);
//
//            if (user.isEmailVerified()) {
//                verifyEmailButton.setVisibility(View.GONE);
//            } else {
//                verifyEmailButton.setVisibility(View.VISIBLE);
//            }
//        } else {
//            status.setText(R.string.signed_out);
//            detail.setText(null);
//
//            emailPasswordButtons.setVisibility(View.VISIBLE);
//            emailPasswordFields.setVisibility(View.VISIBLE);
//            signedInButtons.setVisibility(View.GONE);
//        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                    // ...
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}