package com.example.adventureapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.adventureapp.model.Tasks;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
@RunWith(AndroidJUnit4.class)
public class TasksTest extends TestCase {

    @Test
    public void testGetThreeRandomTasks(){
        Tasks tasks = new Tasks(InstrumentationRegistry.getInstrumentation().getContext());
        List<String> three = tasks.getThreeRandomTasks();

        for(String x : three){
            assertTrue(tasks.getTasks().contains(x));
        }
    }
}
