package com.may.maystream.shopngai.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by May on 8/22/2017.
 */

public class TblCategory {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
//    private String path_pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getPath_pic() {
//        return path_pic;
//    }
//
//    public void setPath_pic(String path_pic) {
//        this.path_pic = path_pic;
//    }
}
