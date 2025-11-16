package org.example;

public class Exercises {

    //Fields
    private int id;
    private String name;
    private String muscle_group;

    // Constructor
    public Exercises(int id, String name, String muscle_group){
        this.id = id;
        this.name = name;
        this.muscle_group = muscle_group;
    }

    // toString
    @Override
    public String toString(){
        return "Exercises [id: "+id+", name: "+name+", muscle_group: "+muscle_group+"]";
    }

    //Getters
    public int getID(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getMuscleGroup(){
        return this.muscle_group;
    }

    //Setters
    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMuscleGroup(String muscle){
        this.muscle_group = muscle;
    }



}
