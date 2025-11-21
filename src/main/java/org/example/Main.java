package org.example;

import org.example.ObjectClasses.*;
import org.example.Repository.PostgreSQLRepository;
import org.example.Service.*;

public class Main {
    public static void main(String[] args) {

        PostgreSQLRepository repo = new PostgreSQLRepository();

        UserService userService = new UserService(repo);
        ExerciseService exerciseService = new ExerciseService(repo);
        MeasurementService measurementService = new MeasurementService(repo, repo);
        WorkoutLogService workoutLogService = new WorkoutLogService(repo, repo, repo);
        UserExerciseService userExerciseService = new UserExerciseService(repo, repo, repo);

        UserInteraction userInteraction = new UserInteraction(userService, exerciseService, measurementService, workoutLogService, userExerciseService);
        userInteraction.start();
    }
}

