package com.example.recalltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.recalltracker.Utilities.RecallTrackerUtils;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity: ";

    private EditText mSearchBoxET;
    private ProgressBar mLoadingProgressBar;
    private static final String VIN_SEARCH_KEY = "VINSearchURL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxET = (EditText)findViewById(R.id.et_search_box);
        mSearchBoxET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;

                String searchQuery = mSearchBoxET.getText().toString();

                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(!TextUtils.isEmpty(searchQuery)) {

                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(mSearchBoxET.getWindowToken(), 0);

                       doVINSearch(searchQuery);
                        Log.d(TAG, "IME Search handled correctly ");
                        handled = true;
                    }
                }
                Log.d(TAG, "IME Search not handled correctly ");
                return handled;
            }
        });

        ImageButton searchButton = (ImageButton) findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mSearchBoxET.getWindowToken(), 0);

                    Log.d(TAG, "search button hit");
                    doVINSearch(searchQuery);
                }
            }
        });
    }

    private void doVINSearch(String searchQuery) {
        //String VINSearchURL = RecallTrackerUtils.buildVINSearchURL(searchQuery);
        Bundle args = new Bundle();
//        args.putString(VIN_SEARCH_KEY, VINSearchURL);
        mLoadingProgressBar.setVisibility(View.VISIBLE);
       // getSupportLoaderManager().restartLoader(RECALL_TRACKER_SEARCH_LOADER_ID, args, this);

       // Log.d(TAG, "VINSearchURL is: " + VINSearchURL);
    }
}
