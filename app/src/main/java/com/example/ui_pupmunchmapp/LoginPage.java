package com.example.ui_pupmunchmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
    Button buttonLogin;
    TextView signUp;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        signUp = findViewById(R.id.txtViewSignUp);
        username = findViewById(R.id.edPassword);
        password = findViewById(R.id.edUsername);
        buttonLogin = findViewById(R.id.buttonLogin);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, SignupActivity.class));
            }
        });



    }
}