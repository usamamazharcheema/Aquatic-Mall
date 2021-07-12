package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Opencomment {

@SerializedName("newsid")
@Expose
private String newsid;
@SerializedName("id")
@Expose
private String id;
@SerializedName("email")
@Expose
private String email;
@SerializedName("name")
@Expose
private String name;
@SerializedName("commentsdetail")
@Expose
private String commentsdetail;
@SerializedName("detail")
@Expose
private String detail;

    @SerializedName("img")
    @Expose
    private String img;
@SerializedName("numberlikes")
@Expose
private Integer numberlikes;

public String getNewsid() {
return newsid;
}

public void setNewsid(String newsid) {
this.newsid = newsid;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getCommentsdetail() {
return commentsdetail;
}

public void setCommentsdetail(String commentsdetail) {
this.commentsdetail = commentsdetail;
}

public String getImg() {
return img;
}

public void setImg(String img) {
this.img = img;
}

public Integer getNumberlikes() {
return numberlikes;
}

public void setNumberlikes(Integer numberlikes) {
this.numberlikes = numberlikes;
}

}