package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likeslist {

@SerializedName("newsid")
@Expose
private String newsid;
@SerializedName("email")
@Expose
private String email;

public String getNewsid() {
return newsid;
}

public void setNewsid(String newsid) {
this.newsid = newsid;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

}