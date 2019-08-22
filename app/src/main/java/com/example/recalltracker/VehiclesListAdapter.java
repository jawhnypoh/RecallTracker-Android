package com.example.recalltracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recalltracker.Models.VehicleItem;

import java.util.List;


public class VehiclesListAdapter extends RecyclerView.Adapter<VehiclesListAdapter.vehiclesListViewHolder> {

    private static final String TAG = "VehiclesListAdapter: ";

    private List<VehicleItem> list;
    Context mContext;

    public VehiclesListAdapter(List<VehicleItem> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public VehiclesListAdapter.vehiclesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vins_item, parent, false);
        vehiclesListViewHolder vehiclesListViewHolder = new vehiclesListViewHolder(view);

        return vehiclesListViewHolder;
    }

    @Override
    public void onBindViewHolder(vehiclesListViewHolder holder, int position) {
        VehicleItem mList = list.get(position);

        holder.carName.setText(mList.getFullName());
        holder.carVIN.setText("VIN: " + mList.getCarVIN());
    }

    @Override
    public int getItemCount() {
        int arraySize = 0;

        try{
            if(list.size() == 0){
                arraySize = 0;
            }
            else{
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
