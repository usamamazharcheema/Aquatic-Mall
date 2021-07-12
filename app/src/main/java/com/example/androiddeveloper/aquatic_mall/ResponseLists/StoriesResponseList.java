package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANDROID DEVELOPER on 20/04/2018.
 */

public class StoriesResponseList {
    @SerializedName("server_response")
    private List<Storiesresponse> Results;

    public List<Storiesresponse> getResults() {
        return Results;
    }

    public void setResults(List<Storiesresponse> results) {
        Results = results;
    }
}