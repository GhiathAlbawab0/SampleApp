package com.ghiath.sampleapp.db.entity;


import com.ghiath.sampleapp.db.Converters;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


@Entity(tableName = "posts"
        ,indices = {@Index( value="idPost"),@Index(value = {"name","caption"})})
public class PostEntity {
    @PrimaryKey int idPost;// autoGeneration has omitted because delete and refresh in continue so number not being between 1-70

         String username;
        private String name;
        private String caption;
        private String type;
        private float category_id;
//        ArrayList< Object > tag = new ArrayList < Object > ();
        private String url;
    @TypeConverters({Converters.class})
        ArrayList < MediaEntity > media = new ArrayList <  > ();
        private float view_count;
        private float like_count;
        private float dislike_count;
        private float comment_count;
        private String coutrycode2;
        private float ranking_score;
        private float created_at;

   @Nullable
   Long lastUpdate;


    public PostEntity(int idPost, String username, String name, String caption, String type, float category_id, String url, ArrayList<MediaEntity> media, float view_count, float like_count, float dislike_count, float comment_count, String coutrycode2, float ranking_score, float created_at, Long lastUpdate) {
        this.idPost = idPost;
        this.username = username;
        this.name = name;
        this.caption = caption;
        this.type = type;
        this.category_id = category_id;
//        this.tag = tag;
        this.url = url;
        this.media = media;
        this.view_count = view_count;
        this.like_count = like_count;
        this.dislike_count = dislike_count;
        this.comment_count = comment_count;
        this.coutrycode2 = coutrycode2;
        this.ranking_score = ranking_score;
        this.created_at = created_at;
        this.lastUpdate = lastUpdate;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCategory_id() {
        return category_id;
    }

    public void setCategory_id(float category_id) {
        this.category_id = category_id;
    }

//    public ArrayList<Object> getTag() {
//        return tag;
//    }
//
//    public void setTag(ArrayList<Object> tag) {
//        this.tag = tag;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<MediaEntity> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<MediaEntity> media) {
        this.media = media;
    }

    public float getView_count() {
        return view_count;
    }

    public void setView_count(float view_count) {
        this.view_count = view_count;
    }

    public float getLike_count() {
        return like_count;
    }

    public void setLike_count(float like_count) {
        this.like_count = like_count;
    }

    public float getDislike_count() {
        return dislike_count;
    }

    public void setDislike_count(float dislike_count) {
        this.dislike_count = dislike_count;
    }

    public float getComment_count() {
        return comment_count;
    }

    public void setComment_count(float comment_count) {
        this.comment_count = comment_count;
    }

    public String getCoutrycode2() {
        return coutrycode2;
    }

    public void setCoutrycode2(String coutrycode2) {
        this.coutrycode2 = coutrycode2;
    }

    public float getRanking_score() {
        return ranking_score;
    }

    public void setRanking_score(float ranking_score) {
        this.ranking_score = ranking_score;
    }

    public float getCreated_at() {
        return created_at;
    }

    public void setCreated_at(float created_at) {
        this.created_at = created_at;
    }
}
