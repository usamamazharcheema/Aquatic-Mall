package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 12/03/2018.
 */



import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class NotificationdataList {

    @SerializedName("notificationdataresponse")
    @Expose
    private List<Notificationdataresponse> notificationdataresponse = null;

    public List<Notificationdataresponse> getNotificationdataresponse() {
        return notificationdataresponse;
    }

    public void setNotificationdataresponse(List<Notificationdataresponse> notificationdataresponse) {
        this.notificationdataresponse = notificationdataresponse;
    }

}