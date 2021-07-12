package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANDROID DEVELOPER on 15/03/2018.
 */



public class AdminResponseLists {

    @SerializedName("adminslist")
    @Expose
    private List<Adminslist> adminslist = null;

    public List<Adminslist> getAdminslist() {
        return adminslist;
    }

    public void setAdminslist(List<Adminslist> adminslist) {
        this.adminslist = adminslist;
    }

}