package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Investorephone {

@SerializedName("phone")
@Expose
private String phone;

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

}