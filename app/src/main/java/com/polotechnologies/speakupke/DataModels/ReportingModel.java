package com.polotechnologies.speakupke.DataModels;

public class ReportingModel {
    public String mCrimeCategory;
    public String mCrimeDescription;
    public Double mLongitude;
    public Double mLatitude;

    public ReportingModel() {
    }

    public ReportingModel(String mCrimeCategory, String mCrimeDescription, Double mLongitude, Double mLatitude) {
        this.mCrimeCategory = mCrimeCategory;
        this.mCrimeDescription = mCrimeDescription;
        this.mLongitude = mLongitude;
        this.mLatitude = mLatitude;
    }
}
