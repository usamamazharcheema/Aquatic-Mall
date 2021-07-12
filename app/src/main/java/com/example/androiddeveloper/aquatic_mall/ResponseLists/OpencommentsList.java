package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 14/02/2018.
 */



import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class OpencommentsList {

    @SerializedName("opencomments")
    @Expose
    private List<Opencomment> opencomments = null;

    public List<Opencomment> getOpencomments() {
        return opencomments;
    }

    public void setOpencomments(List<Opencomment> opencomments) {
        this.opencomments = opencomments;
    }

}