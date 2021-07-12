package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 15/02/2018.
 */




import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class LoginResponseList {

    @SerializedName("login_response")
    @Expose
    private List<LoginResponse> loginResponse = null;

    public List<LoginResponse> getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(List<LoginResponse> loginResponse) {
        this.loginResponse = loginResponse;
    }

}