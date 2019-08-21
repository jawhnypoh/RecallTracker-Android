package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    Typeface roboto;

    String Campaign_Number, Note, NoteDescription, CampaignDescription, Defect, Defectresults, Consequence, Consequences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Campaign_Number = "Egg Salad";
        CampaignDescription = "Yummy yummy";
        Note = "Baby Shark";
        NoteDescription = "doo doo doo";
        Defect = "What not to break";
        Defectresults = "My heart hurts my soul, blah bleh big tears cry cry whine while eating hearts of my enemies";
        Consequence = "Heartache";
        Consequences = "From broken heart, these consequences tend to be very long in order for the explaination to be accurate this is veyrt i am making this very long";

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);

        TextView MFG_Campaign_number = findViewById(R.id.MFG_Campaign_Number);
        TextView notes = findViewById(R.id.Notes);
        TextView defect = findViewById(R.id.Defect);
        TextView consequence = findViewById(R.id.Consequences);
        setResults(Campaign_Number,CampaignDescription,MFG_Campaign_number);
        setResults(Note,NoteDescription,notes);
        setResults(Defect,Defectresults,defect);
        setResults(Consequence,Consequences,consequence);
        //setResults(Campaign_Number,CampaignDescription,MFG_Campaign_number);
    }

    protected void setResults(String descriptor, String description, TextView textview) {
        textview.setText(descriptor + ": " + description);

    }



}
