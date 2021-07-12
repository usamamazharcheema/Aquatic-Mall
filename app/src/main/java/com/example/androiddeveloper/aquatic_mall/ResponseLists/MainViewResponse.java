package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fm on 2/7/2018.
 */

public class MainViewResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("icodeno")
    @Expose
    private String codeno;
    @SerializedName("ishopno")
    @Expose
    private String shopno;
    @SerializedName("ishopcode")
    @Expose
    private String shopcode;
    @SerializedName("ifloor")
    @Expose
    private String ifloor;
    @SerializedName("irate")
    @Expose
    private String irate;

    public String getIarea() {
        return iarea;
    }

    public void setIarea(String iarea) {
        this.iarea = iarea;
    }

    public String getCodeno() {
        return codeno;
    }

    public void setCodeno(String codeno) {
        this.codeno = codeno;
    }

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

    public String getIfloor() {
        return ifloor;
    }

    public void setIfloor(String ifloor) {
        this.ifloor = ifloor;
    }

    public String getIrate() {
        return irate;
    }

    public void setIrate(String irate) {
        this.irate = irate;
    }

    public String getInstallments() {
        return installments;
    }

    public void setInstallments(String installments) {
        this.installments = installments;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("iarea")
    @Expose

    private String iarea;
    @SerializedName("iinstallments")
    @Expose
    private String installments;
    @SerializedName("iamount")
    @Expose
    private String amount;
    @SerializedName("iduedate")
    @Expose
    private String duedate;
    @SerializedName("istatus")
    @Expose
    private String status;

    @SerializedName("floor")
    @Expose
    private String floor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @SerializedName("rate")
    @Expose
    private String rate;
}
