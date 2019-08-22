package com.example.recalltracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recalltracker.Models.VehicleItem;

import java.util.List;


public class VehiclesListAdapter extends RecyclerView.Adapter<VehiclesListAdapter.vehiclesListViewHolder> {

    private static final String TAG = "VehiclesListAdapter: ";

    private List<VehicleItem> list;
    private Context mContext;

    public VehiclesListAdapter(List<VehicleItem> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VehiclesListAdapter.vehiclesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vins_item, parent, false);

        return new vehiclesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(vehiclesListViewHolder holder, int position) {
        VehicleItem mList = list.get(position);
        holder.carName.setText(mList.getFullName());
        holder.carVIN.setText(String.format("VIN: %s", mList.getCarVIN()));
    }

    @Override
    public int getItemCount() {
        int arraySize = 0;

        try {
            if (list.size() != 0) {
                arraySize = list.size();
            }
        }
        catch (Exception e){

        }
        return arraySize;
    }

    class vehiclesListViewHolder extends RecyclerView.ViewHolder {
        TextView carName;
        TextView carVIN;

        public vehiclesListViewHolder(View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.vehicle_name);
            carVIN = itemView.findViewById(R.id.vin_number);
        }
    }
}
