package com.example.ui_pupmunchmapp;

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
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        buttonReg = findViewById(R.id.button2);

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
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, email, password, passwordConfirm;

                name = String.valueOf(editTextName.getText());
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                passwordConfirm = String.valueOf(editTextPasswordConfirm.getText());



                if(name.isEmpty()){
                    editTextName.setError("This field cannot be empty");
                }else if(email.isEmpty()){
                    editTextEmail.setError("This field cannot be empty");
                }
                if (password.equals(passwordConfirm)) {
                    editTextPasswordConfirm.setError(null);
                } else {
                    editTextPasswordConfirm.setError("Passwords do not match");
                    editTextPasswordConfirm.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    // You can navigate to another activity or perform other actions here
                                    Toast.makeText(SignupActivity.this, "Account Created",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.e("AuthenticationFailed", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });


    }
}