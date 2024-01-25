package com.example.ui_pupmunchmapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FoodItemAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<FoodItem> items = new ArrayList<FoodItem>();
        items.add(new FoodItem("Pepperoni Pizza", "Pizza Stall", R.drawable.a, 160));
        items.add(new FoodItem("French fries", "Fries Stall", R.drawable.b, 40));
        items.add(new FoodItem("Ham sandwich", "Sandwich Stall", R.drawable.c, 35));
        items.add(new FoodItem("Chao fan", "Rice Bowl Stall", R.drawable.d, 60));
        items.add(new FoodItem("Siomai", "Street Food Stall", R.drawable.e, 20));
        items.add(new FoodItem("Beef burger B1T1", "Burger Stall", R.drawable.f, 50));
        items.add(new FoodItem("Pepperoni Pizza", "Pizza Stall", R.drawable.a, 160));
        items.add(new FoodItem("French fries", "Fries Stall", R.drawable.b, 40));
        items.add(new FoodItem("Ham sandwich", "Sandwich Stall", R.drawable.c, 35));
        items.add(new FoodItem("Chao fan", "Rice Bowl Stall", R.drawable.d, 60));
        items.add(new FoodItem("Siomai", "Street Food Stall", R.drawable.e, 20));
        items.add(new FoodItem("Beef burger B1T1", "Burger Stall", R.drawable.f, 50));

        // Initialize the adapter with the items
        adapter = new FoodItemAdapter(getActivity().getApplicationContext(), items);
    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_search, container, false);
//        // Find the ListView and set the adapter
//        RecyclerView recyclerView = view.findViewById(R.id.searchRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);
//
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                View child = rv.findChildViewUnder(e.getX(), e.getY());
//                if (child != null && e.getAction() == MotionEvent.ACTION_UP) {
//                    int position = rv.getChildAdapterPosition(child);
//                    FoodItem selectedFoodItem = adapter.getItemName(position);
//
//                    // Set arguments for FoodItemDetails
//                    FoodItemDetails foodItemDetailsFragment = new FoodItemDetails();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("FoodName", selectedOrder.displayFoodName);
//                    bundle.putString("StallName", selectedOrder.stallname);
//                    bundle.putString("FoodPrice", selectedOrder.foodPrice);
//                    //get
//                    foodItemDetailsFragment.setArguments(bundle);
//
//                    // Replace the fragment with OrderDetailsFragment
//                    FragmentTransaction fr = getFragmentManager().beginTransaction();
//                    fr.replace(R.id.ordersContainer, foodItemDetailsFragment);
//                    fr.addToBackStack(null);
//                    fr.commit();
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                // Handle touch events if needed
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//                // Handle disallow intercept if needed
//            }
//        });
//        return view;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
//        FoodItem foodItem = items.get(position);
//
//        holder.foodNameView.setText(foodItem.getItemName());
//        holder.stallNameView.setText(foodItem.getStallName());
//        holder.foodPriceView.setText(String.valueOf(foodItem.getFoodPrice()));
//        holder.foodImageView.setImageResource(foodItem.getFoodImage());
//
//        // Set click listener for individual items
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle item click, for example, pass data to FoodItemDetails
//                openFoodItemDetails(foodItem);
//
//                private void openFoodItemDetails(FoodItem selectedFoodItem) {
//                    FoodItemDetails foodItemDetailsFragment = new FoodItemDetails();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("FoodName", selectedFoodItem.getItemName());
//                    bundle.putString("StallName", selectedFoodItem.getStallName());
//                    bundle.putInt("FoodPrice", selectedFoodItem.getFoodPrice());
//                    // Add other data as needed
//
//                    foodItemDetailsFragment.setArguments(bundle);
//
//                    FragmentTransaction fr = getFragmentManager().beginTransaction();
//                    fr.replace(R.id.fragmentContainer, foodItemDetailsFragment);
//                    fr.addToBackStack(null);
//                    fr.commit();
//                }
//            }
//        });
//    }
}