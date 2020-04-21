package com.makarand.Model;

import com.google.cloud.firestore.annotation.Exclude;

public class User {
    private String name, email, password, phone, uid;

    public User(){}
    public User(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User(String name, String email, String password, String phone, String uid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.uid = uid;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Exclude
    public boolean validate() {
        if(name == null || name.length() < 3) return false;
        if(email == null || email.length() < 5) return false;
        if(password == null || password.length() < 6) return false;
        if(phone == null) phone = "";
        return true;
    }
}
