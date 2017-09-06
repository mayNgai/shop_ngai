package com.may.maystream.shopngai.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by may on 9/7/2017.
 */
@DatabaseTable(tableName="TblDetail")
public class TblDetail implements Serializable {
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

    @SerializedName("size")
    @Expose
    @DatabaseField(useGetSet = true)
    private String size;

    @SerializedName("stock")
    @Expose
    @DatabaseField(useGetSet = true)
    private String stock;

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
