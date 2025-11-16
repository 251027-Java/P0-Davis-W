package org.example.Service;

import java.util.List;

import org.example.Exercises;
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

    public List<Exercises> getAllExercises(){
        return exercisesRepository.getAllExercises();
    }

    public boolean updateExercises(int id, String name, String muscle_group){
        Exercises existing = exercisesRepository.getExerciseById(id);
        if(existing == null){
            return false;
        }
        existing.setID(id);
        existing.setName(name);
        existing.setMuscleGroup(muscle_group);
        exercisesRepository.updateExercise(existing);
        return true;
    }

    public List<Exercises> getExercisesByMuscleGroup(String muscle_group){
        List<Exercises> allExercises = exercisesRepository.getAllExercises();
        return allExercises.stream().filter(exercise -> exercise.getMuscleGroup().equalsIgnoreCase(muscle_group)).toList();
    }


}
