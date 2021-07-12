package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 03/02/2018.
 */


import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class NewsResponseLists  {

    @SerializedName("newsresponse")
    @Expose
    private List<Newsresponse> newsresponse = null;

    public List<Newsresponse> getNewsresponse() {
        return newsresponse;
    }

    public void setNewsresponse(List<Newsresponse> newsresponse) {
        this.newsresponse = newsresponse;
    }

}