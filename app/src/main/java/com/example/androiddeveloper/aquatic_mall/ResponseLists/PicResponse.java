package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 07/02/2018.
 */



import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class PicResponse {

    @SerializedName("featureimgresponse")
    @Expose
    private List<Featureimgresponse> featureimgresponse = null;

    public List<Featureimgresponse> getFeatureimgresponse() {
        return featureimgresponse;
    }

    public void setFeatureimgresponse(List<Featureimgresponse> featureimgresponse) {
        this.featureimgresponse = featureimgresponse;
    }

}