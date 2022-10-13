package com.tworoot2.loginscreen.RetrofitAPIWork;

public class ResponseModel {

    User user;

    public ResponseModel(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
