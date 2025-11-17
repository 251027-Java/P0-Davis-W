package org.example.Service;

import java.util.Collections;
import java.util.List;

import org.example.Exercises;
import org.example.UserExercises;
import org.example.Repository.IUserExercises;
import org.example.Repository.IUserRepository;

public class UserExerciseService {

    //Field
    private IUserExercises favortieExercises;
    private IUserRepository userRepo;

    // Constructor
    public UserExerciseService(IUserExercises favortieExercises, IUserRepository userRepo){
        this.favortieExercises = favortieExercises;
        this.userRepo = userRepo;
    }

    //Methods
    public UserExercises addFavoriteExercise(int user_id, int exercise_id){
        if (favortieExercises.getFavoriteByUserId(user_id) != null) {
            return null;
        }
        UserExercises newFavorite = new UserExercises(user_id, exercise_id);
        favortieExercises.addUserExercise(newFavorite);
        return newFavorite;
    }

    public boolean deleteFavoriteExercise(int exercise_id){
        if(favortieExercises.getFavoriteByExerciseId(exercise_id) == null){
            return false;
        }
        favortieExercises.deleteUserExercise(exercise_id);
        return true;
    }

    public List<Exercises> getFavoritesForUser(int user_id) {
        if (userRepo != null && userRepo.getUserById(user_id) == null) {
            return Collections.emptyList();
        }
        List<Exercises> favs = favortieExercises.getExercisesForUser(user_id);
        return favs == null ? Collections.emptyList() : favs;
    }

        

    

}
