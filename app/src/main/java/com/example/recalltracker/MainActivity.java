package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recalltracker.Utilities.VehicleInfoUtils;

import java.util.Objects;

import com.example.recalltracker.Utils.Firebase;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity: ";

    private EditText mSearchBoxET;
    private static final String VIN_SEARCH_KEY = "VINSearchURL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.firebaseInit(this);

        mSearchBoxET = findViewById(R.id.et_search_box);
        mSearchBoxET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;

                String searchQuery = mSearchBoxET.getText().toString();

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(searchQuery)) {

                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        Objects.requireNonNull(imm).hideSoftInputFromWindow(mSearchBoxET.getWindowToken(), 0);

                        doVINSearch(searchQuery);
                        Log.d(TAG, "IME Search handled correctly ");
                        handled = true;
                    } else {
                        // TextUtils is empty, can't be allowed
                        Toast.makeText(getApplicationContext(),"VIN cannot be empty",Toast.LENGTH_SHORT).show();
                    }
                }
                Log.d(TAG, "IME Search not handled correctly ");
                return handled;
            }
        });

        ImageButton searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(mSearchBoxET.getWindowToken(), 0);

                    doVINSearch(searchQuery);
                }
                else {
                    // TextUtils is empty, can't be allowed
                    Toast.makeText(getApplicationContext(),"VIN cannot be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void doVINSearch(String searchQuery) {
        String VINSearchURL = VehicleInfoUtils.buildVINURL(searchQuery);
        Bundle args = new Bundle();
        args.putString(VIN_SEARCH_KEY, VINSearchURL);

        Intent intent = new Intent(getBaseContext(), VehicleInfoActivity.class);
        intent.putExtra("VIN_API_URL", VINSearchURL);
        intent.putExtra("VIN_SEARCH_QUERY", searchQuery);
        startActivity(intent);
    }
}
