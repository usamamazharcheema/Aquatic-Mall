package com.example.androiddeveloper.aquatic_mall.fragments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fm on 2/5/2018.
 */

public class feature_model {
    @SerializedName("id")
    private String id;
    @SerializedName("featurename")
    private String featurename;
    @SerializedName("floor")
    private String floor;
    @SerializedName("price")
    private String price;
    @SerializedName("sqfeet")
    private String sqfeet;
    @SerializedName("floorno")
    private String floorno;
    @SerializedName("shopno")
    private String shopno;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeaturename() {
        return featurename;
    }

    public void setFeaturename(String featurename) {
        this.featurename = featurename;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getSqfeet() {
        return sqfeet;
    }

    public void setSqfeet(String sqfeet) {
        this.sqfeet = sqfeet;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFloorno() {
        return floorno;
    }

    public void setFloorno(String floorno) {
        this.floorno = floorno;
    }

    public String getShopno() {
        return shopno;
    }

    public void setShopno(String shopno) {
        this.shopno = shopno;
    }
}
