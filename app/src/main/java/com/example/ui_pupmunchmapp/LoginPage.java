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


public class LoginPage extends AppCompatActivity {
    Button buttonLogin;
    TextView signUp;
    EditText editTextEmail,editTextPassword;
    FirebaseAuth mAuth;


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
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(getApplicationContext(),MainNavigationC.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
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
                startActivity(new Intent(LoginPage.this, SignupActivity.class));
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
}