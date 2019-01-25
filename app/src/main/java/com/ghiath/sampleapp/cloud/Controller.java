package com.ghiath.sampleapp.cloud;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Controller {
     static Webservice webservice;
    static String baseUrl="http://stg.api.fawasell.com/v1/";

    public static Webservice getWebservice() {
        if(webservice==null) {
            Retrofit retrofit;
            OkHttpClient okHttpClient = null;


                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(1, TimeUnit.MINUTES)

                        .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(EnvelopeConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            webservice = retrofit.create(Webservice.class);
        }
        return webservice;
    }
    public static Webservice getWebservice(String selectedBaseUrl) {
        Webservice webservice1=null;
            Retrofit retrofit;
            OkHttpClient okHttpClient = null;

                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(1, TimeUnit.MINUTES)
                        .build();            retrofit = new Retrofit.Builder()
                    .baseUrl(selectedBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        webservice1 = retrofit.create(Webservice.class);


        return webservice1;
    }
}
