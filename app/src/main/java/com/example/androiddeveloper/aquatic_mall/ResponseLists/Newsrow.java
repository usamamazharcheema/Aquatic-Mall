package com.example.androiddeveloper.aquatic_mall.ResponseLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Newsrow {

@SerializedName("rows")
@Expose
private Integer rows;

public Integer getRows() {
return rows;
}

public void setRows(Integer rows) {
this.rows = rows;
}

}