package com.example.recalltracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
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

                        // Log and toast
                        Log.d(TAG, "Retrieved firebase push token");
                        Toast.makeText(MainActivity.this, "Retrieved firebase push token", Toast.LENGTH_SHORT).show();

                        firebaseSignIn(token);
                    }
                });

    }

    public void firebaseSignIn(final String pushToken) {
        super.onStart();
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                            updateUser(userId, pushToken);
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void createUser(CollectionReference users, String uid, String pushToken) {
        Map<String, Object> newUser = new HashMap<>();
        newUser.put("vin", new ArrayList<String>());
        newUser.put("pushToken", pushToken);
        newUser.put("notificationsEnabled", true);
        users.add(newUser);
    }

    public void updateUser(final String uid, final String pushToken) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference users = db.collection("users");
        final DocumentReference docRef = users.document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (Objects.requireNonNull(document).exists()) {
                        Log.d(TAG, "Updated push token for " + uid);
                        docRef.update("pushToken", pushToken);
                    } else {
                        Log.d(TAG, "User " + uid + " not found. Creating new user...");
                        createUser(users, uid, pushToken);
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}
