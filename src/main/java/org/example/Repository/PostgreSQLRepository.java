package org.example.Repository;

import java.sql.*;
import java.util.List;

import org.example.Exercises;
import org.example.Measurements;
import org.example.User;
import org.example.UserExercises;
import org.example.WorkoutLogs;

public class PostgreSQLRepository implements IUserRepository,
IMeasurementsRepository, IExercisesRepository, IWorkoutLogRepository, IUserExercises {

    // Fields
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432/workoutdb";
    private static final String Postgre_User = "postgres";
    private static final String Postgre_PW = "mysecretpassword";
    private Connection connection;

    public PostgreSQLRepository() {
        try{
            connection = DriverManager.getConnection(Postgre_URL, Postgre_User, Postgre_PW);
            createTables();
            System.out.println("Successful creation of PostgreSQL database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // helper method to create each table; helps
    // clean up the constructor for readability.
    private void createTables() throws SQLException{
        try(Statement stmt = connection.createStatement()){
            stmt.executeUpdate("CREATE SCHEMA IF NOT EXISTS GymReports");


            // Creating Users table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS GymReports.Users (" +
                    "user_id SERIAL PRIMARY KEY," +
                    "first_name VARCHAR(50)," +
                    "last_name VARCHAR(50)," +
                    "email VARCHAR(100) UNIQUE NOT NULL," +
                    "date_joined TIMESTAMP NOT NULL)");
            
            // Creating Measurements table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS GymReports.Measurements (" +
                    "measurement_id SERIAL PRIMARY KEY," +
                    "user_id INT REFERENCES GymReports.Users(user_id) ON DELETE CASCADE," +
                    "log_date TIMESTAMP NOT NULL," +
                    "weight_lbs NUMERIC(5,2)," +
                    "chest_inches NUMERIC(5,2)," +
                    "arms_inches NUMERIC(5,2)," +
                    "waist_inches NUMERIC(5,2)," +
                    "bodyfat_percent DECIMAL(3,2))");

            // Creating Exercises table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS GymReports.Exercises (" +
                    "exercise_id SERIAL PRIMARY KEY," +
                    "exercise_name VARCHAR(50) UNIQUE NOT NULL," +
                    "muscle_group VARCHAR(50) NOT NULL)");
            
            // Creating WorkoutLogs table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS GymReports.WorkoutLogs (" +
                    "workout_id SERIAL PRIMARY KEY," +
                    "user_id INT REFERENCES GymReports.Users(user_id) ON DELETE CASCADE," +
                    "exercise_id INT REFERENCES GymReports.Exercises(exercise_id) ON DELETE CASCADE," +
                    "log_date TIMESTAMP NOT NULL," +
                    "set INT NOT NULL," +
                    "reps INT NOT NULL," +
                    "weight_lbs NUMERIC(5,2))");
            
            // Creating UserExercises table; this table establishes a many-to-many
            // relationship, linking users to their favorite exercises
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS GymReports.UserExercises (" +
                    "user_id INT REFERENCES GymReports.Users(user_id) ON DELETE CASCADE," +
                    "exercise_id INT REFERENCES GymReports.Exercises(exercise_id) ON DELETE CASCADE," +
                    "PRIMARY KEY (user_id, exercise_id))");


            System.out.println("All tables created");
        }
    }



    // IWorkoutLogRepository METHODS
    @Override
    public void addWorkoutLog(WorkoutLogs workoutLog) {
        String sql = "INSERT INTO GymReports.WorkoutLogs (workout_id, user_id, exercise_id, log_date, set, reps, weight_lbs) VALUES ( ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, workoutLog.getWorkoutID());
            stmt.setInt(2, workoutLog.getUserID());
            stmt.setInt(3, workoutLog.getExerciseID());
            stmt.setDate(4, new java.sql.Date(workoutLog.getLogDate().getTime()));
            stmt.setInt(5, workoutLog.getSets());
            stmt.setInt(6, workoutLog.getReps());
            stmt.setDouble(7, workoutLog.getWeight());
            stmt.executeUpdate();
            System.out.println("Workout log created successfully");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public List<WorkoutLogs> getWorkoutLogsByUser(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWorkoutLogsByUser'");
    }
    @Override
    public List<WorkoutLogs> getWorkoutLogsByExercise(int userId, int exerciseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWorkoutLogsByExercise'");
    }

    @Override
    public void updateWorkoutLog(WorkoutLogs workoutLog) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateWorkoutLog'");
    }

    @Override
    public void deleteWorkoutLog(int workoutId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteWorkoutLog'");
    }

    @Override
    public WorkoutLogs getWorkoutLogById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWorkoutLogById'");
    }

    @Override
    public List<WorkoutLogs> getAllWorkoutLogs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllWorkoutLogs'");
    }




    //IUserRepository METHODS
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




    // IExerciseRepository METHODS
    @Override
    public void addExercise(Exercises exercise) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addExercise'");
    }

    @Override
    public Exercises getExerciseById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExerciseById'");
    }

    @Override
    public List<Exercises> getAllExercises() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllExercises'");
    }

    @Override
    public void updateExercise(Exercises exercise) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateExercise'");
    }

    @Override
    public void deleteExercise(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteExercise'");
    }




    // IMeasurementsRepository METHODS
    @Override
    public void addMeasurement(Measurements measurement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMeasurement'");
    }

    @Override
    public List<Measurements> getMeasurementsByUser(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMeasurementsByUser'");
    }

    @Override
    public Measurements getLatestMeasurement(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLatestMeasurement'");
    }

    @Override
    public void updateMeasurement(Measurements measurement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMeasurement'");
    }

    @Override
    public void deleteMeasurement(int measurementId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMeasurement'");
    }

    @Override
    public Measurements getMeasurementsById(int measurementId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMeasurementsById'");
    }

    @Override
    public List<Measurements> getAllMeasurements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMeasurements'");
    }




    // IUserExercises METHODS
    @Override
    public void addUserExercise(UserExercises userExercise) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUserExercise'");
    }

    @Override
    public void deleteUserExercise(int exerciseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUserExercise'");
    }

    @Override
    public UserExercises getFavoriteByUserId(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFavoriteByUserId'");
    }

    @Override
    public UserExercises getFavoriteByExerciseId(int exerciseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFavoriteByExerciseId'");
    }

    @Override
    public List<Exercises> getExercisesForUser(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExercisesForUser'");
    }
    

}
