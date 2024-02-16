package com.example.ui_pupmunchmapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    Button buttonReg;
    EditText editTextName, editTextEmail, editTextPassword, editTextPasswordConfirm;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextName = findViewById(R.id.inputUsername);
        editTextEmail = findViewById(R.id.inputEmail);
        editTextPassword = findViewById(R.id.inputPassword);
        mAuth = FirebaseAuth.getInstance();
        editTextPasswordConfirm = findViewById(R.id.inputConfirmPassword);
        buttonReg = findViewById(R.id.buttonReg);

        editTextPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                editTextPasswordConfirm.setTextColor(getResources().getColor(android.R.color.black));
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editTextEmail.getText().toString();
                final String password = editTextPassword.getText().toString();
                final String username = editTextName.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        String uid = user.getUid();

                                        Map<String, Object> userData = new HashMap<>();
                                        userData.put("email", email);
                                        userData.put("username", username); // Set username
                                        userData.put("role", "customer"); // Set role

                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        db.collection("users").document(uid)
                                                .set(userData)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d(TAG, "DocumentSnapshot successfully written!");
                                                        Intent intent = new Intent(SignupActivity.this, MainNavigationC.class);
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error writing document", e);
                                                        // Handle failure
                                                    }
                                                });
                                    }
                                } else {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "Sign up failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
