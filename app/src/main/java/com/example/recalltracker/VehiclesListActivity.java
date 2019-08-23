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
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class VehiclesListActivity extends AppCompatActivity {

    private static final String TAG = "VehiclesListActivity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button addVehicleBtn;

    private String userId;
    private DatabaseAPI databaseAPI;

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

        String year = "2017";
        String make = "TOYOTA";
        String model = "RAV4 HV";

        String carName = "2017 TOYOTA RAV4 HV";
        String carVIN = "JTMDJREV6HD120994";

        VehicleItem vehicleItem = new VehicleItem(year, make, model, carVIN);

        carName = "2018 NISSAN ROGUE SPORT";
        carVIN = "JN1BJ1CRXJW288164";

        year = "2018";
        make = "NISSAN";
        model = "ROGUE SPORT";

        VehicleItem vehicleItem1 = new VehicleItem(year, make, model, carVIN);

        vehicleItemList.add(vehicleItem);
        vehicleItemList.add(vehicleItem1);

        recyclerView = findViewById(R.id.vins_rv);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        userId = getIntent().getStringExtra("USER_ID");
        databaseAPI = new DatabaseAPI(userId);

        mAdapter = new VehiclesListAdapter(vehicleItemList, VehiclesListActivity.this);

        ((VehiclesListAdapter) mAdapter).setOnItemClickListener(new VehiclesListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // Call the Firebase API to get Recalls for specific vehicle
                goToResultsActivity();
                Log.d(TAG, "onItemClick position: " + position);
            }
        });
        recyclerView.setAdapter(mAdapter);

        databaseAPI.getUser(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                }
            }
        });
    }

    private void goToSearchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToResultsActivity() {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }
}
