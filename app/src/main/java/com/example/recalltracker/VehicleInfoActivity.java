package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.recalltracker.Utilities.VehicleInfoUtils;

public class VehicleInfoActivity extends Activity {

    private TextView year_TV, make_TV, model_TV;

    private String SAMPLE_VIN = "JTMDJREV6HD120994";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);

        setVehicleInfo();
    }

    protected void setVehicleInfo() {
        year_TV = (TextView)findViewById(R.id.year_tv);
        make_TV = (TextView)findViewById(R.id.make_tv);
        model_TV = (TextView)findViewById(R.id.model_tv);

        VehicleInfoUtils.placeIdTask asyncTask = new VehicleInfoUtils.placeIdTask(new VehicleInfoUtils.AsyncResponse() {
            @Override
            public void processFinish(String year, String make, String model) {
                year_TV.setText(year);
                make_TV.setText(make);
                model_TV.setText(model);
            }
        });

        asyncTask.execute(SAMPLE_VIN);
    }
}
