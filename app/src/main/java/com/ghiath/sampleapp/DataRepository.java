package com.ghiath.sampleapp;


import android.util.Log;


import com.ghiath.sampleapp.cloud.WebserviceCall;
import com.ghiath.sampleapp.db.AppDatabase;
import com.ghiath.sampleapp.db.entity.CategoryEntity;
import com.ghiath.sampleapp.db.entity.PostEntity;
import com.ghiath.sampleapp.ui.MessageViewer;

import java.util.Calendar;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;


/**
 * Repository to handle the work with customers and tables
 * Created by Ghiath on 12/24/2017.
 */

public class DataRepository {
    public static final String TAG="DataRepository";
    private static DataRepository sInstance;

    private static final int FRESH_TIMEOUT=10;//in seconds


    AppExecutors executor;
     AppDatabase mDatabase;

     private MediatorLiveData<List<CategoryEntity>> mObservableCategories;
     private MediatorLiveData<List<PostEntity>> mObserablePosts;

     private DataRepository(AppDatabase database) {
         this.mDatabase = database;
         executor = new AppExecutors();

         mObservableCategories = new MediatorLiveData<>();
         mObserablePosts = new MediatorLiveData<>();

         mObservableCategories.addSource(mDatabase.categoryDao().loadAllCategories(),
                 customersEntities -> {
                     if (mDatabase.getDatabaseCreated().getValue() != null) {
                         mObservableCategories.postValue(customersEntities);
                     }
                 });
         mObserablePosts.addSource(mDatabase.postDao().loadAllPosts(),
                 tableEntities -> {
                     if (mDatabase.getDatabaseCreated().getValue() != null)
                         mObserablePosts.postValue(tableEntities);
                 });

     }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }


    public LiveData<List<CategoryEntity>> getCategories(MessageViewer messageViewer) {

        executor.diskIO().execute(()->{refreshCategories(messageViewer);});

           return mObservableCategories;
    }

    public void refreshCategories(MessageViewer messageViewer)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,-FRESH_TIMEOUT);


        executor.networkIO().execute(() ->
                {
                    boolean categoriesExist=mDatabase.categoryDao().hasCategories(calendar.getTimeInMillis());
                    if(!categoriesExist) {
                        List<CategoryEntity> categoryEntities = new WebserviceCall(messageViewer).getCategoriesOnline();
                        if (categoryEntities != null && !categoryEntities.isEmpty()) {
//                        mDatabase.categoryDao().deleteAllCategories();
                            //set update date
                            Calendar calendar1 = Calendar.getInstance();
                            for (CategoryEntity cat : categoryEntities) {
                                cat.setLastUpdate(calendar1.getTimeInMillis());
                            }
                            mDatabase.categoryDao().insertAllCategories(categoryEntities);
                        }
                    }});
    }
    public LiveData<List<PostEntity>> loadPosts(MessageViewer me,String category,String page,String limit,String q)
    {
        executor.diskIO().execute(()->{ refereshPosts(me,category,page,limit,q);});
        return mObserablePosts;


    }

    public void refereshPosts(MessageViewer messageViewer,String category,String page,String limit,String q)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,-FRESH_TIMEOUT);
        boolean postsExist=mDatabase.postDao().hasPosts(calendar.getTimeInMillis());
        if(!postsExist)
            executor.networkIO().execute(() ->
            {
                List<PostEntity> postEntities=new WebserviceCall(messageViewer).getPostsOnline(category,page,limit,q);
                if(postEntities!=null && !postEntities.isEmpty())
                {
//                        mDatabase.categoryDao().deleteAllCategories();
                    //set update date
                    Calendar calendar1=Calendar.getInstance();
                    for (PostEntity cat:postEntities) {
                        cat.setLastUpdate(calendar1.getTimeInMillis());
                    }
                    mDatabase.postDao().deleteAllPosts();
                    mDatabase.postDao().insertOrReplaceAllPosts(postEntities);
                }
            });

    }

//
//      public LiveData<List<CategoryEntity>> getFilteredCustomers(String name)
//    {
//
//        return mDatabase.customerDao().loadCustomerByName("%"+name.toLowerCase()+"%");
//    }

}
