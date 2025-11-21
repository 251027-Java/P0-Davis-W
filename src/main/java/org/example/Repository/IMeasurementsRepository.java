package org.example.Repository;

import org.example.ObjectClasses.Measurements;

public interface IMeasurementsRepository {
    //Measurements
    void addMeasurement(Measurements measurement);
    Measurements getMeasurementByUserId(int userId);
    void updateMeasurement(Measurements measurement);
    void deleteMeasurement(int measurementId);
    Measurements getMeasurementsById(int measurementId);
    
}
