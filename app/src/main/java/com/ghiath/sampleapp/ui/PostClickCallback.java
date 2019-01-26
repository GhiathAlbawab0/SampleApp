package com.ghiath.sampleapp.ui;


import com.ghiath.sampleapp.db.entity.CategoryEntity;
import com.ghiath.sampleapp.db.entity.PostEntity;

public interface PostClickCallback {
void onClick(PostEntity customerEntity);
}
