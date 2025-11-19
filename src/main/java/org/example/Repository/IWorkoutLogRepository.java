package org.example.Repository;

import org.example.WorkoutLogs;

public interface IWorkoutLogRepository {
    //Workout Logs
    void addWorkoutLog(WorkoutLogs workoutLog);
    WorkoutLogs getWorkoutLogsByUser(int userId);
    WorkoutLogs getWorkoutLogsByExercise(int userId, int exerciseId);
    void updateWorkoutLog(WorkoutLogs workoutLog);
    void deleteWorkoutLog(int workoutId);
    WorkoutLogs getWorkoutLogById(int id);
}
