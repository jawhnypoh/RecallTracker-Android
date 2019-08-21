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

        Campaign_Number = "Campaign Number:";
        CampaignDescription = "This shouldn't be here";
        Note = "Note:";
        NoteDescription = "wHaT ArE U dOIn";
        Defect = "Defect:";
        Defectresults = "Testing";
        Consequence = "Consequence:";
        Consequences = "Sales sux";

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);

        TextView MFG_Campaign_number = findViewById(R.id.MFG_Campaign_Number);
        TextView notes = findViewById(R.id.Notes);
        TextView defect = findViewById(R.id.Defect);
        TextView consequence = findViewById(R.id.Consequences);
        TextView MFG_Campaign_Header = findViewById(R.id.MFG_Campaign_Header);
        TextView notes_Header =findViewById(R.id.Notes_Header);
        TextView defect_Header =findViewById(R.id.Defect_Header);
        TextView consequence_Header =findViewById(R.id.Consequence_Header);

        setResults(Campaign_Number,MFG_Campaign_Header);
        setResults(Note,notes_Header);
        setResults(Defect,defect_Header);
        setResults(Consequence,consequence_Header);
        setResults(CampaignDescription,MFG_Campaign_number);
        setResults(NoteDescription,notes);
        setResults(Defectresults,defect);
        setResults(Consequences,consequence);
    }

    protected void setResults(String descriptor, TextView textview) {
        textview.setText(descriptor);

    }



}
