package com.example.recalltracker.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.recalltracker.Models.VehicleItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Firebase {

    private static final String TAG = "Firebase";

    public static void firebaseInit(final Context context) {
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
                        Toast.makeText(context, "Retrieved firebase push token",
                                Toast.LENGTH_SHORT).show();

                        firebaseSignIn(context, token);
                    }
                });
    }

    private static void firebaseSignIn(final Context context, final String pushToken) {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            CollectionReference users = db.collection("users");

                            Map<String, Object> data = new HashMap<>();
                            data.put("pushToken", pushToken);

                            DatabaseAPI.updateUser(users, userId, data);
                        } else {
                            Toast.makeText(context, "Firebase authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
