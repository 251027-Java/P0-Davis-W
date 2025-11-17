import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.Date;

import org.checkerframework.checker.units.qual.A;
import org.example.UserExercises;
import org.example.Repository.IUserExercises;
import org.example.Repository.IUserRepository;
import org.example.Service.UserExerciseService;
import org.example.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
public class FavoriteExerciseTest {

    @InjectMocks
    private UserExerciseService userExercise;

    @Mock
    private IUserExercises userExerciseRepo;

    @Mock
    private IUserRepository userRepo;

    @BeforeEach
    public void setUp(){
        userExerciseRepo = Mockito.mock(IUserExercises.class);
        userRepo = Mockito.mock(IUserRepository.class);
        userExercise = new UserExerciseService(userExerciseRepo, userRepo);
    }

}
