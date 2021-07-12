package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("phonenumber")
@Expose
private String phonenumber;
@SerializedName("success")
@Expose
private String success;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPhonenumber() {
return phonenumber;
}

public void setPhonenumber(String phonenumber) {
this.phonenumber = phonenumber;
}

public String getSuccess() {
return success;
}

public void setSuccess(String success) {
this.success = success;
}

}