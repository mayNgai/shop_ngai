package com.may.maystream.shopngai.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by May on 8/18/2017.
 */
@DatabaseTable(tableName="TblMember")
public class TblMember implements Serializable{
    @DatabaseField( useGetSet = true)
    private String success;

    @DatabaseField( useGetSet = true)
    private String message;

    @DatabaseField(id = true, useGetSet = true)
    private String guid;

    @SerializedName("first_name")
    @Expose
    @DatabaseField( useGetSet = true)
    private String first_name;

    @SerializedName("last_name")
    @Expose
    @DatabaseField( useGetSet = true)
    private String last_name;

    @DatabaseField( useGetSet = true)
    private String authentication;

    @DatabaseField( useGetSet = true)
    private String user_id;

    @SerializedName("password")
    @Expose
    @DatabaseField( useGetSet = true)
    private String password;

    @DatabaseField( useGetSet = true)
    private int status;

    @SerializedName("tel")
    @Expose
    @DatabaseField( useGetSet = true)
    private String tel;

    @SerializedName("email")
    @Expose
    @DatabaseField( useGetSet = true)
    private String email;

    @DatabaseField( useGetSet = true)
    private String date_register;

    @SerializedName("id")
    @Expose
    @DatabaseField( useGetSet = true)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_register() {
        return date_register;
    }

    public void setDate_register(String date_register) {
        this.date_register = date_register;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
