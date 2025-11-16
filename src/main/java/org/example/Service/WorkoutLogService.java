package org.example.Service;

import java.util.Date;
import java.util.List;

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

    public List<WorkoutLogs> getWorkoutLogsByUser(int user_id){
        List<WorkoutLogs> allLogs = workoutLogRepository.getAllWorkoutLogs();
        return allLogs.stream().filter(log -> log.getUserID() == user_id).toList();
    }

    public List<WorkoutLogs> getWorkoutLogByExercise(int user_id, int exercise_id){
        List<WorkoutLogs> allLogs = workoutLogRepository.getAllWorkoutLogs();
        return allLogs.stream().filter(log -> log.getUserID() == user_id && log.getExerciseID() == exercise_id) .toList();
    }

    public boolean updateWorkoutLog(int workout_id, int sets, int reps, double weight){
        WorkoutLogs existing = workoutLogRepository.getWorkoutLogById(workout_id);
        if(existing == null){
            return false;
        }
        existing = new WorkoutLogs(workout_id, existing.getUserID(), existing.getExerciseID(), existing.getLogDate(), sets, reps, weight);
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
