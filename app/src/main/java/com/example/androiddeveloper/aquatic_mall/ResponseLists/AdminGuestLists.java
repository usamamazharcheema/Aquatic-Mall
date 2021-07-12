package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 16/03/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class AdminGuestLists {

    @SerializedName("adminsslist")
    @Expose
    private List<Adminsslist> adminsslist = null;

    public List<Adminsslist> getAdminsslist() {
        return adminsslist;
    }

    public void setAdminsslist(List<Adminsslist> adminsslist) {
        this.adminsslist = adminsslist;
    }

}