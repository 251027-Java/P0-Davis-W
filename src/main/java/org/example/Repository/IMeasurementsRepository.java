package org.example.Repository;

import java.util.List;

import org.example.Measurements;

public interface IMeasurementsRepository {
    //Measurements
    void addMeasurement(Measurements measurement);
    List<Measurements> getMeasurementsByUser(int userId);
    Measurements getLatestMeasurement(int userId);
    void updateMeasurement(Measurements measurement);
    void deleteMeasurement(int measurementId);
}
