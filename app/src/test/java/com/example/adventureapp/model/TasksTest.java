package com.example.adventureapp.model;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class TasksTest extends TestCase {

    protected void setUp() {

    }

    public void testGetTasks(){
        Tasks tasks = new Tasks(Arrays.asList("one", "two", "three"));
        assertEquals(Arrays.asList("one", "two", "three"), tasks.getTasks());
    }

    public void testSetTasks(){
        Tasks tasks = new Tasks();
        tasks.setTasks(Arrays.asList("one"));
        assertEquals(Arrays.asList("one"), tasks.getTasks());
    }

    public void testGetThreeRandomTasks(){
        Tasks tasks = new Tasks(Arrays.asList("one", "two", "three"));
        List<String> rand = tasks.getThreeRandomTasks();
        for(String str : rand){
            assertTrue(tasks.getTasks().contains(str));
        }
    }

}
