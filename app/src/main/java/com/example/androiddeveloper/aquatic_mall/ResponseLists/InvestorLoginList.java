package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 15/02/2018.
 */



import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class InvestorLoginList {

    @SerializedName("server_response")
    @Expose
    private List<ServerResponse> serverResponse = null;

    public List<ServerResponse> getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(List<ServerResponse> serverResponse) {
        this.serverResponse = serverResponse;
    }

}