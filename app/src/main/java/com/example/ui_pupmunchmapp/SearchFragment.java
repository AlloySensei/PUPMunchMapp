package com.example.ui_pupmunchmapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    public FoodItemAdapter adapter;
    public RecyclerView recyclerView;
    public SearchView searchView;
    public List<FoodItem> itemList;
    Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<FoodItem> items = new ArrayList<FoodItem>();
        items.add(new FoodItem("Pepperoni Pizza", "Pizza Stall", R.drawable.a, 160));
        items.add(new FoodItem("French fries", "Fries Stall", R.drawable.b, 40));
        items.add(new FoodItem("Ham sandwich", "Sandwich Stall", R.drawable.c, 35));
        items.add(new FoodItem("Chao fan", "Rice Bowl Stall", R.drawable.d, 60));
        items.add(new FoodItem("Siomai", "Street Food Stall", R.drawable.e, 20));
        items.add(new FoodItem("Beef burger B1T1", "Burger Stall", R.drawable.f, 50));
        items.add(new FoodItem("Pepperoni Pizza", "Pizza Stall", R.drawable.a, 160));
        items.add(new FoodItem("French fries", "Fries Stall", R.drawable.b, 40));
        items.add(new FoodItem("Ham sandwich", "Sandwich Stall", R.drawable.c, 35));
        items.add(new FoodItem("Chao fan", "Rice Bowl Stall", R.drawable.d, 60));
        items.add(new FoodItem("Siomai", "Street Food Stall", R.drawable.e, 20));
        items.add(new FoodItem("Beef burger B1T1", "Burger Stall", R.drawable.f, 50));

        // Initialize the adapter with the items
        adapter = new FoodItemAdapter(getActivity().getApplicationContext(), items, requireActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        // Find the RecyclerView and set the adapter
        recyclerView = view.findViewById(R.id.searchRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the RecyclerView based on the search query
                filterList(newText);
                return true;
            }
        });

        recyclerView = recyclerView.findViewById(R.id.searchRecyclerView);
        itemList = new ArrayList<>();

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && e.getAction() == MotionEvent.ACTION_UP) {
                    int position = rv.getChildAdapterPosition(child);
                    FoodItem selectedFoodItem = adapter.getItem(position);

                    // Set arguments for FoodItemDetails
                    FoodItemDetails foodItemDetailsFragment = new FoodItemDetails();
                    Bundle bundle = new Bundle();
                    bundle.putInt("FoodImage", selectedFoodItem.getFoodImage());
                    bundle.putString("FoodName", selectedFoodItem.getItemName());
                    bundle.putString("StallName", selectedFoodItem.getStallName());
                    bundle.putInt("FoodPrice", selectedFoodItem.getFoodPrice());
                    //get
                    foodItemDetailsFragment.setArguments(bundle);

                    // Replace the fragment with OrderDetailsFragment
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.searchFragmentContainer, foodItemDetailsFragment);
                    fr.addToBackStack(null);
                    fr.commit();
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // Handle touch events if needed
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                // Handle disallow intercept if needed
            }
        });
        return view;
    }

    private void filterList(String text) {
        List<FoodItem> filteredList = new ArrayList<>();
        for (FoodItem item : itemList){
            if (item.getItemName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.
                    setFilteredItems(filteredList);
        }
    }
}