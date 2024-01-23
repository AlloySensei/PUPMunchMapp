package com.example.ui_pupmunchmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {
    Button buttonReg;
    EditText editTextName, editTextEmail, editTextPassword, editTextPasswordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editTextName = findViewById(R.id.inputUsername);
        editTextEmail = findViewById(R.id.inputEmail);
        editTextPassword = findViewById(R.id.inputPassword);
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


            }
        });


    }
}