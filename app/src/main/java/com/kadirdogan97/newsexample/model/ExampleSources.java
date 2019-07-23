package com.kadirdogan97.newsexample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExampleSources implements Parcelable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sources")
    @Expose
    private List<Sources> sources = null;

    protected ExampleSources(Parcel in) {
        status = in.readString();
        sources = in.createTypedArrayList(Sources.CREATOR);
    }

    public static final Creator<ExampleSources> CREATOR = new Creator<ExampleSources>() {
        @Override
        public ExampleSources createFromParcel(Parcel in) {
            return new ExampleSources(in);
        }

        @Override
        public ExampleSources[] newArray(int size) {
            return new ExampleSources[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Sources> getSources() {
        return sources;
    }

    public void setSources(List<Sources> sources) {
        this.sources = sources;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeTypedList(sources);
    }
}
