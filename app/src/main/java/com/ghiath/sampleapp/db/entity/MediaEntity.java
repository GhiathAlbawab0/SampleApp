package com.ghiath.sampleapp.db.entity;

public class MediaEntity {


        private String type;
        private String url;
        private String title;


        // Getter Methods

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }

        // Setter Methods

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
