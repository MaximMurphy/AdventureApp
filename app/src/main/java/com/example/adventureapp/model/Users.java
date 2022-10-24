package com.example.adventureapp.model;

import java.util.List;

public class Users {

    private List<User> userList;

    public Users(){}

    public Users(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
