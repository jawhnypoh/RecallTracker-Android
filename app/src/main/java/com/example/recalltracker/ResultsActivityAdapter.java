package com.example.recalltracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recalltracker.Utilities.Models.RecallItem;

import java.util.List;
public class ResultsActivityAdapter extends RecyclerView.Adapter<ResultsActivityAdapter.resultsActivityViewHolder> {
    private static final String TAG = "ResultsActivityAdapter: ";

    private List<RecallItem> list;
    Context mContext;

    public ResultsActivityAdapter(List<RecallItem> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ResultsActivityAdapter.resultsActivityViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.results_item, parent, false);
        resultsActivityViewHolder resultsActivityViewHolder = new resultsActivityViewHolder(view);

        return resultsActivityViewHolder;
    }

    @Override
    public void onBindViewHolder(resultsActivityViewHolder holder, int position) {
        RecallItem mList = list.get(position);
        holder.Campaign_Number.setText(mList.getCampaign_Number());
        holder.Notes.setText(mList.getNote());
        holder.Defect.setText(mList.getDefect());
        holder.Consequence.setText(mList.getConsequences());
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
        catch(Exception e){
            arraySize = list.size();
        }
        return arraySize;
    }

    class resultsActivityViewHolder extends RecyclerView.ViewHolder {
        TextView Campaign_Number;
        TextView Notes;
        TextView Defect;
        TextView Consequence;

        public resultsActivityViewHolder(View itemView) {
            super(itemView);
            Campaign_Number = itemView.findViewById(R.id.MFG_Campaign_Number);
            Notes = itemView.findViewById(R.id.Notes);
            Defect = itemView.findViewById(R.id.Defect);
            Consequence = itemView.findViewById(R.id.Consequences);
        }
    }
}
