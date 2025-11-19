package org.example.Service;

import org.example.UserExercises;
import org.example.Repository.IUserExercises;

public class UserExerciseService {

    //Field
    private IUserExercises favortieExercises;


    // Constructor
    public UserExerciseService(IUserExercises favortieExercises){
        this.favortieExercises = favortieExercises;
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

    public boolean updateFavoriteExercise(int userId, int exerciseId){
        UserExercises existing = favortieExercises.getFavoriteByUserId(userId);
        if(existing == null){
            return false;
        }
        existing.setExerciseID(exerciseId);
        favortieExercises.updateUserExercise(existing);
        return true;
    }

    public UserExercises getFavoritesByUser(int user_id) {
        return favortieExercises.getFavoriteByUserId(user_id);
    }

    

        

    

}
