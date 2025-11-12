package org.example.Repository;

import java.sql.*;
import java.util.List;

import org.example.Exercise;
import org.example.Measurement;
import org.example.User;
import org.example.WorkoutLog;

public class PostgreSQLRepository implements IUserRepository,
IMeasurementsRepository, IExercisesRepository, IWorkoutLogRepository {

    // Fields
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432/workoutdb";
    private static final String Postgre_User = "postgres";
    private static final String Postgre_PW = "mysecretpassword";
    private Connection connection;

    public PostgreSQLRepository() {
        try{
            connection = DriverManager.getConnection(Postgre_URL, Postgre_User, Postgre_PW);
            System.out.println("Successful creation of PostgreSQL database!");
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() throws SQLException{
        try(Statement stmt = connection.createStatement()){
            stmt.executeUpdate("CREATE SCHEMA IF NOT EXISTS GymReports;"+
                    "CREATE TABLE IF NOT EXISTS GymReports.Users (" +
                    "user_id SERIAL PRIMARY KEY," +
                    "first_name VARCHAR(50)," +
                    "last_name VARCHAR(50)," +
                    "email VARCHAR(100) UNIQUE NOT NULL," +
                    "date_joined TIMESTAMP NOT NULL);");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS GymReports.Measurements (" +
                    "measurement_id SERIAL PRIMARY KEY," +
                    "user_id INT REFERENCES GymReports.Users(user_id) ON DELETE CASCADE," +
                    "log_date TIMESTAMP NOT NULL," +
                    "weight_lbs "

        }


    }




    @Override
    public void addWorkoutLog(WorkoutLog workoutLog) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addWorkoutLog'");
    }

    @Override
    public List<WorkoutLog> getWorkoutLogsByUser(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWorkoutLogsByUser'");
    }

    @Override
    public List<WorkoutLog> getWorkoutLogsByExercise(int userId, int exerciseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWorkoutLogsByExercise'");
    }

    @Override
    public void updateWorkoutLog(WorkoutLog workoutLog) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateWorkoutLog'");
    }

    @Override
    public void deleteWorkoutLog(int workoutId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteWorkoutLog'");
    }

    @Override
    public void addUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUser'");
    }

    @Override
    public User getUserById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public void updateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public void addExercise(Exercise exercise) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addExercise'");
    }

    @Override
    public Exercise getExerciseById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExerciseById'");
    }

    @Override
    public List<Exercise> getAllExercises() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllExercises'");
    }

    @Override
    public void updateExercise(Exercise exercise) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateExercise'");
    }

    @Override
    public void deleteExercise(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteExercise'");
    }

    @Override
    public void addMeasurement(Measurement measurement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMeasurement'");
    }

    @Override
    public List<Measurement> getMeasurementsByUser(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMeasurementsByUser'");
    }

    @Override
    public Measurement getLatestMeasurement(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLatestMeasurement'");
    }

    @Override
    public void updateMeasurement(Measurement measurement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMeasurement'");
    }

    @Override
    public void deleteMeasurement(int measurementId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMeasurement'");
    }

}
