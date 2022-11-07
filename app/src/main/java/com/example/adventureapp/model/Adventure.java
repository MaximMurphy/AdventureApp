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

    public String getTaskOne(){ return taskOne; }
    public String getTaskTwo(){ return taskTwo; }
    public String getTaskThree(){ return taskThree; }

    public String getAdventureName() {
        return adventureName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
    }

    public void setTaskOne(String taskOne) {
        this.taskOne = taskOne;
    }
    public void setTaskTwo(String taskTwo) {
        this.taskTwo = taskTwo;
    }
    public void setTaskThree(String taskThree) {
        this.taskThree = taskThree;
    }
}
