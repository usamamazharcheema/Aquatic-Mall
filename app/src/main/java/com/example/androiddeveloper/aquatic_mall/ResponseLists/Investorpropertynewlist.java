package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Investorpropertynewlist {

@SerializedName("shopno")
@Expose
private String shopno;
@SerializedName("shopcode")
@Expose
private String shopcode;

    @SerializedName("payment")
    @Expose
    private String payment;

public String getShopno() {
return shopno;
}

public void setShopno(String shopno) {
this.shopno = shopno;
}

public String getShopcode() {
return shopcode;
}

public void setShopcode(String shopcode) {
this.shopcode = shopcode;
}



    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

}