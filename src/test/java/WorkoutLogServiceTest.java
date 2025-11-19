import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.example.WorkoutLogs;
import org.example.Repository.IWorkoutLogRepository;
import org.example.Service.WorkoutLogService;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class WorkoutLogServiceTest {

    @InjectMocks
    private WorkoutLogService workoutLog;

    @Mock
    private IWorkoutLogRepository measureRepo;

    @Test
    public void testCreateWorkoutLogSuccess(){
        when(measureRepo.getWorkoutLogById(1)).thenReturn(null);

        WorkoutLogs createWorkoutLog = workoutLog.createWorkoutLog(1, 1, 1, 4, 12, 225.0);

        Assertions.assertNotNull(createWorkoutLog);
        Assertions.assertEquals(1, createWorkoutLog.getWorkoutID());
        Assertions.assertEquals(1, createWorkoutLog.getUserID());
        Assertions.assertEquals(1, createWorkoutLog.getExerciseID());
        Assertions.assertEquals(4, createWorkoutLog.getSets());
        Assertions.assertEquals(12, createWorkoutLog.getReps());
        Assertions.assertEquals(225.0, createWorkoutLog.getWeight());

        verify(measureRepo, times(1)).addWorkoutLog(Mockito.any(WorkoutLogs.class));
        
    }

    @Test
    public void testCreateWorkoutLogFailsWhenIdExists(){
        WorkoutLogs exists = new WorkoutLogs(2, 1, 1, new Date(), 4, 10, 200.0);
        when(measureRepo.getWorkoutLogById(2)).thenReturn(exists);

        WorkoutLogs newWorkoutLog = workoutLog.createWorkoutLog(2, 3, 2, 4, 12, 225.0);

        Assertions.assertNull(newWorkoutLog);

        verify(measureRepo, never()).addWorkoutLog(Mockito.any(WorkoutLogs.class));
    }

    @Test
    public void testGetWorkoutLogsByLogId(){
        WorkoutLogs exists = new WorkoutLogs(3, 1, 2, new Date(), 5, 15, 250.0);
        when(measureRepo.getWorkoutLogById(3)).thenReturn(exists);

        WorkoutLogs retrievedLog = workoutLog.getWorkoutLog(3);

        Assertions.assertNotNull(retrievedLog);
        Assertions.assertEquals(3, retrievedLog.getWorkoutID());

        verify(measureRepo, times(1)).getWorkoutLogById(3);
    }

    @Test
    public void testUpdateLog() {
        WorkoutLogs exists = new WorkoutLogs(1, 1, 1, new Date(), 3, 8, 145.0);
        when(measureRepo.getWorkoutLogById(1)).thenReturn(exists);
        doNothing().when(measureRepo).updateWorkoutLog(any(WorkoutLogs.class));

        boolean result = workoutLog.updateWorkoutLog(1, 2, 4, 405);

        Assertions.assertTrue(result);

        verify(measureRepo).updateWorkoutLog(argThat(log ->
                log.getSets() == 2 &&
                log.getReps() == 4 &&
                log.getWeight() == 405
        ));
    }







}
