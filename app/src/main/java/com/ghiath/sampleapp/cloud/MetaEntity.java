package com.ghiath.sampleapp.cloud;

public class MetaEntity {

    Integer status;

    String response;
    String message;
    Integer success;
    Integer total;
    Integer showing;
    Integer page;
    Integer pages;
    Long total_processing_time;
    Boolean cached;


    public Integer getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public Integer getSuccess() {
        return success;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getShowing() {
        return showing;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPages() {
        return pages;
    }

    public Long getTotal_processing_time() {
        return total_processing_time;
    }

    public Boolean getCached() {
        return cached;
    }

    @Override
    public String toString() {
        return "MetaEntity{" +
                "status=" + status +
                ", response='" + response + '\'' +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", total=" + total +
                ", showing=" + showing +
                ", page=" + page +
                ", pages=" + pages +
                ", total_processing_time=" + total_processing_time +
                ", cached=" + cached +
                '}';
    }
}
