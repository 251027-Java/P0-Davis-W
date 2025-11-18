import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.Date;

import org.checkerframework.checker.units.qual.A;
import org.example.Measurements;
import org.example.Repository.IMeasurementsRepository;
import org.example.Repository.IUserRepository;
import org.example.Service.MeasurementService;
import org.example.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
public class MeasurementServiceTest {

    @InjectMocks
    private MeasurementService measurement;

    @Mock 
    private IMeasurementsRepository measureRepo;

    @Test
    public void testCreateMeasurementSuccess(){
        when(measureRepo.getMeasurementsById(1)).thenReturn(null);

        Measurements createMeasurement = measurement.createMeasurements(1, 1, 180.5, 12.4, 
        18.1, 30.0, 25.0);

        Assertions.assertNotNull(createMeasurement);
        Assertions.assertEquals(1, createMeasurement.getMeasurementID());
        Assertions.assertEquals(1, createMeasurement.getUserID());
        Assertions.assertEquals(180.5, createMeasurement.getWeight());
        Assertions.assertEquals(12.4, createMeasurement.getChest());
        Assertions.assertEquals(18.1, createMeasurement.getArms());
        Assertions.assertEquals(30.0, createMeasurement.getWaist());
        Assertions.assertEquals(25.0, createMeasurement.getBodyFat());

        verify(measureRepo, times(1)).addMeasurement(Mockito.any(Measurements.class));
    }

    @Test
    public void testCreateMeasurementFailsWhenIdExists(){
        Measurements exists = new Measurements(2, 1, new Date(),175.0, 11.0, 17.0, 29.0, 24.0);
        when(measureRepo.getMeasurementsById(2)).thenReturn(exists);

        Measurements newMeasurement = measurement.createMeasurements(2, 1, 180.5, 12.4, 
        18.1, 30.0, 25.0);

        Assertions.assertNull(newMeasurement);

        verify(measureRepo, never()).addMeasurement(Mockito.any(Measurements.class));
    }

}
