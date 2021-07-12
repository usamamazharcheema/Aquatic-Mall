package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ANDROID DEVELOPER on 20/04/2018.
 */

public class Storiesresponse implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @SerializedName("s_text")
    @Expose

    private String duration;

    public String getS_images() {
        return s_images;
    }

    public void setS_images(String s_images) {
        this.s_images = s_images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("s_images")

    @Expose
    private String s_images;
}