package com.example.ui_pupmunchmapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OrderDetails extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_details, container, false);

        // Retrieve data from the Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            String stallName = bundle.getString("StallName", "");
            String orderRefNum = bundle.getString("OrderRefNum", "");
            String customerName = bundle.getString("CustomerName", "");
            String date = bundle.getString("OrderDate", "");
            String time = bundle.getString("OrderTime", "");

            // update TextViews with the data
            TextView stallNameTextView = view.findViewById(R.id.displayStallName);
            TextView orderRefNumTextView = view.findViewById(R.id.displayOrderRefNum);
            TextView customerNameTextView = view.findViewById(R.id.displayCustomerName);
            TextView orderDateTextView = view.findViewById(R.id.displayOrderDate);
            TextView orderTimeTextView = view.findViewById(R.id.displayOrderTime);

            stallNameTextView.setText(stallName);
            orderRefNumTextView.setText(orderRefNum);
            customerNameTextView.setText(customerName);
            orderDateTextView.setText(date);
            orderTimeTextView.setText(time);
        }
        return view;
    }
}