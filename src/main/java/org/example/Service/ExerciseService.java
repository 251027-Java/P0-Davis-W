package org.example.Service;

import org.example.ObjectClasses.Exercises;
import org.example.Repository.IExercisesRepository;

public class ExerciseService {

    //Fields 
    private IExercisesRepository exercisesRepository;

    //Constructor
    public ExerciseService(IExercisesRepository exercisesRepository){
        this.exercisesRepository = exercisesRepository;
    }

    // Methods
    public Exercises createExercise(int id, String name, String muscle_group){
        if(exercisesRepository.getExerciseById(id) != null){
            return null;
        }
        Exercises newExercises = new Exercises(id, name, muscle_group);
        exercisesRepository.addExercise(newExercises);
        return newExercises;
    }

    public Exercises getExercises(int id){
        return exercisesRepository.getExerciseById(id);
    }

    public boolean deleteExercise(int id){
        if(exercisesRepository.getExerciseById(id) == null){
            return false;
        }
        exercisesRepository.deleteExercise(id);
        return true;
    }

    public boolean updateExercises(int id, String name, String muscle_group){
        Exercises existing = exercisesRepository.getExerciseById(id);
        if(existing == null){
            return false;
        }
        existing.setName(name);
        existing.setMuscleGroup(muscle_group);
        exercisesRepository.updateExercise(existing);
        return true;
    }


}
