package org.example.Repository;

import java.util.List;

import org.example.WorkoutLogs;

public interface IWorkoutLogRepository {
    //Workout Logs
    void addWorkoutLog(WorkoutLogs workoutLog);
    WorkoutLogs getWorkoutLogsByUser(int userId);
    WorkoutLogs getWorkoutLogsByExercise(int userId, int exerciseId);
    List<WorkoutLogs> getAllWorkoutLogs();
    void updateWorkoutLog(WorkoutLogs workoutLog);
    void deleteWorkoutLog(int workoutId);
    WorkoutLogs getWorkoutLogById(int id);
}
