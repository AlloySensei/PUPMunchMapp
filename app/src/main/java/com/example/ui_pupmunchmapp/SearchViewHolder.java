package com.example.ui_pupmunchmapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView foodNameView, stallNameView, foodPriceView;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.foodImageViewS);
        foodNameView = itemView.findViewById(R.id.foodNameTextViewS);
        stallNameView = itemView.findViewById(R.id.stallNameTextViewS);
        foodPriceView = itemView.findViewById(R.id.foodPriceTextViewS);
    }
}
