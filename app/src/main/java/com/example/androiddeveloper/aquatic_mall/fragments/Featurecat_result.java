package com.example.androiddeveloper.aquatic_mall.fragments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fm on 2/5/2018.
 */

public class Featurecat_result {
    @SerializedName("server_response")
    private List<feature_model> Results;

    public List<feature_model> getResults() {
        return Results;
    }

    public void setResults(List<feature_model> results) {
        Results = results;
    }
}
