import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.Date;

import org.checkerframework.checker.units.qual.A;
import org.example.WorkoutLogs;
import org.example.Repository.IMeasurementsRepository;
import org.example.Repository.IUserRepository;
import org.example.Repository.IWorkoutLogRepository;
import org.example.Service.UserService;
import org.example.Service.WorkoutLogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
public class WorkoutLogServiceTest {

    @InjectMocks
    private WorkoutLogService workoutLog;

    @Mock
    private IWorkoutLogRepository measureRepo;

    @BeforeEach
    public void setUp(){
        measureRepo = Mockito.mock(IWorkoutLogRepository.class);
        workoutLog = new WorkoutLogService(measureRepo);
    }

}
