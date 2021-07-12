package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Featureimgresponse {

@SerializedName("pic")
@Expose
private String pic;

public String getPic() {
return pic;
}

public void setPic(String pic) {
this.pic = pic;
}

}