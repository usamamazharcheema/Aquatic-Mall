package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getchatresponse {

@SerializedName("message")
@Expose
private String message;
@SerializedName("sender")
@Expose
private String sender;

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public String getSender() {
return sender;
}

public void setSender(String sender) {
this.sender = sender;
}

}