package com.milvum.stemapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.ArraySet;

import java.util.Set;

/**
 * Created by Randy Tjin Asjoe on 02/11/2017.
 */

public class Vote implements Parcelable {

    private String mId;
    private String mToken;
    private String mPartyName;
    private String mCandidateId;
    private String mCandidateGender;
    private String mCandidateCity;
    private String mCandidateFirstName;
    private int mPosition;

    public Vote(String id, String token, String partyName, Candidate candidate, int position) {
        mId = id;
        mToken = token;
        mPartyName = partyName;
        mCandidateId = candidate.getId();
        mCandidateFirstName = candidate.getFirstName();
        mCandidateLastName = candidate.getLastName();
        mCandidateGender = candidate.getGender();
        mCandidateCity= candidate.getCity();
        mPosition = position;
    }

    public String getCandidateFirstName() {
        return mCandidateFirstName;
    }

    public void setCandidateFirstName(String mCandidateFirstName) {
        this.mCandidateFirstName = mCandidateFirstName;
    }

    public String getCandidateCity() {
        return mCandidateCity;
    }

    public void setCandidateCity(String mCandidateCity) {
        this.mCandidateCity = mCandidateCity;
    }

    public String getCandidateGender() {
        return mCandidateGender;
    }

    public void setCandidateGender(String mCandidateGender) {
        this.mCandidateGender = mCandidateGender;
    }

    public String getCandidateId() {
        return mCandidateId;
    }

    public void setCandidateId(String mCandidateId) {
        this.mCandidateId = mCandidateId;
    }

    public String getPartyName() {
        return mPartyName;
    }

    public void setPartyName(String mPartyName) {
        this.mPartyName = mPartyName;
    }

    private String mCandidateLastName;

    public String getCandidateLastName() {
        return mCandidateLastName;
    }

    public void setCandidateLastName(String mCandidateLastName) {
        this.mCandidateLastName = mCandidateLastName;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public String getId() {
        return mId;
    }

    public void setId(String Id) {
        this.mId = Id;
    }

    public String getFirstLetter() {
        return "" + mCandidateFirstName.charAt(0);
    }

    public ArraySet<String> toArraySet(){
        ArraySet<String> voteSet = new ArraySet<>();
        voteSet.add(getId());
        voteSet.add(getToken());
        voteSet.add(getPartyName());
        voteSet.add("" + getCandidateId());
        voteSet.add(getCandidateLastName());
        voteSet.add(getCandidateFirstName());
        voteSet.add(getCandidateGender());
        voteSet.add(getCandidateCity());
        return voteSet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mToken);
        dest.writeString(mPartyName);
        dest.writeString(mCandidateId);
        dest.writeString(mCandidateFirstName);
        dest.writeString(mCandidateLastName);
        dest.writeString(mCandidateGender);
        dest.writeString(mCandidateCity);
        dest.writeInt(mPosition);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Creator<Vote> CREATOR = new Creator<Vote>() {
        public Vote createFromParcel(Parcel in) {
            return new Vote(in);
        }

        public Vote[] newArray(int size) {
            return new Vote[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Vote(Parcel in) {
        mId = in.readString();
        mToken = in.readString();
        mPartyName = in.readString();
        mCandidateId = in.readString();
        mCandidateFirstName = in.readString();
        mCandidateLastName = in.readString();
        mCandidateGender = in.readString();
        mCandidateCity = in.readString();
        mPosition = in.readInt();
    }
}
