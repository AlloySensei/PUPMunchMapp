package com.example.ui_pupmunchmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemViewHolder> {

    Context context;
    List<FoodItem> items;

    public FoodItemAdapter(Context context, List<FoodItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodItemViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        holder.foodNameView.setText(items.get(position).getItemName());
        holder.stallNameView.setText(items.get(position).getStallName());
        holder.foodPriceView.setText(String.valueOf(items.get(position).getFoodPrice()));
        holder.foodImageView.setImageResource(items.get(position).getFoodImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
