package com.may.maystream.shopngai.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by may on 9/7/2017.
 */
@DatabaseTable(tableName="TblOrder")
public class TblOrder implements Serializable {
    @DatabaseField( useGetSet = true)
    private String success;

    @DatabaseField( useGetSet = true)
    private String message;

    @DatabaseField(id = true, useGetSet = true)
    private String guid;

    @SerializedName("id")
    @Expose
    @DatabaseField(useGetSet = true)
    private String id;

    @SerializedName("name")
    @Expose
    @DatabaseField(useGetSet = true)
    private String name;

    @SerializedName("vendor")
    @Expose
    @DatabaseField(useGetSet = true)
    private String vendor;

    @SerializedName("price")
    @Expose
    @DatabaseField(useGetSet = true)
    private String price;

    @SerializedName("discount")
    @Expose
    @DatabaseField(useGetSet = true)
    private String discount;

    @SerializedName("stock")
    @Expose
    @DatabaseField(useGetSet = true)
    private String stock;

    @SerializedName("ratting")
    @Expose
    @DatabaseField(useGetSet = true)
    private String ratting;

    @SerializedName("category")
    @Expose
    @DatabaseField(useGetSet = true)
    private String category;

    @SerializedName("likes")
    @Expose
    @DatabaseField(useGetSet = true)
    private String likes;

    @SerializedName("wholesale_price")
    @Expose
    @DatabaseField(useGetSet = true)
    private String wholesale_price;

    @SerializedName("condition")
    @Expose
    @DatabaseField(useGetSet = true)
    private String condition;

    @SerializedName("brand")
    @Expose
    @DatabaseField(useGetSet = true)
    private String brand;

    @SerializedName("ship_from")
    @Expose
    @DatabaseField(useGetSet = true)
    private String ship_from;

    @SerializedName("detail")
    @Expose
    @DatabaseField(useGetSet = true)
    private String detail;

    @SerializedName("count_ratting")
    @Expose
    @DatabaseField(useGetSet = true)
    private String count_ratting;

    @SerializedName("pic_name")
    @Expose
    @DatabaseField(useGetSet = true)
    private String pic_name;

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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

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

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRatting() {
        return ratting;
    }

    public void setRatting(String ratting) {
        this.ratting = ratting;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getWholesale_price() {
        return wholesale_price;
    }

    public void setWholesale_price(String wholesale_price) {
        this.wholesale_price = wholesale_price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getShip_from() {
        return ship_from;
    }

    public void setShip_from(String ship_from) {
        this.ship_from = ship_from;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCount_ratting() {
        return count_ratting;
    }

    public void setCount_ratting(String count_ratting) {
        this.count_ratting = count_ratting;
    }

    public String getPic_name() {
        return pic_name;
    }

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
