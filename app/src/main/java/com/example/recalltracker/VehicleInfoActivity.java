package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.recalltracker.Utilities.VehicleInfoUtils;

public class VehicleInfoActivity extends AppCompatActivity {

    private final String TAG = "VehicleInfoActivity: ";

    private TextView year_TV, vin_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);

        final String queryVIN = getIntent().getStringExtra("VIN_SEARCH_QUERY");
        String queryUrl = getIntent().getStringExtra("VIN_API_URL");

        setVehicleInfo(queryUrl, queryVIN);
    }

    protected void setVehicleInfo(String queryURL, final String queryVIN) {
        year_TV = (TextView)findViewById(R.id.car_info_tv);
        vin_TV = (TextView)findViewById(R.id.vin_tv);

        VehicleInfoUtils.placeIdTask asyncTask = new VehicleInfoUtils.placeIdTask(new VehicleInfoUtils.AsyncResponse() {
            @Override
            public void processFinish(Integer year, String make, String model) {
                year_TV.setText(Integer.toString(year) + " " + make + " " + model);
                vin_TV.setText("VIN: " + queryVIN);
            }
        });
        Log.d(TAG, "setVehicleInfo() queryURL: " + queryURL);
        asyncTask.execute(queryURL);
    }
}
