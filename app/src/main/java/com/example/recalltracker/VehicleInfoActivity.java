package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.recalltracker.Utilities.VehicleInfoUtils;

public class VehicleInfoActivity extends AppCompatActivity {

    private final String TAG = "VehicleInfoActivity: ";

    private TextView year_TV, vin_TV, confirm_TV;

    private Button addBtn, searchAnotherBtn;

    private ProgressBar mProgress;

    // Nissan Rogue Sport: JN1BJ1CRXJW288164
    // Toyota RAV4 HV: JTMDJREV6HD120994

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);
        confirm_TV = findViewById(R.id.confirm_tv);

        addBtn = (Button)findViewById(R.id.btn_add);
        searchAnotherBtn = (Button)findViewById(R.id.btn_another);
        mProgress = (ProgressBar)findViewById(R.id.load_more_progress);
        searchAnotherBtn.setBackgroundColor(Color.TRANSPARENT);
        searchAnotherBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Go to Search activity
                goToSearchActivity();
            }
        });

        final String queryVIN = getIntent().getStringExtra("VIN_SEARCH_QUERY");
        String queryUrl = getIntent().getStringExtra("VIN_API_URL");

        setVehicleInfo(queryUrl, queryVIN);
    }

    protected void setVehicleInfo(String queryURL, final String queryVIN) {
        year_TV = (TextView)findViewById(R.id.car_info_tv);
        vin_TV = (TextView)findViewById(R.id.vin_tv);

        VehicleInfoUtils.placeIdTask asyncTask = new VehicleInfoUtils.placeIdTask(new VehicleInfoUtils.AsyncResponse() {
            @Override
            public void processFinish(String year, String make, String model) {
                // if VIN is not found
                if(make.isEmpty() || model.isEmpty() || year.isEmpty() ) {
                    Log.d(TAG, "if statement called");
                    addBtn.setVisibility(View.INVISIBLE);
                    mProgress.setVisibility(View.INVISIBLE);
                    confirm_TV.setText("Vehicle Not Found");
                    vin_TV.setText("We couldn't find a vehicle with that VIN. Please try again.");
                }
                else {
                    Log.d(TAG, "else called");
                    addBtn.setVisibility(View.VISIBLE);
                    confirm_TV.setText("Vehicle Found");
                    year_TV.setText(year + " " + make + " " + model);
                    vin_TV.setText("VIN: " + queryVIN);
                }
            }
        });
        Log.d(TAG, "setVehicleInfo() queryURL: " + queryURL);
        asyncTask.execute(queryURL);
    }

    private void goToSearchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
