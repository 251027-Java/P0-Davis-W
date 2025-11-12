package org.example.Repository;

import java.util.List;

import org.example.Exercise;

public interface IExercisesRepository {
    //Exercises
    void addExercise(Exercise exercise);
    Exercise getExerciseById(int id);
    List<Exercise> getAllExercises();
    void updateExercise(Exercise exercise);
    void deleteExercise(int id);
}
