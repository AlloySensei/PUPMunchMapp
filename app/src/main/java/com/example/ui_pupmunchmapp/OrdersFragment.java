package com.example.ui_pupmunchmapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ui_pupmunchmapp.databinding.ActivityMainNavigationCBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class OrdersFragment extends Fragment {

    // Declare lists to hold data
    private ArrayList<String> stallNameList = new ArrayList<>();
    private ArrayList<String> orderRefNumList = new ArrayList<>();
    private ArrayList<String> customerNameList = new ArrayList<>();
    private ArrayList<String> orderDateList = new ArrayList<>();
    private ArrayList<String> orderTimeList = new ArrayList<>();

    // Adapter for the ListView
    private ListAdapter adapter;
    ArrayList<ListOrders> ordersList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize your arrays here
        String[] stallNames = {"Stall1", "Stall2", "Stall3"};
        String[] orderRefNums = {"123", "456", "789"};
        String[] customerNames = {"Cus1", "Cus2", "Cus3"};
        String[] orderDates = {"01-20", "01-21", "01-22"};
        String[] orderTimes = {"01:30", "2:30", "3:30"};

        // Populate ArrayLists with the arrays
        stallNameList.addAll(Arrays.asList(stallNames));
        orderRefNumList.addAll(Arrays.asList(orderRefNums));
        customerNameList.addAll(Arrays.asList(customerNames));
        orderDateList.addAll(Arrays.asList(orderDates));
        orderTimeList.addAll(Arrays.asList(orderTimes));

        // Generate the list of ListOrders objects
        ordersList = generateOrdersList();

        // Initialize the adapter with the sample data
        adapter = new ListAdapter(requireContext(), ordersList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        // Find the ListView and set the adapter
        ListView listView = view.findViewById(R.id.orderListView);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListOrders selectedOrder = (ListOrders) parent.getItemAtPosition(position);

                // Create OrderDetails and set arguments
                OrderDetails orderDetailsFragment = new OrderDetails();
                Bundle bundle = new Bundle();
                bundle.putString("StallName", selectedOrder.stallName);
                bundle.putString("OrderRefNum", selectedOrder.orderRefNum);
                bundle.putString("CustomerName", selectedOrder.customerName);
                bundle.putString("OrderDate", selectedOrder.date);
                bundle.putString("OrderTime", selectedOrder.time);
                orderDetailsFragment.setArguments(bundle);

                // Replace the fragment with OrderDetailsFragment
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.ordersContainer, orderDetailsFragment);
                fr.addToBackStack(null);
                fr.commit();
            }
        });
        return view;
    }

    // Method to generate a list of ListOrders objects
    private ArrayList<ListOrders> generateOrdersList() {
        ArrayList<ListOrders> ordersList = new ArrayList<>();

        // Assuming all lists have the same size, you can iterate through one of them
        for (int i = 0; i < stallNameList.size(); i++) {
            ListOrders order = new ListOrders(
                    stallNameList.get(i),
                    orderRefNumList.get(i),
                    customerNameList.get(i),
                    orderDateList.get(i),
                    orderTimeList.get(i)
            );
            ordersList.add(order);
        }
        return ordersList;
    }
}