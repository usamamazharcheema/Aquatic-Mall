package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 06/04/2018.
 */


import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class NewInvestyorLIsts {

    @SerializedName("investorpropertynewlist")
    @Expose
    private List<Investorpropertynewlist> investorpropertynewlist = null;

    public List<Investorpropertynewlist> getInvestorpropertynewlist() {
        return investorpropertynewlist;
    }

    public void setInvestorpropertynewlist(List<Investorpropertynewlist> investorpropertynewlist) {
        this.investorpropertynewlist = investorpropertynewlist;
    }

}