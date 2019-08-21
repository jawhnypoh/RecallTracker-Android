package com.example.recalltracker.Utilities;

import android.net.Uri;
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

    private static final String VIN_API_URL = "http://api.carmd.com/v3.0/decode";

    private static final String VIN_API_PARTNER_TOKEN = "51f30c30efda47cdae96033bda957101";

    private static final String VIN_API_AUTH_TOKEN = "MzUyY2RiZjAtZWFlMC00YWYzLWEyOWQtNTQwMzdmOGI4OGY4";

    public static String buildVINURL(String vinQuery) {
        Uri.Builder builder = Uri.parse(VIN_API_URL).buildUpon();
        builder.appendQueryParameter("vin", vinQuery);
        Log.d(TAG, "buildVINURL: " + builder.build().toString());

        return builder.build().toString();
    }

    public interface AsyncResponse {
        void processFinish(Integer year, String make, String model);
    }

    public static class placeIdTask extends AsyncTask<String, Void, JSONObject> {
        // Callback Interface
        public AsyncResponse delegate = null;

        public placeIdTask(AsyncResponse asyncResponse) {
            //Assigning call back interface through constructor
            delegate = asyncResponse;
        }

        @Override
        protected JSONObject doInBackground(String...params) {
            JSONObject jsonVIN = null;
            try {
                Log.d(TAG, "params[0]: " + params[0]);

                jsonVIN = getVINJSON(params[0]);

            } catch (Exception e ) {
                Log.e(TAG, "doInBackground(): Cannot process JSON results ", e);
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
                else {
                    Log.e(TAG, "json is null");
                }
            } catch (JSONException e) {
                Log.e(TAG, "Cannot process JSON Response ", e);
            }
        }

        public static JSONObject getVINJSON(String queryURL) {
            try {
                URL url = new URL(queryURL);

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                connection.setRequestProperty("Authorization", "Basic " + VIN_API_AUTH_TOKEN);
                connection.setRequestProperty("Partner-Token", VIN_API_PARTNER_TOKEN);

                int status = connection.getResponseCode();
                if(status != 200) {
                    Log.e(TAG, "bad response code, status is: " + status);
                }

                Log.d(TAG, connection.toString());

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                StringBuffer json = new StringBuffer(1024);
                String tmp="";
                while((tmp=reader.readLine())!=null)
                    json.append(tmp).append("\n");
                reader.close();

                JSONObject data = new JSONObject(json.toString());

                Log.d(TAG, "data: " + data.toString());

                return data;

            } catch (Exception e) {
                Log.e(TAG, "Error: ", e);
                return null;
            }
        }
    }
}
