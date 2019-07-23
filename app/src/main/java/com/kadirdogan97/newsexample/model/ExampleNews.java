package com.kadirdogan97.newsexample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExampleNews implements Parcelable {
    private String status;
    private String totalResults;
    private List<Articles> articles;

    protected ExampleNews(Parcel in) {
        status = in.readString();
        totalResults = in.readString();
        articles = in.createTypedArrayList(Articles.CREATOR);
    }

    public static final Creator<ExampleNews> CREATOR = new Creator<ExampleNews>() {
        @Override
        public ExampleNews createFromParcel(Parcel in) {
            return new ExampleNews(in);
        }

        @Override
        public ExampleNews[] newArray(int size) {
            return new ExampleNews[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeString(totalResults);
        parcel.writeTypedList(articles);
    }
}
