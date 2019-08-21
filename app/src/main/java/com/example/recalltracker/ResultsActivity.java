package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    Typeface roboto;

    String Campaign_Number, Note, NoteDescription, CampaignDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Campaign_Number = "Egg Salad";
        CampaignDescription = "Yummy yummy";
        Note = "Baby Shark";
        NoteDescription = "doo doo doo";
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);

        TextView MFG_Campaign_number = findViewById(R.id.MFG_Campaign_Number);
        TextView notes = findViewById(R.id.Notes);
        setResults(Campaign_Number,CampaignDescription,MFG_Campaign_number);
        setResults(Note,NoteDescription,notes);
        //setResults(Campaign_Number,CampaignDescription,MFG_Campaign_number);
    }

    protected void setResults(String descriptor, String description, TextView textview) {
        textview.setText(descriptor + ": " + description);

    }



}
