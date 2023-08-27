package com.example.mas.Model;

public class SizeModel {
    private String size_id;
    private String description;
    public String getSize_id() {
        return size_id;
    }

    public void setSize_id(String size_id) {
        this.size_id = size_id;
    }

    public String getSize_description() {
        return description;
    }

    public void setSize_description(String size_description) {
        this.description = size_description;
    }

    public SizeModel(String size_id, String size_description) {
        this.size_id = size_id;
        this.description = size_description;
    }


}
