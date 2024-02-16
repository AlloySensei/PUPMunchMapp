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

public class SignupS extends AppCompatActivity {
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editTextPasswordConfirm.setTextColor(getResources().getColor(android.R.color.black));
            }
        });
        // Assuming mAuth is FirebaseAuth instance and signupButton is the button for sign-up
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign-up successful, set role to "seller"
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String uid = user.getUid();

                                    Map<String, Object> userData = new HashMap<>();
                                    userData.put("email", email);
                                    userData.put("role", "seller"); // Set role to "seller"

                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection("users").document(uid)
                                            .set(userData)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(SignupS.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                                    // Redirect user to seller dashboard or any other appropriate activity
                                                    startActivity(new Intent(SignupS.this, OrdersS.class));
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
                                } else {
                                    // If sign-up fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignupS.this, "Sign up failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



    }
}
