package com.example.recycler;

import com.google.firebase.database.Exclude;

public class model {
    private String name;
    private String imgUri;
    private  String  Key;

    @Exclude
    public String getKey() {
        return Key;
    }
    @Exclude
    public void setKey(String key) {
        Key = key;
    }

    public model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public model(String name, String imgUri) {
        if(name.trim().equals(""))
        {
            name = "No Name";
        }
        else {
            this.name = name;
        }
        this.imgUri = imgUri;
    }
}
