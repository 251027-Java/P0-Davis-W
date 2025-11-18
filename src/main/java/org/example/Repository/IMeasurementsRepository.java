package org.example.Repository;

import java.util.List;

import org.example.Measurements;

public interface IMeasurementsRepository {
    //Measurements
    void addMeasurement(Measurements measurement);
    List<Measurements> getAllMeasurements();
    Measurements getMeasurementByUserId(int userId);
    void updateMeasurement(Measurements measurement);
    void deleteMeasurement(int measurementId);
    Measurements getMeasurementsById(int measurementId);
    
}
