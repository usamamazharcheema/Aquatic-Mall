package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 05/04/2018.
 */


import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class InvestorLoginnewLIsts {

    @SerializedName("Investorloginnew")
    @Expose
    private List<Investorloginnew> investorloginnew = null;

    public List<Investorloginnew> getInvestorloginnew() {
        return investorloginnew;
    }

    public void setInvestorloginnew(List<Investorloginnew> investorloginnew) {
        this.investorloginnew = investorloginnew;
    }

}