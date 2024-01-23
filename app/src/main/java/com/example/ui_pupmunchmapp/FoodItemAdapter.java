package com.example.ui_pupmunchmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    Context context;
    List<FoodItem> items;

    public FoodItemAdapter(Context context, List<FoodItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.foodNameView.setText(items.get(position).getItemName());
        holder.stallNameView.setText(items.get(position).getStallName());
        holder.foodPriceView.setText(items.get(position).getFoodPrice());
        holder.imageView.setImageResource(items.get(position).getFoodImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
