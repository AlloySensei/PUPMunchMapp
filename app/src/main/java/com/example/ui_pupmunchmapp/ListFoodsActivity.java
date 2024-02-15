package com.example.ui_pupmunchmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ListFoodsActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterListFood;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;

    TextView titleTxt;
    ImageView btn;
    FirebaseDatabase database;
    ProgressBar progressBar;
    RecyclerView foodListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_foods);

        titleTxt = findViewById(R.id.titleTxt);
        btn = findViewById(R.id.Btn);
        progressBar = findViewById(R.id.progressBar);
        foodListView = findViewById(R.id.foodListView);
        database = FirebaseDatabase.getInstance("https://pupmunchmapp-default-rtdb.asia-southeast1.firebasedatabase.app");



        getIntentExtra();
        initList();
        setVariable();
    }

    private void setVariable() {

    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Foods");
        progressBar.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();

        Query query;
        if(isSearch){
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText + '\uf8ff');
        }else{
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue : snapshot.getChildren()){
                        list.add(issue.getValue(Foods.class));
                    }
                    if(list.size()>0){
                        foodListView.setLayoutManager(new GridLayoutManager(ListFoodsActivity.this,2));
                        adapterListFood = new FoodListAdapter(list);
                        foodListView.setAdapter(adapterListFood);
                    }
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId", 0);
        categoryName = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

        titleTxt.setText(categoryName);
        btn.setOnClickListener(v -> {finish();});
    }
}