package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recalltracker.Models.VehicleItem;

import java.util.ArrayList;
import java.util.List;

public class VehiclesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    List<VehicleItem> vehicleItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);

        VehicleItem vehicleItem = new VehicleItem();

        String carName = "2017 TOYOTA RAV4 HV";
        String carVIN = "JTMDJREV6HD120994";

        vehicleItem.setCarName(carName);
        vehicleItem.setCarVIN(carVIN);

        vehicleItemList.add(vehicleItem);
        vehicleItemList.add(vehicleItem);

        recyclerView = findViewById(R.id.vins_rv);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new VehiclesListAdapter(vehicleItemList, VehiclesListActivity.this);
        recyclerView.setAdapter(mAdapter);
    }
}
