package com.example.ui_pupmunchmapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_pupmunchmapp.databinding.ActivityMainNavigationCBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrdersFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    TextView emptyTxt,totalFeeTxt,totalTxt;
    ScrollView scrollviewCart;
    RecyclerView cartView;
    Button orderBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        managmentCart = new ManagmentCart(getContext());
        emptyTxt = view.findViewById(R.id.emptyTxt);
        scrollviewCart = view.findViewById(R.id.scrollviewCart);
        cartView = view.findViewById(R.id.cartView);
        totalFeeTxt = view.findViewById(R.id.totalFeeTxt);
        totalTxt = view.findViewById(R.id.totalTxt);
        orderBtn = view.findViewById(R.id.button2);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });


        calculateCart();
        initList();
        return view;
    }

    private void initList() {
        if(managmentCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollviewCart.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollviewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        cartView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managmentCart.getListCart(), getContext(), new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });
        cartView.setAdapter(adapter);

    }

    private void calculateCart() {
        double itemTotal = managmentCart.getTotalFee();
        totalFeeTxt.setText("₱" + itemTotal);
        totalTxt.setText("₱" + itemTotal);
    }

    private void placeOrder() {
        // Get the current user's ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Get a reference to the Firestore database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get the username from Firestore
        db.collection("users").document(userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Retrieve the username from the document snapshot
                            String userName = documentSnapshot.getString("username");

                            // Create a new order object with the necessary details
                            Map<String, Object> orderData = new HashMap<>();
                            orderData.put("userName", userName); // Use the username instead of UID
                            orderData.put("items", managmentCart.getListCart()); // Assuming getListCart() returns a list of items in the cart
                            orderData.put("totalPrice", managmentCart.getTotalFee()); // Assuming getTotalFee() returns the total price of items in the cart

                            // Add the order to the "Orders" collection in Firestore
                            db.collection("Orders")
                                    .add(orderData)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            // Order placed successfully
                                            Toast.makeText(getContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show();

                                            managmentCart.clearCart();
                                            // Update the UI
                                            initList();
                                            calculateCart();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Failed to place the order
                                            Toast.makeText(getContext(), "Failed to place order: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            // Document doesn't exist
                            Toast.makeText(getContext(), "User document not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error getting document
                        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }




}