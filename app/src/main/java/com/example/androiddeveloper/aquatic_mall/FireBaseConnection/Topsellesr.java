package com.example.androiddeveloper.aquatic_mall.FireBaseConnection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topsellesr {

@SerializedName("email")
@Expose
private String email;

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

}