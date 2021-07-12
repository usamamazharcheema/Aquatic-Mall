package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANDROID DEVELOPER on 15/03/2018.
 */



public class GetchatresponseList {

    @SerializedName("getchatresponse")
    @Expose
    private List<Getchatresponse> getchatresponse = null;

    public List<Getchatresponse> getGetchatresponse() {
        return getchatresponse;
    }

    public void setGetchatresponse(List<Getchatresponse> getchatresponse) {
        this.getchatresponse = getchatresponse;
    }

}