package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fm on 2/7/2018.
 */

public class MainResponselist {
    @SerializedName("server_response")
    private List<MainViewResponse> Results;

    public List<MainViewResponse> getResults() {
        return Results;
    }

    public void setResults(List<MainViewResponse> results) {
        Results = results;
    }
}

