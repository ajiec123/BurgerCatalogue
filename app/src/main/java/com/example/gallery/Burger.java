package com.example.gallery;

import android.os.Parcel;
import android.os.Parcelable;

public class Burger implements Parcelable {
    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    private String name;
    private String detail;
    private int photo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.detail);
        dest.writeInt(this.photo);
    }

    public Burger() {
    }

    protected Burger(Parcel in) {
        this.name = in.readString();
        this.detail = in.readString();
        this.photo = in.readInt();
    }

    public static final Parcelable.Creator<Burger> CREATOR = new Parcelable.Creator<Burger>() {
        @Override
        public Burger createFromParcel(Parcel source) {
            return new Burger(source);
        }

        @Override
        public Burger[] newArray(int size) {
            return new Burger[size];
        }
    };
}