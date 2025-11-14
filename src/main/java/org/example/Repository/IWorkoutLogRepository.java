package org.example.Repository;

import java.util.List;

import org.example.WorkoutLogs;

public interface IWorkoutLogRepository {
    //Workout Logs
    void addWorkoutLog(WorkoutLogs workoutLog);
    List<WorkoutLogs> getWorkoutLogsByUser(int userId);
    List<WorkoutLogs> getWorkoutLogsByExercise(int userId, int exerciseId);
    void updateWorkoutLog(WorkoutLogs workoutLog);
    void deleteWorkoutLog(int workoutId);
}
