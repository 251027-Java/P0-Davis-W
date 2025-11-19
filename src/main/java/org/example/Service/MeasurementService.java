package org.example.Service;

import org.example.Measurements;
import org.example.Repository.IMeasurementsRepository;

public class MeasurementService {

    //Field
    private IMeasurementsRepository measurementRepo;

    // Constructor
    public MeasurementService(IMeasurementsRepository measurementRepo){
        this.measurementRepo = measurementRepo;
    }

    //Methods
    public Measurements createMeasurements(int measurement_id, int user_id, double weight, double chest_inches, double arm_inches, double waist_inches, double body_fat_percent){
        if(measurementRepo.getMeasurementsById(measurement_id) != null){
            return null;
        }
        Measurements newMeasurement = new Measurements(measurement_id, user_id, new java.util.Date(), weight, chest_inches, arm_inches, waist_inches, body_fat_percent);
        measurementRepo.addMeasurement(newMeasurement);
        return newMeasurement;
    }

    public Measurements getMeasurementsByUserID(int user_id){
        return measurementRepo.getMeasurementByUserId(user_id);
    }

    public Measurements getMeasurementsById(int measurement_id){
        return measurementRepo.getMeasurementsById(measurement_id);
    }

    public boolean deleteMeasurement(int measurement_id){
        if(measurementRepo.getMeasurementsById(measurement_id) == null){
            return false;
        }
        measurementRepo.deleteMeasurement(measurement_id);
        return true;
    }

    public boolean updateMeasurement(int measurement_id, int user_id, double weight, double chest_inches, double arm_inches, double waist_inches, double body_fat_percent){
        Measurements existing = measurementRepo.getMeasurementsById(measurement_id);
        if(existing == null){
            return false;
        }
        existing.setUserID(user_id);
        existing.setWeight(weight);
        existing.setChest(chest_inches);
        existing.setArms(arm_inches);
        existing.setWaist(waist_inches);
        existing.setBodyFat(body_fat_percent);
        measurementRepo.updateMeasurement(existing);
        return true;
    }





}
