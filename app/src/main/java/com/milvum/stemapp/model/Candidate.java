package com.milvum.stemapp.model;

/**
 * Created by Randy Tjin Asjoe on 10/02/2017.
 */

public class Candidate {

    private int mId;
    private String mLastName;
    private String mFirstName;
    private String mGender;
    private String mCity;

    public Candidate(int id, String lastName, String firstName, String gender, String city) {
        mId = id;
        mLastName = lastName;
        mFirstName = firstName;
        mGender = gender;
        mCity = city;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
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
}
