import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.Date;

import org.checkerframework.checker.units.qual.A;
import org.example.Exercises;
import org.example.Repository.IExercisesRepository;
import org.example.Service.ExerciseService;
import org.example.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;


@ExtendWith(MockitoExtension.class)
public class ExerciseServiceTest {

    @InjectMocks
    private ExerciseService exercise;

    @Mock 
    private IExercisesRepository mockRepo;

    @BeforeEach
    public void setUp(){
        mockRepo = Mockito.mock(IExercisesRepository.class);
        exercise = new ExerciseService(mockRepo);
    }

}
