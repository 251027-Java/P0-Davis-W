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
import org.example.User;
import org.example.UserExercises;
import org.example.Repository.IExercisesRepository;
import org.example.Repository.IUserExercises;
import org.example.Repository.IUserRepository;
import org.example.Service.ExerciseService;
import org.example.Service.UserExerciseService;
import org.example.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
public class FavoriteExerciseTest {

    @InjectMocks
    private UserExerciseService userExercise;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private ExerciseService exerciseService;

    @Mock
    private IUserExercises mockUserExerciseRepo;

    @Mock
    private IUserRepository mockUserRepo;

    @Mock
    private IExercisesRepository mockExerciseRepo;


    @Test
    public void testAddFavoriteExerciseSuccess(){
        when(mockUserRepo.getUserById(1)).thenReturn(null);
        when(mockUserExerciseRepo.getFavoriteByUserId(1)).thenReturn(null);
        
        User user = userService.createUser(1, "John", "Wayne", "jwayne@test.com");
        Exercises exercise = exerciseService.createExercise(2, "Bench", "Chest");

        UserExercises favorite = userExercise.addFavoriteExercise(user.getID(), exercise.getID());

        Assertions.assertNotNull(favorite);
        Assertions.assertEquals(1, favorite.getUserID());
        Assertions.assertEquals(2, favorite.getExerciseID());

        verify(mockUserExerciseRepo, times(1)).addUserExercise(Mockito.any(UserExercises.class));
    }


}
