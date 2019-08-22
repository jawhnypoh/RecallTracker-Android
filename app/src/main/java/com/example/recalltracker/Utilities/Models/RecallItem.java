package com.example.recalltracker.Utilities.Models;

public class RecallItem {

    private String Note, Campaign_Number, Defect, Consequences;

    public String getCampaign_Number() {
        return Campaign_Number;
    }

    public String getConsequences() {
        return Consequences;
    }

    public String getDefect() {
        return Defect;
    }

    public String getNote() {
        return Note;
    }

    public void setCampaign_Number(String campaign_Number) {
        Campaign_Number = campaign_Number;
    }

    public void setConsequences(String consequences) {
        Consequences = consequences;
    }

    public void setDefect(String defect) {
        Defect = defect;
    }

    public void setNote(String note) {
        Note = note;
    }

}
