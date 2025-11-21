package org.example.ObjectClasses;

public class UserExercises {

    //Fields
    private int user_id;
    private int exercise_id;

    //Constructor
    public UserExercises(int user_id, int exercise_id){
        this.user_id = user_id;
        this.exercise_id = exercise_id;
    }

    //toString
    @Override
    public String toString(){
        return "UserExercises [user_id: "+user_id+", exercise_id: "+exercise_id+"]";
    }

    //Getters
    public int getUserID(){
        return this.user_id;
    }

    public int getExerciseID(){
        return this.exercise_id;
    }

    //Setters
    public void setUserID(int user_id){
        this.user_id = user_id;
    }
    
    public void setExerciseID(int exercise_id){
        this.exercise_id = exercise_id;
    }

}
