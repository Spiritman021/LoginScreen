package com.tworoot2.loginscreen.RetrofitAPIWork;

public class User {

    String name;
    String email;
    String usertype;
    String token;


    public User() {
    }


    public User(String name, String email, String usertype, String token) {
        this.name = name;
        this.email = email;
        this.usertype = usertype;
        this.token = token;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
