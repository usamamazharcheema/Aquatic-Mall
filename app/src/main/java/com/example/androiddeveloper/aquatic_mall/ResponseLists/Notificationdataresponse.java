package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notificationdataresponse {

@SerializedName("id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("cnic")
@Expose
private String cnic;
@SerializedName("unitno")
@Expose
private String unitno;
@SerializedName("shopcode")
@Expose
private String shopcode;
@SerializedName("floorno")
@Expose
private String floorno;
@SerializedName("status")
@Expose
private String status;
@SerializedName("item")
@Expose
private String item;

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

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public String getCnic() {
return cnic;
}

public void setCnic(String cnic) {
this.cnic = cnic;
}

public String getUnitno() {
return unitno;
}

public void setUnitno(String unitno) {
this.unitno = unitno;
}

public String getShopcode() {
return shopcode;
}

public void setShopcode(String shopcode) {
this.shopcode = shopcode;
}

public String getFloorno() {
return floorno;
}

public void setFloorno(String floorno) {
this.floorno = floorno;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getItem() {
return item;
}

public void setItem(String item) {
this.item = item;
}

}