package com.example.doan_android_2020._general.models;


import android.graphics.Bitmap;

public class User {
    private String userName;
    private String userFullName;
    private String userAvatar;
    private String userEmail;
    private String userPhone;
    private String useraddress;
    private String userBio;
    private String userGender;

    public User(String userName, String userFullName, String userEmail, String userPhone, String useraddress, String userBio, String userGender) {
        this.userName = userName;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.useraddress = useraddress;
        this.userBio = userBio;
        this.userGender = userGender;
    }

    public User(String userName, String userFullName) {
        this.userName = userName;
        this.userFullName = userFullName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userFullName='" + userFullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", useraddress='" + useraddress + '\'' +
                ", userBio='" + userBio + '\'' +
                ", userGender='" + userGender + '\'' +
                '}';
    }
}
