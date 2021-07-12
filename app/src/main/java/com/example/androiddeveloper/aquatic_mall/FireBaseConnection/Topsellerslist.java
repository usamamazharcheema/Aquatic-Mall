package com.example.androiddeveloper.aquatic_mall.FireBaseConnection;

/**
 * Created by ANDROID DEVELOPER on 09/03/2018.
 */



import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Topsellerslist {

    @SerializedName("topsellesr")
    @Expose
    private List<Topsellesr> topsellesr = null;

    public List<Topsellesr> getTopsellesr() {
        return topsellesr;
    }

    public void setTopsellesr(List<Topsellesr> topsellesr) {
        this.topsellesr = topsellesr;
    }

}