package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 06/04/2018.
 */


import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class NewInvestorDetailLists {

    @SerializedName("newinvestordetaillist")
    @Expose
    private List<Newinvestordetaillist> newinvestordetaillist = null;

    public List<Newinvestordetaillist> getNewinvestordetaillist() {
        return newinvestordetaillist;
    }

    public void setNewinvestordetaillist(List<Newinvestordetaillist> newinvestordetaillist) {
        this.newinvestordetaillist = newinvestordetaillist;
    }

}