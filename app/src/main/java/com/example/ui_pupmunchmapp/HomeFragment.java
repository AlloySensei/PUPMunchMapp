package com.example.ui_pupmunchmapp;

import static com.example.ui_pupmunchmapp.R.layout.sp_item;
import static com.example.ui_pupmunchmapp.R.layout.viewholder_featured;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ui_pupmunchmapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private ActivityMainBinding binding;

    FirebaseDatabase database;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    FirebaseAuth mAuth;
    TextView userName,welcome;
    Spinner priceSp,locationSp, timeSp;
    FirebaseUser user;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://pupmunchmapp-default-rtdb.asia-southeast1.firebasedatabase.app");
        user = mAuth.getCurrentUser();
        userName = view.findViewById(R.id.userName);
        welcome = view.findViewById(R.id.welcome);
        priceSp = view.findViewById(R.id.priceSp);
        timeSp = view.findViewById(R.id.timeSp);
        locationSp = view.findViewById(R.id.locationSp);
        if(user == null){
            Intent intent = new Intent(getContext(),LoginPage.class);
            startActivity(intent);
        }else {
            userName.setText(user.getEmail());

        }
        initLocation();
        initTime();
        initPrice();
        return view;
    }

    private void initLocation() {
        DatabaseReference myRef = database.getReference("Location");
        ArrayList<Location> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Location.class));
                    }
                    Log.d("FirebaseData", "Data loaded successfully: " + list.size() + " items");
                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(requireContext(), sp_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    locationSp.setAdapter(adapter);
                } else {
                    Log.d("FirebaseData", "No data found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseData", "Data loading canceled with error: " + error.getMessage());
            }
        });

    }

    private void initTime() {
        DatabaseReference myRef = database.getReference("Time");
        ArrayList<Time> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Time.class));
                    }
                    Log.d("FirebaseData", "Data loaded successfully: " + list.size() + " items");
                    ArrayAdapter<Time> adapter = new ArrayAdapter<>(requireContext(), sp_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    timeSp.setAdapter(adapter);
                } else {
                    Log.d("FirebaseData", "No data found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseData", "Data loading canceled with error: " + error.getMessage());
            }
        });

    }
    private void initPrice() {
        DatabaseReference myRef = database.getReference("Price");
        ArrayList<Price> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Price.class));
                    }
                    Log.d("FirebaseData", "Data loaded successfully: " + list.size() + " items");
                    ArrayAdapter<Price> adapter = new ArrayAdapter<>(requireContext(), sp_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    priceSp.setAdapter(adapter);
                } else {
                    Log.d("FirebaseData", "No data found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseData", "Data loading canceled with error: " + error.getMessage());
            }
        });

    }
}