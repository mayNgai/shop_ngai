package com.may.maystream.shopngai.controller;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by May on 8/18/2017.
 */

public class ApplicationController extends Application{
    private static ApplicationController instance = null;
    protected static Activity _activity;
    public String SYSTEM;
    public String ENVIROMENT;
    private static ApplicationController _instance = null;
    public static String URL_1 = "";
    public static String URL_2 = "";
    public ApplicationController() {
        setInstance("S","DEV");
    }

    public static ApplicationController getInstance() {
        if (null == instance) {
            instance = new ApplicationController();
        }
        return instance;
    }

    private void setInstance(String criteria,String instance){
        if(criteria.equalsIgnoreCase("S")){
            SYSTEM = criteria;
            ENVIROMENT = instance;
            if(instance.equalsIgnoreCase("DEV")){
                URL_1 = "http://172.20.10.12/tellme/";
                URL_2 = "http://172.20.10.12:80/tellme/img/";
//                URL_1 = "http://10.255.248.63:80/tellme/";
//                URL_2 = "http://10.255.248.63:80/tellme/img/";
            }

        }
    }


    public static Activity getActivity(){
        return _activity;

    }
    public static void setActivity(Activity a){
        _activity = a;

    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public static Bitmap StringToBitMap(String encodedString){
        Bitmap bitmap = null;
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

        }catch(Exception e){
            e.getMessage();
        }
        return bitmap;
    }

    public static String BitMapToString(Bitmap bitmap){
        try{
            ByteArrayOutputStream baos=new  ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,90, baos);
            byte [] b=baos.toByteArray();
            String temp= Base64.encodeToString(b, Base64.DEFAULT);
            return temp;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //if(flagDev==true)if(flagDev==true)catchAlert(e.getMessage(), e.getStackTrace()[2].getLineNumber());
            return null;
        }
    }

    public byte[] BitMapToByteArray(Bitmap bitmap){
        try{
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void writeTofile(String content,String syncFor){
        try {
            String path = Environment.getExternalStorageDirectory().getPath() + "/shopNgai";
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdir();
            }
            String fileName = "/records"+syncFor+".txt";
            FileOutputStream fos;
            fos = new FileOutputStream(filePath.getPath() + fileName);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e);
        }

    }

    public Bitmap getResizedBitmap(Bitmap bm, double newHeight, double newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;

    }
}
