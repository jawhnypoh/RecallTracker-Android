package com.example.recalltracker.Utils;

import com.example.recalltracker.Models.VehicleItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseAPI {

    private String userId;
    private CollectionReference users;

    public DatabaseAPI(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        this.users = db.collection("users");
        this.userId = userId;
    }

    public void getUser(OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        DocumentReference docRef = users.document(userId);
        docRef.get().addOnCompleteListener(onCompleteListener);
    }

    public void updateUser(final Map<String, Object> data) {
        DocumentReference docRef = users.document(userId);
        data.put("updated", System.currentTimeMillis());
        docRef.set(data, SetOptions.merge());
    }

    public void addVehicle(final VehicleItem vehicle) {
        Map<String, Object> data = new HashMap<>();
        ArrayList<VehicleItem> vehicles = new ArrayList<>();
        vehicles.add(vehicle);
        data.put("vehicles", vehicles);

        updateUser(data);
    }

    public static Map<String, Object> createUserData(String pushToken, boolean notificationsEnabled, ArrayList<VehicleItem> vehicles) {
        Map<String, Object> data = new HashMap<>();
        data.put("pushToken", pushToken);
        data.put("notificationsEnabled", notificationsEnabled);
        data.put("vehicles", vehicles);
        return data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CollectionReference getUsers() {
        return users;
    }

    public void setUsers(CollectionReference users) {
        this.users = users;
    }
}
