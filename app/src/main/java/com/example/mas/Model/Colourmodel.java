package com.example.mas.Model;
public class Colourmodel {
    private String color_id;
    private String description;
    public Colourmodel(String color_id, String description) {
        this.color_id = color_id;
        this.description = description;
    }
    public String getColor_id() {
        return color_id;
    }
    public void setColor_id(String color_id) {
        this.color_id = color_id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
