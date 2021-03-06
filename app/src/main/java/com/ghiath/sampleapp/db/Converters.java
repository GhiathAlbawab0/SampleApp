package com.ghiath.sampleapp.db;


import com.ghiath.sampleapp.db.entity.MediaEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.room.TypeConverter;

public class Converters {
        @TypeConverter
        public static ArrayList<MediaEntity> fromString(String value) {
            Type listType = new TypeToken<ArrayList<MediaEntity>>() {}.getType();
            ArrayList<MediaEntity> mediaEntities= new Gson().fromJson(value, listType);
            return mediaEntities;
        }

        @TypeConverter
        public static String fromArrayLisr(ArrayList<MediaEntity> list) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return json;
        }
        @TypeConverter
        public static String fromArrayList(ArrayList<Object> list) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return json;
        }
    }

