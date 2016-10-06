package com.zhanming.healthycook.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.net.URLEncoder;

/**
 * Created by zhanming on 2016/9/30.
 */
public class Recipe implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("img")
    private String imgUrl;
    @SerializedName("description")
    private String description;
    @SerializedName("keywords")
    private String key;
    @SerializedName("message")
    private String message;


    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", key='" + key + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public Recipe() {

    }

    public Recipe(Recipe r) {
        this.id = id;
        this.name = r.name;
        this.description = r.description;
        this.imgUrl = r.imgUrl;
        this.key = r.key;
        this.message = r.message;
    }

    protected Recipe(Parcel in) {
        id = in.readString();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(imgUrl);
        parcel.writeString(description);
        parcel.writeString(key);
        parcel.writeString(message);
    }
}
