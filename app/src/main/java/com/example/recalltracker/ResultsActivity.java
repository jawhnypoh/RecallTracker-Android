package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.example.recalltracker.Utilities.Models.RecallItem;

public class ResultsActivity extends AppCompatActivity {

    Typeface roboto;

    String Campaign_Number, Note, NoteDescription, CampaignDescription, Defect, Defectresults, Consequence, Consequences;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    List<RecallItem> recallItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Campaign_Number = "Campaign Number:";
        CampaignDescription = "N030023";
        Note = "Note:";
        NoteDescription = "GM RECALL NO. 03023.CUSTOMERS CAN ALSO CONTACT THE NATIONAL HIGHWAY TRAFFIC SAFETY ADMINISTRATION&#39;S AUTO SAFETY HOTLINE AT 1-888-DASH-2-DOT (1-888-327-4236).";
        Defect = "Defect:";
        Defectresults = "ON CERTAIN MINIVANS, PICKUP TRUCKS, AND SPORT UTILITY VEHICLES WITH CERTAIN MODEL-ENGINE COMBINATIONS, THE WINDSHIELD WIPER MOTOR MAY FAIL DUE TO CRACKED SOLDER JOINTS ON THE CONTROLLER CIRCUIT BOARD.  THE WINDSHIELD WIPERS COULD WORK INTERMITTENTLY OR NOT AT ALL.";
        Consequence = "Consequence:";
        Consequences = "IF THIS WERE TO OCCUR DURING BAD WEATHER, DRIVER VISIBILITY WOULD BE REDUCED, WHICH COULD RESULT IN A CRASH.";

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

        RecallItem recallItem = new RecallItem();
        recallItem.setCampaign_Number(CampaignDescription);
        recallItem.setNote(NoteDescription);
        recallItem.setDefect(Defectresults);
        recallItem.setConsequences(Consequences);

        RecallItem recallItem1 = new RecallItem();
        recallItem1.setCampaign_Number(CampaignDescription);
        recallItem1.setNote(NoteDescription);
        recallItem1.setDefect(Defectresults);
        recallItem1.setConsequences(Consequences);

        recallItems.add(recallItem);
        recallItems.add(recallItem1);

        recyclerView = findViewById(R.id.results_rv);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ResultsActivityAdapter(recallItems, ResultsActivity.this);
        recyclerView.setAdapter(mAdapter);

//        setResults(Campaign_Number,MFG_Campaign_Header);
//        setResults(Note,notes_Header);
//        setResults(Defect,defect_Header);
//        setResults(Consequence,consequence_Header);
//        setResults(CampaignDescription,MFG_Campaign_number);
//        setResults(NoteDescription,notes);
//        setResults(Defectresults,defect);
//        setResults(Consequences,consequence);


    }

    protected void setResults(String descriptor, TextView textview) {
        textview.setText(descriptor);

    }



}
