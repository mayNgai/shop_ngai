package com.may.maystream.shopngai.controller;

import android.app.Activity;
import android.app.Application;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
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

    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
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
