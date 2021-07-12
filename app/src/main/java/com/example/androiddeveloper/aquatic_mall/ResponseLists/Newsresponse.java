package com.example.androiddeveloper.aquatic_mall.ResponseLists;



        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Newsresponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("heading")
    @Expose
    private String heading;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("numberlikes")
    @Expose
    private Integer numberlikes;
    @SerializedName("numbercomments")
    @Expose
    private Integer numbercomments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getNumberlikes() {
        return numberlikes;
    }

    public void setNumberlikes(Integer numberlikes) {
        this.numberlikes = numberlikes;
    }

    public Integer getNumbercomments() {
        return numbercomments;
    }

    public void setNumbercomments(Integer numbercomments) {
        this.numbercomments = numbercomments;
    }

}