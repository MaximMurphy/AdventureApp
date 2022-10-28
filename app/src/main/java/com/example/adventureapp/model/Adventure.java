package com.example.adventureapp.model;

import java.util.Arrays;
import java.util.List;

public class Adventure {

    private String user, adventureName, taskOne, taskTwo, taskThree;
    public Adventure(){}

    public Adventure(String user, String adventureName, String taskOne, String taskTwo, String taskThree) {
        this.user = user;
        this.adventureName = adventureName;
        this.taskOne = taskOne;
        this.taskTwo = taskTwo;
        this.taskThree = taskThree;
    }

    public String getUser() {
        return user;
    }

    public List<String> getTasks(){ return Arrays.asList(taskOne, taskTwo, taskThree); }

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
