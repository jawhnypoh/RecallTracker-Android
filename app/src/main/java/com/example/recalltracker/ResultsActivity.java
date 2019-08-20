package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView MFG_Campaign_number, notes;

    Typeface roboto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);
        TextView MFG_Campaign_number = findViewById(R.id.MFG_Campaign_Number);
        TextView notes = findViewById(R.id.Notes);
        MFG_Campaign_number.setText("Hello");
        notes.setText("Existence is pain");
    }


}
