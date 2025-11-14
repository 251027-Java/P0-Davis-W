package org.example.Repository;

import java.util.List;

import org.example.Exercises;

public interface IExercisesRepository {
    //Exercises
    void addExercise(Exercises exercise);
    Exercises getExerciseById(int id);
    List<Exercises> getAllExercises();
    void updateExercise(Exercises exercise);
    void deleteExercise(int id);
}
