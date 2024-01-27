package com.example.ui_pupmunchmapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodItemViewHolder extends RecyclerView.ViewHolder {
    ImageView foodImageView;
    TextView foodNameView, stallNameView, foodPriceView;

    public FoodItemViewHolder(@NonNull View itemView) {
        super(itemView);
        foodImageView = itemView.findViewById(R.id.foodImageViewS);
        foodNameView = itemView.findViewById(R.id.foodNameTextViewS);
        stallNameView = itemView.findViewById(R.id.stallNameTextViewS);
        foodPriceView = itemView.findViewById(R.id.foodPriceTextViewS);
    }
}
