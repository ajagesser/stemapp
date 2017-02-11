package com.milvum.stemapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Randy Tjin Asjoe on 10/02/2017.
 */

public class Party implements Parcelable {

    private int mId;
    private String mName;
    private int mPosition;
    private int mIcon;

    public Party(int id, String name, int position, int icon) {
        mId = id;
        mName = name;
        mPosition = position;
        mIcon = icon;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        this.mIcon = mIcon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = mName;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = mPosition;
    }

    public int getId() {
        return mId;
    }

    public void setId(int Id) {
        this.mId = Id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeInt(mPosition);
        dest.writeInt(mIcon);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Party> CREATOR = new Parcelable.Creator<Party>() {
        public Party createFromParcel(Parcel in) {
            return new Party(in);
        }

        public Party[] newArray(int size) {
            return new Party[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Party(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mPosition = in.readInt();
        mIcon = in.readInt();
    }
}
