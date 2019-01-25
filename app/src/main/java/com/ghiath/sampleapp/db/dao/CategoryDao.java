package com.ghiath.sampleapp.db.dao;



import com.ghiath.sampleapp.db.entity.CategoryEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Ghiath on 12/24/2017.
 */
@Dao
public interface CategoryDao {
    @Query("SELECT * FROM categories")
    LiveData<List<CategoryEntity>> loadAllCategories();

    @Query("SELECT COUNT(*) FROM categories")
    int countCategories();

    @Query("SELECT * FROM categories WHERE id= :id")
    LiveData<CategoryEntity> loadCategoryById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllCategories(List<CategoryEntity> categoryEntities);

    @Query("select (SELECT COUNT(*) FROM categories WHERE  lastUpdate >= :timeout) > 0")
    public boolean hasCategories( long timeout);

    @Query("DELETE FROM categories")
    int deleteAllCategories();

}
