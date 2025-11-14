package org.example;

import java.util.Date;

public class WorkoutLogs {

    //Field
    private int workout_id;
    private int user_id;
    private int exercise_id;
    private Date log_date;
    private int sets;
    private int reps;
    private double weight_lbs;

    // Constructor
    public WorkoutLogs(int workout_id, int user_id, int exercise_id, Date log_date, int sets, int reps, double weight_lbs){
        this.workout_id = workout_id;
        this.user_id = user_id;
        this.exercise_id = exercise_id;
        this.log_date = log_date;
        this.sets = sets;
        this.reps = reps;
        this.weight_lbs = weight_lbs;
    }

    // toString
    @Override
    public String toString(){
        return "WorkoutLogs [workout_id: "+workout_id+", user_id: "+user_id+", exercise_id: "+exercise_id+
        ", log_date: "+log_date+", sets: "+sets+", reps: "+reps+", weight_lbs: "+weight_lbs+"]";
    }

    // Getters
    public int getWorkoutID(){
        return this.workout_id;
    }

    public int getUserID(){
        return this.user_id;
    }

    public int getExerciseID(){
        return this.exercise_id;
    }

    public Date getLogDate(){
        return this.log_date;
    }

    public int getSets(){
        return this.sets;
    }

    public int getReps(){
        return this.reps;
    }

    public double getWeight(){
        return this.weight_lbs;
    }
    

}
