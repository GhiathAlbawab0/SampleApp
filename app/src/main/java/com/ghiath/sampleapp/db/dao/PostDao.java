package com.ghiath.sampleapp.db.dao;

import com.ghiath.sampleapp.db.entity.CategoryEntity;
import com.ghiath.sampleapp.db.entity.PostEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceAllPosts(List<PostEntity> postEntities);

    @Update()
    void updatePosts(PostEntity postEntity);

    @Query("SELECT * FROM posts")
    LiveData<List<PostEntity>> loadAllPosts();

    @Query("SELECT * FROM posts where idPost=:postId")
    LiveData<List<PostEntity>> loadPostsForCustomerID(int postId);

    @Query("SELECT * FROM posts WHERE lower(name) LIKE :name OR lower(caption) LIKE :name")
    LiveData<List<PostEntity>> searchPostsByString(String name);


    @Query("DELETE FROM posts")
    int deleteAllPosts();

     @Query("SELECT COUNT(*) FROM posts")
        int countPosts();

    @Query("select (SELECT COUNT(*) FROM posts WHERE  lastUpdate >= :timeout) > 0")
    public boolean hasPosts( long timeout);

}
