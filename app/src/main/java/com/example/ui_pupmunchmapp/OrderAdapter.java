package com.example.ui_pupmunchmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<OrderItem> orderItemList;
    private Context context;

    public OrderAdapter(List<OrderItem> orderItemList, Context context) {
        this.orderItemList = orderItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_orders, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem orderItem = orderItemList.get(position);
        holder.bind(orderItem);
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, numberInCartTextView, totalPriceTextView, userNameTextView;
        ImageView imageView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTxt);
            numberInCartTextView = itemView.findViewById(R.id.numberInCart);
            totalPriceTextView = itemView.findViewById(R.id.totalFee);
            userNameTextView = itemView.findViewById(R.id.customerName);
            imageView = itemView.findViewById(R.id.pic);
        }

        public void bind(OrderItem orderItem) {
            titleTextView.setText(orderItem.getTitle());
            numberInCartTextView.setText(String.valueOf(orderItem.getNumberInCart()));
            totalPriceTextView.setText("P" + orderItem.getTotalPrice());
            userNameTextView.setText(orderItem.getUserName());

            Glide.with(context)
                    .load(orderItem.getImagePath())
                    .transform(new CenterCrop(), new RoundedCorners(30))
                    .into(imageView);
        }
    }
}
