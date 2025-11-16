package org.example.Repository;

import java.util.List;

import org.example.Exercises;
import org.example.User;
import org.example.WorkoutLogs;

public interface IWorkoutLogRepository {
    //Workout Logs
    void addWorkoutLog(WorkoutLogs workoutLog);
    List<WorkoutLogs> getWorkoutLogsByUser(int userId);
    List<WorkoutLogs> getWorkoutLogsByExercise(int userId, int exerciseId);
    List<WorkoutLogs> getAllWorkoutLogs();
    void updateWorkoutLog(WorkoutLogs workoutLog);
    void deleteWorkoutLog(int workoutId);
    WorkoutLogs getWorkoutLogById(int id);
}
