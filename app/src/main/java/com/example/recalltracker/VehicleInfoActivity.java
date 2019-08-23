package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.recalltracker.Models.VehicleItem;
import com.example.recalltracker.Utilities.*;
import com.example.recalltracker.Utils.DatabaseAPI;

public class VehicleInfoActivity extends AppCompatActivity {

    private final String TAG = "VehicleInfoActivity: ";

    private TextView year_TV, vin_TV, confirm_TV;

    private ImageView car_IV;

    private Button addBtn;

    private ProgressBar mProgress;

    private DatabaseAPI databaseAPI;

    private VehicleItem vehicleItem;

    // Nissan Rogue Sport: JN1BJ1CRXJW288164
    // Toyota RAV4 HV: JTMDJREV6HD120994

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);
        confirm_TV = findViewById(R.id.confirm_tv);

        final String queryVIN = getIntent().getStringExtra("VIN_SEARCH_QUERY");
        String queryUrl = getIntent().getStringExtra("VIN_API_URL");

        String userId = getIntent().getStringExtra("USER_ID");
        databaseAPI = new DatabaseAPI(userId);

        Button searchAnotherBtn = findViewById(R.id.btn_another);
        mProgress = findViewById(R.id.load_more_progress);
        car_IV = findViewById(R.id.car_iv);
        car_IV.setVisibility(View.INVISIBLE);
        searchAnotherBtn.setBackgroundColor(Color.TRANSPARENT);
        searchAnotherBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Go to Search activity
                goToSearchActivity();
            }
        });

        addBtn = findViewById(R.id.btn_add);
        addBtn.setVisibility(View.INVISIBLE);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add vehicle to list in DB and go to VehicleListActivity
                addToDB(vehicleItem);
                goToVehicleListActivity();
            }
        });

        setVehicleInfo(queryUrl, queryVIN);
    }

    protected void setVehicleInfo(String queryURL, final String queryVIN) {
        year_TV = findViewById(R.id.car_info_tv);
        vin_TV = findViewById(R.id.vin_tv);

        VehicleInfoUtils.placeIdTask asyncTask = new VehicleInfoUtils.placeIdTask(new VehicleInfoUtils.AsyncResponse() {
            @Override
            public void processFinish(String year, String make, String model) {
                // if VIN is not found
                mProgress.setVisibility(View.INVISIBLE);
                if(make.isEmpty() || model.isEmpty() || year.isEmpty() ) {
                    car_IV.setVisibility(View.VISIBLE);
                    Log.d(TAG, "if statement called");
                    confirm_TV.setText("Vehicle Not Found");
                    vin_TV.setText("We couldn't find a vehicle with that VIN. Please try again.");
                }
                else {
                    Log.d(TAG, "else called");
                    addBtn.setVisibility(View.VISIBLE);
                    car_IV.setVisibility(View.VISIBLE);
                    confirm_TV.setText("Vehicle Found");
                    year_TV.setText(year + " " + make + " " + model);
                    vin_TV.setText("VIN: " + queryVIN);

                    vehicleItem = new VehicleItem(make, model, year, queryVIN);
                }
            }
        });
        Log.d(TAG, "setVehicleInfo() queryURL: " + queryURL);
        asyncTask.execute(queryURL);
    }

    private void addToDB(VehicleItem vehicleItem) {
        databaseAPI.addVehicle(vehicleItem);
    }

    private void goToSearchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToVehicleListActivity() {
        Intent intent = new Intent(this, VehiclesListActivity.class);
        startActivity(intent);
    }
}
