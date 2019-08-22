package com.example.recalltracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.recalltracker.Models.VehicleItem;
import com.example.recalltracker.Utils.DatabaseAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehiclesListActivity extends AppCompatActivity {

    private static final String TAG = "VehiclesListActivity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button addVehicleBtn;

    private String userId;

    ArrayList vehicleItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);

        addVehicleBtn = findViewById(R.id.btn_add_car);
        addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Go to Search activity
                goToSearchActivity();
            }
        });

        recyclerView = findViewById(R.id.vins_rv);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        userId = getIntent().getStringExtra("USER_ID");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference users = db.collection("users");

        DatabaseAPI.getUser(users, userId, new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    mAdapter = new VehiclesListAdapter(vehicleItemList, VehiclesListActivity.this);
                    recyclerView.setAdapter(mAdapter);
                }
            }
        });

    }

    private void goToSearchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
