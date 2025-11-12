package org.example.Repository;

import java.util.List;

import org.example.Measurement;

public interface IMeasurementsRepository {
    //Measurements
    void addMeasurement(Measurement measurement);
    List<Measurement> getMeasurementsByUser(int userId);
    Measurement getLatestMeasurement(int userId);
    void updateMeasurement(Measurement measurement);
    void deleteMeasurement(int measurementId);
}
