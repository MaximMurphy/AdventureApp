package com.example.adventureapp.model;
import junit.framework.*;
public class AdventureTest extends TestCase{
    // assigning the values
    Adventure adventureForSetters;
    protected void setUp(){
        adventureForSetters = new Adventure();
    }

    // test method to add two values
    public void testGetId(){
        Adventure adventure = new Adventure(
                "id", "user", "adventureName", "taskOne", "taskTwo", "taskThree"
        );

        assertNotNull(adventure);
        assertEquals("id", adventure.getId());
    }

    public void testGetUser(){
        Adventure adventure = new Adventure(
                "id", "user", "adventureName", "taskOne", "taskTwo", "taskThree"
        );

        assertNotNull(adventure);
        assertEquals("user", adventure.getUser());
    }

    public void testGetAdventureName(){
        Adventure adventure = new Adventure(
                "id", "user", "adventureName", "taskOne", "taskTwo", "taskThree"
        );

        assertNotNull(adventure);
        assertEquals("adventureName", adventure.getAdventureName());
    }

    public void testGetTaskOne(){
        Adventure adventure = new Adventure(
                "id", "user", "adventureName", "taskOne", "taskTwo", "taskThree"
        );

        assertNotNull(adventure);
        assertEquals("taskOne", adventure.getTaskOne());
    }

    public void testGetTaskTwo(){
        Adventure adventure = new Adventure(
                "id", "user", "adventureName", "taskOne", "taskTwo", "taskThree"
        );

        assertNotNull(adventure);
        assertEquals("taskTwo", adventure.getTaskTwo());
    }

    public void testGetTaskThree(){
        Adventure adventure = new Adventure(
                "id", "user", "adventureName", "taskOne", "taskTwo", "taskThree"
        );

        assertNotNull(adventure);
        assertEquals("taskThree", adventure.getTaskThree());
    }

    public void testSetId(){
        adventureForSetters.setId("id");
        assertEquals("id", adventureForSetters.getId());
    }

    public void testSetUser(){
        adventureForSetters.setUser("user");
        assertEquals("user", adventureForSetters.getUser());
    }

    public void testSetAdventureName(){
        adventureForSetters.setAdventureName("adventureName");
        assertEquals("adventureName", adventureForSetters.getAdventureName());
    }

    public void testSetTaskOne(){
        adventureForSetters.setTaskOne("taskOne");
        assertEquals("taskOne", adventureForSetters.getTaskOne());
    }

    public void testSetTaskTwo(){
        adventureForSetters.setTaskTwo("taskTwo");
        assertEquals("taskTwo", adventureForSetters.getTaskTwo());
    }

    public void testSetTaskThree(){
        adventureForSetters.setTaskThree("taskThree");
        assertEquals("taskThree", adventureForSetters.getTaskThree());
    }

}
