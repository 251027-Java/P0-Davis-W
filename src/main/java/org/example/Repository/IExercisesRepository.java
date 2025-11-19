package org.example.Repository;

import org.example.Exercises;

public interface IExercisesRepository {
    //Exercises
    void addExercise(Exercises exercise);
    Exercises getExerciseById(int id);
    void updateExercise(Exercises exercise);
    void deleteExercise(int id);
}
