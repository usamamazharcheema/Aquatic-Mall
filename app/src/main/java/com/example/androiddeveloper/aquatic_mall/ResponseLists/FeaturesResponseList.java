package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 07/02/2018.
 */


import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class FeaturesResponseList {

    @SerializedName("featureresponse")
    @Expose
    private List<Featureresponse> featureresponse = null;

    public List<Featureresponse> getFeatureresponse() {
        return featureresponse;
    }

    public void setFeatureresponse(List<Featureresponse> featureresponse) {
        this.featureresponse = featureresponse;
    }

}
