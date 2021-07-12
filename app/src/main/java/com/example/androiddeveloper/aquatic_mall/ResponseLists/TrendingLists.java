package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 22/01/2018.
 */

import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class TrendingLists {

    @SerializedName("trendingresponse")
    @Expose
    private List<Trendingresponse> trendingresponse = null;

    public List<Trendingresponse> getTrendingresponse() {
        return trendingresponse;
    }

    public void setTrendingresponse(List<Trendingresponse> trendingresponse) {
        this.trendingresponse = trendingresponse;
    }

}