package com.example.ui_pupmunchmapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrdersS extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_s, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Orders")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<OrderItem> orderItemList = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            // Extract userName
                            String userName = documentSnapshot.getString("userName");

                            // Extract ordered items
                            List<Map<String, Object>> orderedItems = (List<Map<String, Object>>) documentSnapshot.get("items");
                            if (orderedItems != null) {
                                for (Map<String, Object> item : orderedItems) {
                                    String title = (String) item.get("title");
                                    String imagePath = (String) item.get("imagePath");
                                    int numberInCart = ((Long) item.get("numberInCart")).intValue();
                                    double price = (double) item.get("price");

                                    OrderItem orderItem = new OrderItem(title, numberInCart, price, imagePath, userName);
                                    orderItemList.add(orderItem);
                                }
                            }
                        }

                        // Update the RecyclerView adapter with the retrieved order items
                        RecyclerView recyclerView = view.findViewById(R.id.cartView);
                        OrderAdapter adapter = new OrderAdapter(orderItemList, getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure to fetch data from Firestore
                    }
                });

        return view;
    }
}
