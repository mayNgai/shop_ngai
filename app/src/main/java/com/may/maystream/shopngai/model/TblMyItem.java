package com.may.maystream.shopngai.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by May on 8/28/2017.
 */
@DatabaseTable(tableName="TblMyItem")
public class TblMyItem implements Serializable {
    @DatabaseField(id = true, useGetSet = true)
    private String guid;

    @DatabaseField( useGetSet = true)
    private String success;

    @DatabaseField( useGetSet = true)
    private String message;

    @DatabaseField( useGetSet = true)
    private String o_id;

    @DatabaseField( useGetSet = true)
    private String o_name;

    @SerializedName("o_c_id")
    @Expose
    @DatabaseField( useGetSet = true)
    private String o_c_id;

    @SerializedName("o_vendor_id")
    @Expose
    @DatabaseField( useGetSet = true)
    private String o_vendor_id;

    @DatabaseField( useGetSet = true)
    private String o_start_date;

    @DatabaseField( useGetSet = true)
    private String o_end_date;

    @DatabaseField( useGetSet = true)
    private String o_price;

    @DatabaseField( useGetSet = true)
    private String o_discount;

    @DatabaseField( useGetSet = true)
    private String o_ratting;

    @DatabaseField( useGetSet = true)
    private String o_stock;

    @DatabaseField( useGetSet = true)
    private String o_category;

    @DatabaseField( useGetSet = true)
    private String o_condition;

    @DatabaseField( useGetSet = true)
    private String o_like;

    @DatabaseField( useGetSet = true)
    private String o_wholesale_price;

    @DatabaseField( useGetSet = true)
    private String o_brand;

    @DatabaseField( useGetSet = true)
    private String o_ship_from;

    @DatabaseField( useGetSet = true)
    private String o_detail;

    @DatabaseField( useGetSet = true)
    private String o_count_ratting;

    @DatabaseField( useGetSet = true)
    private String 	o_pic_name;

    public String getO_pic_name() {
        return o_pic_name;
    }

    public void setO_pic_name(String o_pic_name) {
        this.o_pic_name = o_pic_name;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getO_name() {
        return o_name;
    }

    public void setO_name(String o_name) {
        this.o_name = o_name;
    }

    public String getO_c_id() {
        return o_c_id;
    }

    public void setO_c_id(String o_c_id) {
        this.o_c_id = o_c_id;
    }

    public String getO_vendor_id() {
        return o_vendor_id;
    }

    public void setO_vendor_id(String o_vendor_id) {
        this.o_vendor_id = o_vendor_id;
    }

    public String getO_start_date() {
        return o_start_date;
    }

    public void setO_start_date(String o_start_date) {
        this.o_start_date = o_start_date;
    }

    public String getO_end_date() {
        return o_end_date;
    }

    public void setO_end_date(String o_end_date) {
        this.o_end_date = o_end_date;
    }

    public String getO_price() {
        return o_price;
    }

    public void setO_price(String o_price) {
        this.o_price = o_price;
    }

    public String getO_discount() {
        return o_discount;
    }

    public void setO_discount(String o_discount) {
        this.o_discount = o_discount;
    }

    public String getO_ratting() {
        return o_ratting;
    }

    public void setO_ratting(String o_ratting) {
        this.o_ratting = o_ratting;
    }

    public String getO_like() {
        return o_like;
    }

    public void setO_like(String o_like) {
        this.o_like = o_like;
    }

    public String getO_brand() {
        return o_brand;
    }

    public void setO_brand(String o_brand) {
        this.o_brand = o_brand;
    }

    public String getO_ship_from() {
        return o_ship_from;
    }

    public void setO_ship_from(String o_ship_from) {
        this.o_ship_from = o_ship_from;
    }

    public String getO_detail() {
        return o_detail;
    }

    public void setO_detail(String o_detail) {
        this.o_detail = o_detail;
    }

    public String getO_count_ratting() {
        return o_count_ratting;
    }

    public void setO_count_ratting(String o_count_ratting) {
        this.o_count_ratting = o_count_ratting;
    }

    public String getO_stock() {
        return o_stock;
    }

    public void setO_stock(String o_stock) {
        this.o_stock = o_stock;
    }

    public String getO_category() {
        return o_category;
    }

    public void setO_category(String o_category) {
        this.o_category = o_category;
    }

    public String getO_condition() {
        return o_condition;
    }

    public void setO_condition(String o_condition) {
        this.o_condition = o_condition;
    }

    public String getO_wholesale_price() {
        return o_wholesale_price;
    }

    public void setO_wholesale_price(String o_wholesale_price) {
        this.o_wholesale_price = o_wholesale_price;
    }
}
