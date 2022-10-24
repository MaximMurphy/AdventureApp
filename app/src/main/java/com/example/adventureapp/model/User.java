package com.example.adventureapp.model;

import java.util.List;

public class User {

    private String userName;
    private List<Adventure> adventureList;

    public User(){}

    public User(String userName, List<Adventure> adventureList) {
        this.userName = userName;
        this.adventureList = adventureList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Adventure> getAdventureList() {
        return adventureList;
    }

    public void setAdventureList(List<Adventure> adventureList) {
        this.adventureList = adventureList;
    }




}
