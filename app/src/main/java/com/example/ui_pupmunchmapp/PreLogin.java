package com.example.ui_pupmunchmapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PreLogin extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

        mAuth = FirebaseAuth.getInstance();

        Button customerButton = findViewById(R.id.customer);
        Button sellerButton = findViewById(R.id.seller);

        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PreLogin.this, SignupActivity.class));
            }
        });

        sellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PreLogin.this, SignupS.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User is already logged in, check their role and redirect accordingly
            checkUserRole(currentUser.getUid());
        }
    }

    private void checkUserRole(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("users").document(userId);

        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String role = document.getString("role");
                        if ("customer".equals(role)) {
                            startActivity(new Intent(PreLogin.this, MainNavigationC.class));
                            finish();
                        } else if ("seller".equals(role)) {
                            startActivity(new Intent(PreLogin.this, MainActivity.class));
                            finish();
                        } else {
                            // Unknown role or role not found
                            showToast("Unknown user role");
                        }
                        finish(); // Finish the PreLogin activity to prevent going back to it
                    } else {
                        // Document doesn't exist
                        showToast("User document not found");
                    }
                } else {
                    // Error getting document
                    showToast("Error: " + task.getException().getMessage());
                }
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(PreLogin.this, message, Toast.LENGTH_SHORT).show();
    }
}