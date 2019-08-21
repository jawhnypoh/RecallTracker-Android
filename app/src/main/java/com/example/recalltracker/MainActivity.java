package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.recalltracker.Utils.Firebase;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.firebaseInit(this);
    }

}
