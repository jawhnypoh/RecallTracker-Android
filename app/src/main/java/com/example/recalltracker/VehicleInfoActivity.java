package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.recalltracker.Utilities.VehicleInfoUtils;

public class VehicleInfoActivity extends AppCompatActivity {

    private TextView year_TV, vin_TV;

    private String SAMPLE_VIN = "5TFAW5F19EX759955";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);

        setVehicleInfo();
    }

    protected void setVehicleInfo() {
        year_TV = (TextView)findViewById(R.id.car_info_tv);
        vin_TV = (TextView)findViewById(R.id.vin_tv);

        VehicleInfoUtils.placeIdTask asyncTask = new VehicleInfoUtils.placeIdTask(new VehicleInfoUtils.AsyncResponse() {
            @Override
            public void processFinish(Integer year, String make, String model) {
                year_TV.setText(Integer.toString(year) + " " + make + " " + model);
                vin_TV.setText("VIN: " + SAMPLE_VIN);
            }
        });

        asyncTask.execute(SAMPLE_VIN);
    }
}
