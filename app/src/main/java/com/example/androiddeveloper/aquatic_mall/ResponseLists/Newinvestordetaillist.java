package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Newinvestordetaillist {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("in_fcode")
    @Expose
    private String inFcode;
    @SerializedName("shopcode_fid")
    @Expose
    private String shopcodeFid;
    @SerializedName("iinstallments")
    @Expose
    private String iinstallments;
    @SerializedName("iamount")
    @Expose
    private String iamount;
    @SerializedName("iduedate")
    @Expose
    private String iduedate;
    @SerializedName("istatus")
    @Expose
    private String istatus;
    @SerializedName("cash")
    @Expose
    private String cash;
    @SerializedName("amountpaid")
    @Expose
    private String amountpaid;
    @SerializedName("floor")
    @Expose
    private String floor;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("totalpropertyamount")
    @Expose
    private String totalpropertyamount;
    @SerializedName("downpaymentamount")
    @Expose
    private String downpaymentamount;
    @SerializedName("propertydate")
    @Expose
    private String propertydate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInFcode() {
        return inFcode;
    }

    public void setInFcode(String inFcode) {
        this.inFcode = inFcode;
    }

    public String getShopcodeFid() {
        return shopcodeFid;
    }

    public void setShopcodeFid(String shopcodeFid) {
        this.shopcodeFid = shopcodeFid;
    }

    public String getIinstallments() {
        return iinstallments;
    }

    public void setIinstallments(String iinstallments) {
        this.iinstallments = iinstallments;
    }

    public String getIamount() {
        return iamount;
    }

    public void setIamount(String iamount) {
        this.iamount = iamount;
    }

    public String getIduedate() {
        return iduedate;
    }

    public void setIduedate(String iduedate) {
        this.iduedate = iduedate;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(String amountpaid) {
        this.amountpaid = amountpaid;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTotalpropertyamount() {
        return totalpropertyamount;
    }

    public void setTotalpropertyamount(String totalpropertyamount) {
        this.totalpropertyamount = totalpropertyamount;
    }

    public String getDownpaymentamount() {
        return downpaymentamount;
    }

    public void setDownpaymentamount(String downpaymentamount) {
        this.downpaymentamount = downpaymentamount;
    }

    public String getPropertydate() {
        return propertydate;
    }

    public void setPropertydate(String propertydate) {
        this.propertydate = propertydate;
    }

}