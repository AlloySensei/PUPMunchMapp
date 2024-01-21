package com.example.ui_pupmunchmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListOrders> {
    public ListAdapter(@NonNull Context context, ArrayList<ListOrders> dataArrayList){
        super(context, R.layout.list_orders, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListOrders listOrders = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_orders, parent, false);
        }

        // Find the TextViews in your list item layout and set their text
        TextView stallNameTextView = view.findViewById(R.id.listdOrderStallName);
        TextView orderRefNumTextView = view.findViewById(R.id.listdOrderRefNum);
        TextView customerNameTextView = view.findViewById(R.id.listdCustomerName);
        TextView orderDateTextView = view.findViewById(R.id.listdOrderDate);
        TextView orderTimeTextView = view.findViewById(R.id.listdOrderTime);
        // ... (find other TextViews)

        // Set the text for each TextView
        if (listOrders != null) {
            stallNameTextView.setText(listOrders.stallName);
            orderRefNumTextView.setText(listOrders.orderRefNum);
            customerNameTextView.setText(listOrders.customerName);
            orderDateTextView.setText(listOrders.date);
            orderTimeTextView.setText(listOrders.time);
        }
        return view;
    }
}
