package org.example.Repository;

import java.util.List;

import org.example.Exercise;

public interface IUserExercises {
    void addUserExercise(int userId, int exerciseId);
    void deleteUserExercise(int userId, int exerciseId);
    List<Exercise> getExercisesForUser(int userId);
}
