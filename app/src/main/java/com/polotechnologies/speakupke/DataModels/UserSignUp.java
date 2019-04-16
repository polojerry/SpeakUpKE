package com.polotechnologies.speakupke.DataModels;

public class UserSignUp {
    public String mUserId;
    public String mPassword;
    public String mUserName;

    public UserSignUp() {
    }

    public UserSignUp(String mUserId, String mPassword, String mUserName) {
        this.mUserId = mUserId;
        this.mPassword = mPassword;
        this.mUserName = mUserName;
    }
}
