package com.example.ui_pupmunchmapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView.Adapter adapterListFood;
    private String searchText = "";
    FirebaseDatabase database;
    RecyclerView searchRecycleView;
    SearchView searchViewBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        database = FirebaseDatabase.getInstance("https://pupmunchmapp-default-rtdb.asia-southeast1.firebasedatabase.app");
        searchRecycleView = view.findViewById(R.id.searchRecyclerView);
        searchViewBar = view.findViewById(R.id.searchViewBar);

        searchViewBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchText = query.trim();
                initList();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchText = newText.trim();
                initList();
                return true;
            }
        });


        return view;
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Foods");
        ArrayList<Foods> list = new ArrayList<>();

        // Capitalize the first letter of each word in searchText
        String capitalizedSearchText = capitalizeEachWord(searchText);

        // Create query to search for titles containing the capitalizedSearchText
        Query query = myRef.orderByChild("Title").startAt(capitalizedSearchText).endAt(capitalizedSearchText + '\uf8ff');

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Clear previous data
                if(snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Foods.class));
                    }
                }
                displayList(list); // Display updated list
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event
            }
        });
    }

    private String capitalizeEachWord(String text) {
        StringBuilder result = new StringBuilder();
        boolean capitalize = true;
        for (char ch : text.toCharArray()) {
            if (Character.isWhitespace(ch)) {
                capitalize = true;
                result.append(ch);
            } else if (capitalize) {
                result.append(Character.toUpperCase(ch));
                capitalize = false;
            } else {
                result.append(Character.toLowerCase(ch));
            }
        }
        return result.toString();
    }


    private void displayList(ArrayList<Foods> list) {
        // Display the list in RecyclerView with GridLayoutManager
        searchRecycleView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        adapterListFood = new FoodListAdapter(list);
        searchRecycleView.setAdapter(adapterListFood);
    }
}