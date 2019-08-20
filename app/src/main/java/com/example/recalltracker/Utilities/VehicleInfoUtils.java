package com.example.recalltracker.Utilities;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VehicleInfoUtils {

    private static final String TAG = "VehicleInfoUtils: ";

    private static final String VIN_API_URL = "http://api.carmd.com/v3.0/decode?vin=";

    private static final String VIN_API_PARTNER_TOKEN = "76d59ce637ac4979bd1ad37da4560bb5";

    private static final String VIN_API_AUTH_TOKEN = "Basic N2U1ZTcyNzYtZGRhZi00NGJkLTk1ZWYtZGMzMjQ2MmU2NTRh";


    public interface AsyncResponse {

        void processFinish();
    }

    public static class placeIdTask extends AsyncTask<String, Void, JSONObject> {

        // Callback Interface
        public AsyncResponse delegate = null;

        public placeIdTask(AsyncResponse asyncResponse) {
            //Assigning call back interface through constructor
            delegate = asyncResponse;
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            JSONObject jsonVIN = null;
            try {
                jsonVIN = getVINJSON(params[0]);
            } catch (Exception e ) {
                Log.e(TAG, "Cannot process JSON results ", e);
            }

            return jsonVIN;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                if(json != null) {
                    JSONObject details = json.getJSONObject("data");

                    Integer year = details.getInt("year");
                    String make = details.getString("make");
                    String model = details.getString("model");

                    delegate.processFinish(year, make, model);
                }
            } catch (JSONException e) {
                Log.e(TAG, "Cannot process JSON Response ", e);
            }
        }

        public static JSONObject getVINJSON(String vinNumber) {
            try {
                URL url = new URL(String.format(VIN_API_URL, vinNumber));

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                connection.addRequestProperty("x-api-key", VIN_API_AUTH_TOKEN);
                connection.addRequestProperty("x-api-token", VIN_API_PARTNER_TOKEN);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                StringBuffer json = new StringBuffer(1024);
                String tmp="";
                while((tmp=reader.readLine())!=null)
                    json.append(tmp).append("\n");
                reader.close();

                JSONObject data = new JSONObject(json.toString());

                // This value will be 404 if the request was not successful
                if(data.getInt("cod") != 200){
                    return null;
                }

                return data;

            } catch (Exception e) {
                return null;
            }
        }
    }
}
