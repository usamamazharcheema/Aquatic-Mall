package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 18/04/2018.
 */



import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class InvestorRentalYearsList {

    @SerializedName("investorrentalyears")
    @Expose
    private List<Investorrentalyear> investorrentalyears = null;

    public List<Investorrentalyear> getInvestorrentalyears() {
        return investorrentalyears;
    }

    public void setInvestorrentalyears(List<Investorrentalyear> investorrentalyears) {
        this.investorrentalyears = investorrentalyears;
    }

}
