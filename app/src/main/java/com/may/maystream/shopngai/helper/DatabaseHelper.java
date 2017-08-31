package com.may.maystream.shopngai.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.may.maystream.shopngai.model.TblMember;
import com.may.maystream.shopngai.model.TblMyItem;

import java.sql.SQLException;

/**
 * Created by May on 8/18/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    private static final String DATABASE_NAME = "shop_ngai.db";
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<TblMember, String> tblMember;
    private RuntimeExceptionDao<TblMyItem, String> tblMyItem;
    private static DatabaseHelper instance;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, TblMember.class);
            TableUtils.createTable(connectionSource, TblMyItem.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, TblMember.class, true);
            TableUtils.dropTable(connectionSource, TblMyItem.class, true);

        }catch (Exception e){
            e.printStackTrace();
        }
        onCreate(db, connectionSource);

    }

    public RuntimeExceptionDao<TblMember, String> getTblMember() {
        if (tblMember == null) {
            tblMember = getRuntimeExceptionDao(TblMember.class);
        }
        return tblMember;
    }
    public RuntimeExceptionDao<TblMyItem, String> getTblMyItem() {
        if (tblMyItem == null) {
            tblMyItem = getRuntimeExceptionDao(TblMyItem.class);
        }
        return tblMyItem;
    }
}
