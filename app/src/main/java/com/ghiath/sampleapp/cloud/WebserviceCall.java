package com.ghiath.sampleapp.cloud;


import com.ghiath.sampleapp.db.entity.CategoryEntity;
import com.ghiath.sampleapp.db.entity.EchoEntity;
import com.ghiath.sampleapp.db.entity.PostEntity;
import com.ghiath.sampleapp.ui.MessageViewer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class WebserviceCall {
    private final String appId="14378174";
    private final String app_secret="cFJiGxZVTGf3rPNMK1o08WQWWpnr8M9RmZOyGxcM";
    private final String hash_key="diBIGrtVCAH00GtMVLupirbNdFEjooqk8YPJUtUw";

    private Webservice mWebservice;

    private MessageViewer mMessageViewer;


    public WebserviceCall(MessageViewer messageViewer) {
        this.mMessageViewer=messageViewer;
        Retrofit retrofit;
//        retrofit=new Retrofit.Builder()
//                .baseUrl("https://s3-eu-west-1.amazonaws.com/quandoo-assessment/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        mWebservice=retrofit.create(Webservice.class);
        
        mWebservice=Controller.getWebservice();

    }

    public List<CategoryEntity> getCategoriesOnline() {
        Response<List<CategoryEntity>> response = null;
        try {
            response = mWebservice.getCategories(
                    appId,app_secret,convertPassMd5(appId+app_secret+hash_key)
            ).execute();
        } catch (IOException e) {
            e.printStackTrace();
            mMessageViewer.showNoConnection();
            return null;
        }
        if (response!=null && !response.isSuccessful())//when return with error code
        {
            mMessageViewer.showNoConnection();
            return null;
        }
        return (List<CategoryEntity>) response.body();
    }

    public List<PostEntity> getPostsOnline(String category,String page,String limit,String q) {
        Response response= null;
        if (q==null) q="";
        try {
            response = mWebservice.getPosts(
                    category,page,limit,q,appId,app_secret,
                    convertPassMd5(category+page+limit+q+appId+app_secret+hash_key)
            ).execute();
        } catch (IOException e) {
            mMessageViewer.showNoConnection();
            return null;
        }
        if (response!=null && !response.isSuccessful())
        {
            mMessageViewer.showNoConnection();//when return with error code
            return null;
        }

        return (List<PostEntity>) response.body();
    }
    public EchoEntity testCallOnline() {
        Response response= null;
        try {
            response = mWebservice.testCall(appId,app_secret).execute();
        } catch (IOException e) {
            mMessageViewer.showNoConnection();
            return null;
        }
        if (response!=null && !response.isSuccessful())
        {
            mMessageViewer.showNoConnection();//when return with error code
            return null;
        }

        return (EchoEntity) response.body();
    }

    private String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }

    private String md5_sec(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }

//    class TablesConverter{
//        List<Boolean> booleans;
//        List<PostEntity> tableEntities;
//
//        public List<PostEntity> getTableEntities() {
//            return tableEntities;
//        }
//
//        public TablesConverter(Response response) {
//            booleans= (List<Boolean>) response.body();
//            tableEntities=new ArrayList<>();
//            int tableNumber=1;
//            for (Boolean b:
//                    this.booleans) {
//                PostEntity tableEntity= new PostEntity(b);
//                tableEntity.setNumber(tableNumber++);
//                tableEntities.add(tableEntity);
//            }
//
//        }
//    }


}
