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

        Button results_page = findViewById(R.id.results_page);

        results_page.setText("Yo dawg, tap me");
        results_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToResults();
            }
        });

    }
    private void goToResults() {
        Intent intent = new Intent(this, ResultsActivity.class);

        startActivity(intent);
    }
}
