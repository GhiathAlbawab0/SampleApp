package com.ghiath.sampleapp.cloud;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Envelope<T> implements Serializable {
    @Expose
    MetaEntity meta;
    @Expose
    T data;


    public MetaEntity getMeta() {
        return meta;
    }

    public void setMeta(MetaEntity meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Envelope{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
