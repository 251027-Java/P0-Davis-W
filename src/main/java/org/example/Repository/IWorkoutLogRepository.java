package org.example.Repository;

import java.util.List;

import org.example.WorkoutLog;

public interface IWorkoutLogRepository {
    //Workout Logs
    void addWorkoutLog(WorkoutLog workoutLog);
    List<WorkoutLog> getWorkoutLogsByUser(int userId);
    List<WorkoutLog> getWorkoutLogsByExercise(int userId, int exerciseId);
    void updateWorkoutLog(WorkoutLog workoutLog);
    void deleteWorkoutLog(int workoutId);
}
