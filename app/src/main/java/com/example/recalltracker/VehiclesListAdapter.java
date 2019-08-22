package com.example.recalltracker;

import android.content.Context;
import android.util.Log;
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

    private static ClickListener clickListener;

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
        Log.d(TAG, "holder.carName: " + mList.getFullName());
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

    public static class vehiclesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView carName;
        TextView carVIN;

        public vehiclesListViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            carName = itemView.findViewById(R.id.vehicle_name);
            carVIN = itemView.findViewById(R.id.vin_number);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        VehiclesListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
