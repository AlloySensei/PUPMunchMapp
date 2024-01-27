package com.example.ui_pupmunchmapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemViewHolder> {

    Context context;
    List<FoodItem> items;
    private FragmentManager fragmentManager;

    public FoodItemAdapter(Context context, List<FoodItem> items, FragmentManager fragmentManager) {
        this.context = context;
        this.items = items;
        this.fragmentManager = fragmentManager;
    }

    public FoodItem getItem(int position) {
        return items.get(position);
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodItemViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItem foodItem = items.get(position);

        // Bind data to views in food_item_view.xml
        holder.foodImageView.setImageResource(foodItem.getFoodImage());
        holder.foodNameView.setText(foodItem.getItemName());
        holder.stallNameView.setText(foodItem.getStallName());
        holder.foodPriceView.setText(String.valueOf(foodItem.getFoodPrice()));

        // Set click listener for individual items
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click if needed
                // For example, open FoodItemDetails fragment
                openFoodItemDetails(foodItem);
            }
        });
    }

    private void openFoodItemDetails(FoodItem selectedFoodItem) {
        FoodItemDetails foodItemDetailsFragment = new FoodItemDetails();
        Bundle bundle = new Bundle();
        bundle.putInt("FoodImage", selectedFoodItem.getFoodImage());
        bundle.putString("FoodName", selectedFoodItem.getItemName());
        bundle.putString("StallName", selectedFoodItem.getStallName());
        bundle.putInt("FoodPrice", selectedFoodItem.getFoodPrice());

        foodItemDetailsFragment.setArguments(bundle);

        FragmentTransaction fr = fragmentManager.beginTransaction();
        fr.replace(R.id.searchFragmentContainer, foodItemDetailsFragment);
        fr.addToBackStack(null);
        fr.commit();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
