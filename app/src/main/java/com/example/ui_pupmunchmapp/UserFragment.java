package com.example.ui_pupmunchmapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class UserFragment extends Fragment {
    TextView userName,displayEmail;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user, container, false);
        userName = view.findViewById(R.id.userName);
        displayEmail = view.findViewById(R.id.displayEmail);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://pupmunchmapp-default-rtdb.asia-southeast1.firebasedatabase.app");
        user = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("users").document(user.getUid());
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String username = document.getString("username");
                        userName.setText(user.getEmail());
                    }
                }
            }
        });
        displayEmail.setText(user.getEmail());
        return view;
    }
}