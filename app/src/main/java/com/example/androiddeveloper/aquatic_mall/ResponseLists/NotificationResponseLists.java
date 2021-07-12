package com.example.androiddeveloper.aquatic_mall.ResponseLists;

/**
 * Created by ANDROID DEVELOPER on 10/03/2018.
 */




import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class NotificationResponseLists {

    @SerializedName("notificationresponse")
    @Expose
    private List<Notificationresponse> notificationresponse = null;

    public List<Notificationresponse> getNotificationresponse() {
        return notificationresponse;
    }

    public void setNotificationresponse(List<Notificationresponse> notificationresponse) {
        this.notificationresponse = notificationresponse;
    }

}