package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {

@SerializedName("id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("phonenumber")
@Expose
private String phonenumber;
@SerializedName("cnicno")
@Expose
private String cnicno;
@SerializedName("icodeno")
@Expose
private String icodeno;
@SerializedName("email")
@Expose
private String email;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getPhonenumber() {
return phonenumber;
}

public void setPhonenumber(String phonenumber) {
this.phonenumber = phonenumber;
}

public String getCnicno() {
return cnicno;
}

public void setCnicno(String cnicno) {
this.cnicno = cnicno;
}

public String getIcodeno() {
return icodeno;
}

public void setIcodeno(String icodeno) {
this.icodeno = icodeno;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

}