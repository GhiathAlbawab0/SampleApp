package com.ghiath.sampleapp.db.entity;



import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Customer Database entity
 * Created by Ghiath on 12/23/2017.
 */
@Entity(tableName = "categories"
        ,indices = {@Index("id")})
public class CategoryEntity implements Serializable {
    @PrimaryKey    int id;
    String name;
    String description;
    String image;

    @Nullable
    Long lastUpdate;

    public CategoryEntity(int id, String name, String description, String image, Long lastUpdate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.lastUpdate = lastUpdate;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CategoryEntity && this.getId()==((CategoryEntity)obj).getId();
    }
}
