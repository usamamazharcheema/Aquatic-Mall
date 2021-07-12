

package com.example.androiddeveloper.aquatic_mall.ResponseLists;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Trendingresponse {

    @SerializedName("floor")
    @Expose
    private String floor;
    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("date")
    @Expose
    private String date;

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}