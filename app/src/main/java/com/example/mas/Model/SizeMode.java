package com.example.mas.Model;

public class SizeMode {
    private String size_id;
    private String description;
    public SizeMode(String size_id, String description) {
        this.size_id = size_id;
        this.description = description;
    }

    public String getSize_id() {
        return size_id;
    }

    public void setSize_id(String size_id) {
        this.size_id = size_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
