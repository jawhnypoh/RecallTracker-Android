package com.example.recalltracker.Utils;

import com.example.recalltracker.Models.VehicleItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseAPI {

    public static void getUser(CollectionReference users, String uid, OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        DocumentReference docRef = users.document(uid);
        docRef.get().addOnCompleteListener(onCompleteListener);
    }

    public static void updateUser(final CollectionReference users, final String uid, final Map<String, Object> data) {
        DocumentReference docRef = users.document(uid);
        data.put("updated", System.currentTimeMillis());
        docRef.set(data, SetOptions.merge());
    }

    public static void addVehicle(final CollectionReference users, final String uid, final VehicleItem vehicle) {
        Map<String, Object> data = new HashMap<>();
        ArrayList<VehicleItem> vehicles = new ArrayList<VehicleItem>();
        vehicles.add(vehicle);
        data.put("vehicles", vehicles);

        updateUser(users, uid, data);
    }

    public static Map<String, Object> createUserData(String pushToken, boolean notificationsEnabled, ArrayList<VehicleItem> vehicles) {
        Map<String, Object> data = new HashMap<>();
        data.put("pushToken", pushToken);
        data.put("notificationsEnabled", notificationsEnabled);
        data.put("vehicles", vehicles);
        return data;
    }

}
