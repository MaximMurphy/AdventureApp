package com.example.adventureapp.model;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.example.adventureapp.R;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tasks extends Application {
    Context ctx;
    public List<String> tasks;

    public Tasks(){}

    public Tasks(Context ctx){
        this.ctx = ctx;
        this.tasks = Arrays.asList(ctx.getString(R.string.find_mushrooms), ctx.getString(R.string.find_river), ctx.getString(R.string.find_big_rock),
                ctx.getString(R.string.find_animal), ctx.getString(R.string.find_windy_trail), ctx.getString(R.string.find_tall_tree), ctx.getString(R.string.find_bench),
                ctx.getString(R.string.find_ystick), ctx.getString(R.string.find_bird), ctx.getString(R.string.find_trail_marker));
    }

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
