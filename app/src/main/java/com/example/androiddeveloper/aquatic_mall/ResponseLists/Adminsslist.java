package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Adminsslist {

@SerializedName("email")
@Expose
private String email;
@SerializedName("name")
@Expose
private String name;
@SerializedName("token")
@Expose
private String token;
@SerializedName("message")
@Expose
private String message;

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}