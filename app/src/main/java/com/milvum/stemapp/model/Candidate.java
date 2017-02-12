package com.milvum.stemapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Randy Tjin Asjoe on 02/10/2017.
 */

public class Candidate implements Parcelable {

    private String mId;
    private String mLastName;
    private String mFirstName;
    private String mGender;
    private String mCity;

    public Candidate(String id, String lastName, String firstName, String gender, String city) {
        mId = id;
        mLastName = lastName;
        mFirstName = firstName;
        mGender = gender;
        mCity = city;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        this.mGender = gender;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public String getFirstLetter() {
        return "" + mFirstName.charAt(0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mLastName);
        dest.writeString(mFirstName);
        dest.writeString(mGender);
        dest.writeString(mCity);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Candidate> CREATOR = new Parcelable.Creator<Candidate>() {
        public Candidate createFromParcel(Parcel in) {
            return new Candidate(in);
        }

        public Candidate[] newArray(int size) {
            return new Candidate[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Candidate(Parcel in) {
        mId = in.readString();
        mLastName = in.readString();
        mFirstName = in.readString();
        mGender = in.readString();
        mCity = in.readString();
    }
}

