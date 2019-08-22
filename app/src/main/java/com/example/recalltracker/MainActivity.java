package com.example.recalltracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.example.recalltracker.Utilities.VehicleInfoUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.example.recalltracker.Utils.DatabaseAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity: ";

    private EditText mSearchBoxET;
    private ProgressBar mProgress;
    private static final String VIN_SEARCH_KEY = "VINSearchURL";

    private String userId;

    private DatabaseAPI databaseAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        if(!preferences.getBoolean("onboarding_complete", false)){
            Intent onboarding = new Intent(this, OnboardingActivity.class);
            startActivity(onboarding);
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                    databaseAPI = new DatabaseAPI(userId);
                    firebaseInit(userId);
                }
            }
        });

        Button vehiclesListBtn = findViewById(R.id.btn_vin_list);
        vehiclesListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToVehiclesListActivity();
            }
        });

        // this accesses an id in different layout
        View inflatedView = getLayoutInflater().inflate(R.layout.activity_vehicle_info, null);
        mProgress = inflatedView.findViewById(R.id.load_more_progress);
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

    private void firebaseInit(final String userId) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = Objects.requireNonNull(task.getResult()).getToken();
                        Log.d(TAG, "Token: " + token);

                        updatePushToken(userId, token);
                    }
                });
    }

    private void updatePushToken(String userId, String pushToken) {
        Map<String, Object> data = new HashMap<>();
        data.put("pushToken", pushToken);

        databaseAPI.updateUser(data);
    }

    private void doVINSearch(String searchQuery) {
        mProgress.setVisibility(View.VISIBLE);
        String VINSearchURL = VehicleInfoUtils.buildVINURL(searchQuery);
        Bundle args = new Bundle();
        args.putString(VIN_SEARCH_KEY, VINSearchURL);

        Intent intent = new Intent(getBaseContext(), VehicleInfoActivity.class);
        intent.putExtra("VIN_API_URL", VINSearchURL);
        intent.putExtra("VIN_SEARCH_QUERY", searchQuery);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

    private void goToVehiclesListActivity() {
        Intent intent = new Intent(this, VehiclesListActivity.class);
        intent.putExtra("USER_ID", databaseAPI.getUserId());
        startActivity(intent);
    }

}