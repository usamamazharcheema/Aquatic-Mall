package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Investorloginnew {

@SerializedName("id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("phonenumber")
@Expose
private String phonenumber;
@SerializedName("cnic")
@Expose
private String cnic;
@SerializedName("code")
@Expose
private String code;
@SerializedName("email")
@Expose
private String email;
@SerializedName("img")
@Expose
private String img;
@SerializedName("sonof")
@Expose
private String sonof;
@SerializedName("address")
@Expose
private String address;
@SerializedName("profession")
@Expose
private String profession;
@SerializedName("office")
@Expose
private String office;
@SerializedName("date")
@Expose
private String date;
@SerializedName("success")
@Expose
private String success;

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

public String getCnic() {
return cnic;
}

public void setCnic(String cnic) {
this.cnic = cnic;
}

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getImg() {
return img;
}

public void setImg(String img) {
this.img = img;
}

public String getSonof() {
return sonof;
}

public void setSonof(String sonof) {
this.sonof = sonof;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getProfession() {
return profession;
}

public void setProfession(String profession) {
this.profession = profession;
}

public String getOffice() {
return office;
}

public void setOffice(String office) {
this.office = office;
}

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

public String getSuccess() {
return success;
}

public void setSuccess(String success) {
this.success = success;
}

}