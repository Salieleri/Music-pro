package com.example.salieri.entity.model;

public class UserUpdateModel {
    private String userId;

    private String userIdBefore;

    public String getUserIdBefore() {
        return userIdBefore;
    }

    public void setUserIdBefore(String userIdBefore) {
        this.userIdBefore = userIdBefore;
    }

    private String userPassword;

    private String userPhonenumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber) {
        this.userPhonenumber = userPhonenumber == null ? null : userPhonenumber.trim();
    }
}
