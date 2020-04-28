package com.example.projetsession.model;

import android.graphics.Bitmap;

public class Recipe {

    private String title;
    private Bitmap image;
    private String description;
    private String id;

    public Recipe(String title,Bitmap image, String description, String id) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.id = id;
    }

    public Recipe() {};

    public String getId() { return id; }

    public String getTitle() {
        return title;
    }

    public Bitmap getImage() { return image; };

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Bitmap image) { this.image = image; }

    public void setDescription(String description){
        this.description = description;
    }
}
