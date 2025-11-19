package org.example.Service;

import java.util.Date;
import org.example.WorkoutLogs;
import org.example.Repository.IWorkoutLogRepository;

public class WorkoutLogService {

    // Field
    private IWorkoutLogRepository workoutLogRepository;


    //Constructor
    public WorkoutLogService(IWorkoutLogRepository workoutLogRepository){
        this.workoutLogRepository = workoutLogRepository;
    }

    // Methods
    public WorkoutLogs createWorkoutLog(int workout_id, int user_id, int exercise_id, int sets, int reps, double weight){
        if(workoutLogRepository.getWorkoutLogById(workout_id) != null){
            return null;
        }
        WorkoutLogs newLog = new WorkoutLogs(workout_id, user_id, exercise_id, new Date(), sets, reps, weight);
        workoutLogRepository.addWorkoutLog(newLog);
        return newLog;
    }

    public WorkoutLogs getWorkoutLog(int workout_id){
        return workoutLogRepository.getWorkoutLogById(workout_id);
    }

    public WorkoutLogs getWorkoutLogsByUser(int user_id){
        return workoutLogRepository.getWorkoutLogsByUser(user_id);
    }

    public WorkoutLogs getWorkoutLogByExercise(int user_id, int exercise_id){
        return workoutLogRepository.getWorkoutLogsByExercise(user_id, exercise_id);
    }

    public boolean updateWorkoutLog(int workout_id, int sets, int reps, double weight){
        WorkoutLogs existing = workoutLogRepository.getWorkoutLogById(workout_id);
        if(existing == null){
            return false;
        }
        existing.setSets(sets);
        existing.setReps(reps);
        existing.setWeight(weight);
        workoutLogRepository.updateWorkoutLog(existing);
        return true;
    }

    public boolean deleteWorkoutLog(int workout_id){
        if(workoutLogRepository.getWorkoutLogById(workout_id) == null){
            return false;
        }
        workoutLogRepository.deleteWorkoutLog(workout_id);
        return true;
    }


}
