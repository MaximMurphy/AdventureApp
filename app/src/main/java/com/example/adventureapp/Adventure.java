package com.example.adventureapp;

public class Adventure {

    private String user;
    private String adventureName;
    public Adventure(){}

    public Adventure(String user, String adventureName) {
        this.user = user;
        this.adventureName = adventureName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAdventureName() {
        return adventureName;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
    }
}
