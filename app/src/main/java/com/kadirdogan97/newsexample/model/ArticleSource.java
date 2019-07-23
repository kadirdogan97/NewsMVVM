package com.kadirdogan97.newsexample.model;

import android.os.Parcel;
import android.os.Parcelable;


public class ArticleSource implements Parcelable {
    private String id;
    private String name;

    protected ArticleSource(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<ArticleSource> CREATOR = new Creator<ArticleSource>() {
        @Override
        public ArticleSource createFromParcel(Parcel in) {
            return new ArticleSource(in);
        }

        @Override
        public ArticleSource[] newArray(int size) {
            return new ArticleSource[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
    }
}
