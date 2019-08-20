package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextButton = (Button)findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Go to Next activity
                goToNextActivity();
            }
        });
    }

    private void goToNextActivity() {
        Intent intent = new Intent(this, VehicleInfoActivity.class);

        startActivity(intent);
    }
}
