package com.zhanming.healthycook.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URLEncoder;

/**
 * Created by zhanming on 2016/9/30.
 */
public class Recipe implements Parcelable {
    private String name;
    private String imgUrl;
    private String description;
    private String key;
    private String message;

    public Recipe(){

    }

    public Recipe(Recipe r){
        this.name = r.name;
        this.description = r.description;
        this.imgUrl = r.imgUrl;

        this.key = r.key;
        this.message = r.message;
    }

    protected Recipe(Parcel in) {
        name = in.readString();
        imgUrl = in.readString();
        description = in.readString();
        key = in.readString();
        message = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(imgUrl);
        parcel.writeString(description);
        parcel.writeString(key);
        parcel.writeString(message);
    }
}
