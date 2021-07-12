package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 13/02/2018.
 */


import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class PhoneresponseList {

    @SerializedName("investorephone")
    @Expose
    private List<Investorephone> investorephone = null;

    public List<Investorephone> getInvestorephone() {
        return investorephone;
    }

    public void setInvestorephone(List<Investorephone> investorephone) {
        this.investorephone = investorephone;
    }

}