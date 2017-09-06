package com.may.maystream.shopngai.controller;

import android.app.Activity;
import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.may.maystream.shopngai.helper.DatabaseHelper;
import com.may.maystream.shopngai.model.TblDetail;
import com.may.maystream.shopngai.model.TblMember;
import com.may.maystream.shopngai.model.TblMyItem;
import com.may.maystream.shopngai.model.TblOrder;
import com.may.maystream.shopngai.model.TblPicture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by May on 8/18/2017.
 */

public class TaskController {
    private Context context;
    private Activity _activity;
    private DatabaseHelper databaseHelper = null;
    private RuntimeExceptionDao<TblMember, String> tblMemberRuntimeDao;
    private RuntimeExceptionDao<TblMyItem, String> tblMyItemRuntimeDao;
    private RuntimeExceptionDao<TblPicture, String> tblPictureRuntimeDao;
    private RuntimeExceptionDao<TblOrder, String> tblOrderRuntimeDao;
    private RuntimeExceptionDao<TblDetail, String> tblDetailRuntimeDao;

    private void getConnectDatabaseHelper() {
        try {
            _activity = ApplicationController.getActivity();
            databaseHelper  = DatabaseHelper.getHelper(context);
            tblMemberRuntimeDao = databaseHelper.getTblMember();
            tblMyItemRuntimeDao = databaseHelper.getTblMyItem();
            tblPictureRuntimeDao = databaseHelper.getTblPicture();
            tblOrderRuntimeDao = databaseHelper.getTblOrder();
            tblDetailRuntimeDao = databaseHelper.getTblDetail();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createMember(TblMember m){
        getConnectDatabaseHelper();
        try {
            tblMemberRuntimeDao.create(m);

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteMember(){
        getConnectDatabaseHelper();
        try {
            List<TblMember> m = checkMember();
            tblMemberRuntimeDao.delete(m);

        }catch (Exception e){
            e.printStackTrace();
        }


        return true;
    }

    public boolean updateMember(TblMember m){
        getConnectDatabaseHelper();
        try {
            tblMemberRuntimeDao.update(m);

        }catch (Exception e){
            e.printStackTrace();
        }


        return true;
    }

    public List<TblMember> checkMember(){
        List<TblMember> t = new ArrayList<TblMember>();
        getConnectDatabaseHelper();
        try {
            t = tblMemberRuntimeDao.queryForAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    public boolean createMyItem(TblMyItem m){
        getConnectDatabaseHelper();
        try {
            tblMyItemRuntimeDao.create(m);

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
