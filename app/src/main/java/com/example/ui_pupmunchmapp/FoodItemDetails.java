package com.example.ui_pupmunchmapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodItemDetails extends Fragment {

    public FoodItemDetails() {

    }

    public static FoodItemDetails newInstance(String param1, String param2) {
        FoodItemDetails fragment = new FoodItemDetails();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_item_details, container, false);

        ImageView displayFoodImage = view.findViewById(R.id.displayFoodImage);
        TextView displayFoodName = view.findViewById(R.id.displayFoodName);
        TextView displayStallName = view.findViewById(R.id.displayStallName);
        TextView displayFoodPrice = view.findViewById(R.id.displayFoodPrice);

        Bundle args = getArguments();
        if (args != null) {
            int foodImage = args.getInt("FoodImage", 0);
            String foodName = args.getString("FoodName", "");
            String stallName = args.getString("StallName", "");
            int foodPrice = args.getInt("FoodPrice", 0);

            // Set values in the UI elements
            displayFoodImage.setImageResource(foodImage);
            displayFoodName.setText(foodName);
            displayStallName.setText(stallName);
            displayFoodPrice.setText(String.valueOf(foodPrice));
        }

        return view;
    }
}