package com.ghiath.sampleapp;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;


import com.ghiath.sampleapp.db.AppDatabase;

import java.util.Calendar;


public class BasicApplication extends Application {
    public AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

    mAppExecutors=new AppExecutors();

    }


    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this,mAppExecutors);
    }
    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }


}
