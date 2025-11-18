import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import org.example.Exercises;
import org.example.Repository.IExercisesRepository;
import org.example.Service.ExerciseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;


@ExtendWith(MockitoExtension.class)
public class ExerciseServiceTest {

    @InjectMocks
    private ExerciseService exercise;

    @Mock 
    private IExercisesRepository mockRepo;

    @Test
    public void testCreateExerciseSuccess(){
        when(mockRepo.getExerciseById(1)).thenReturn(null);

        Exercises createExercise = exercise.createExercise(1, "Push Up", "Chest");

        Assertions.assertNotNull(createExercise);
        Assertions.assertEquals(1, createExercise.getID());
        Assertions.assertEquals("Push Up", createExercise.getName());
        Assertions.assertEquals("Chest", createExercise.getMuscleGroup());

        verify(mockRepo, times(1)).addExercise(Mockito.any(Exercises.class));
    }

}
