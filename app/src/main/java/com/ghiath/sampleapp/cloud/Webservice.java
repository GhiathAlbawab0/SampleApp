package com.ghiath.sampleapp.cloud;


import com.ghiath.sampleapp.db.entity.CategoryEntity;
import com.ghiath.sampleapp.db.entity.EchoEntity;
import com.ghiath.sampleapp.db.entity.PostEntity;

import java.util.List;

import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Webservice {


    @GET("test")
    Call<EchoEntity> testCall(@Query("app_id") String app_id, @Query("app_secret") String app_secret);
@GET("categories")
    Call<List<CategoryEntity>> getCategories(@Query("app_id") String app_id, @Query("app_secret") String app_secret, @Query("signature") String signature);
@GET("posts")
    Call<List<PostEntity>> getPosts(@Query("category") String category,@Query("page") String page,@Query("limit") String limit,@Query("q") String q,@Query("app_id") String app_id, @Query("app_secret") String app_secret, @Query("signature") String signature);


}
