package com.example.adventureapp.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tasks {

    public List<String> tasks = Arrays.asList("Find mushrooms", "Find a river", "Find a big rock",
            "Find an animal", "Find a windy trail", "Find a tall tree", "Find a bench", "Find a Y-Stick",
            "Find a bird", "Find a trail-marker");

    public Tasks(){}

    public Tasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public List<String> getThreeRandomTasks(){
        List<String> adventureTasks = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            Random rand = new Random();
            int index = rand.nextInt(tasks.size());
            while(adventureTasks.contains(tasks.get(index))){
                index = rand.nextInt(tasks.size());
            }
            adventureTasks.add(tasks.get(index));
        }

        return adventureTasks;
    }

}
