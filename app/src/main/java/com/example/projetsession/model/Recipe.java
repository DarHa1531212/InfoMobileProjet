package com.example.projetsession.model;

import android.graphics.Bitmap;

public class Recipe {

    private String title;
    private Bitmap image;
    private String description;

    public Recipe(String title,Bitmap image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public Recipe() {};

    public String getTitle() {
        return title;
    }

    public Bitmap getImage() { return image; };

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Bitmap image) { this.image = image; }

    public void setDescription(String description){
        this.description = description;
    }
}
