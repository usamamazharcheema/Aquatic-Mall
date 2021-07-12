package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notificationresponse {

@SerializedName("unitno")
@Expose
private String unitno;
@SerializedName("shopcode")
@Expose
private String shopcode;
@SerializedName("floorno")
@Expose
private String floorno;
@SerializedName("item")
@Expose
private String item;

    @SerializedName("id")
    @Expose
    private String id;

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

public String getItem() {
return item;
}

public void setItem(String item) {
this.item = item;
}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}