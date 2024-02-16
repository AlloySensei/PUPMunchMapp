package com.example.ui_pupmunchmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginPage extends AppCompatActivity {
    Button buttonLogin;
    TextView signUp;
    EditText editTextEmail,editTextPassword;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User is already logged in, check their role and redirect accordingly
            checkUserRole(currentUser.getUid());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        signUp = findViewById(R.id.txtViewSignUp);
        editTextEmail = findViewById(R.id.edUsername);
        editTextPassword = findViewById(R.id.edPassword);
        buttonLogin = findViewById(R.id.buttonLogin);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            String email, password;

            @Override
            public void onClick(View v) {
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                hideKeyboard();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginPage.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Check user role after successful login
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    checkUserRole(user.getUid());
                                } else {
                                    // Handle authentication failure
                                    Toast.makeText(LoginPage.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, PreLogin.class));
            }
        });



    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
                            // Redirect customer to customer dashboard
                            startActivity(new Intent(LoginPage.this, MainNavigationC.class));
                        } else if ("seller".equals(role)) {
                            // Redirect seller to seller dashboard
                            startActivity(new Intent(LoginPage.this, MainActivity.class));
                        } else {
                            // Unknown role or role not found
                            Toast.makeText(LoginPage.this, "Unknown user role", Toast.LENGTH_SHORT).show();
                        }
                        finish(); // Finish the LoginActivity to prevent going back to it
                    } else {
                        // Document doesn't exist
                        Toast.makeText(LoginPage.this, "User document not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Error getting document
                    Toast.makeText(LoginPage.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}