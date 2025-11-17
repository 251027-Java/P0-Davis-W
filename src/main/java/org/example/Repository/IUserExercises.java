package org.example.Repository;

import java.util.List;

import org.example.Exercises;
import org.example.UserExercises;

public interface IUserExercises {
    void addUserExercise(UserExercises userExercise);
    void deleteUserExercise(int exerciseId);
    UserExercises getFavoriteByUserId(int userId);
    UserExercises getFavoriteByExerciseId(int exerciseId);
    List<Exercises> getExercisesForUser(int userId);
}
