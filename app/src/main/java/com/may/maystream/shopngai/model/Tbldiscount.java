package com.may.maystream.shopngai.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by May on 8/28/2017.
 */

public class Tbldiscount {
    @SerializedName("d_code")
    private String d_code;
    @SerializedName("d_s_date")
    private String d_s_date;
    @SerializedName("d_e_date")
    private String d_e_date;
    @SerializedName("d_name_pic")
    private String d_name_pic;

    public String getD_code() {
        return d_code;
    }

    public void setD_code(String d_code) {
        this.d_code = d_code;
    }

    public String getD_s_date() {
        return d_s_date;
    }

    public void setD_s_date(String d_s_date) {
        this.d_s_date = d_s_date;
    }

    public String getD_e_date() {
        return d_e_date;
    }

    public void setD_e_date(String d_e_date) {
        this.d_e_date = d_e_date;
    }

    public String getD_name_pic() {
        return d_name_pic;
    }

    public void setD_name_pic(String d_name_pic) {
        this.d_name_pic = d_name_pic;
    }
}
