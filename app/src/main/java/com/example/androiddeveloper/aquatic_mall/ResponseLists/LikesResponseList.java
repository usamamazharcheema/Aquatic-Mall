package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 15/02/2018.
 */

import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class LikesResponseList {

    @SerializedName("likeslist")
    @Expose
    private List<Likeslist> likeslist = null;

    public List<Likeslist> getLikeslist() {
        return likeslist;
    }

    public void setLikeslist(List<Likeslist> likeslist) {
        this.likeslist = likeslist;
    }

}