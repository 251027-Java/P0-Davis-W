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
            
        } catch (Exception e){
            e.printStackTrace();

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
    public WorkoutLogs getWorkoutLogsByUser(int userId) {
        String sql = "SELECT * FROM GymReports.WorkoutLogs WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                WorkoutLogs workoutLog = new WorkoutLogs(
                    rs.getInt("workout_id"),
                    rs.getInt("user_id"),
                    rs.getInt("exercise_id"),
                    rs.getDate("log_date"),
                    rs.getInt("set"),
                    rs.getInt("reps"),
                    rs.getDouble("weight_lbs")
                );
                return workoutLog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WorkoutLogs getWorkoutLogsByExercise(int userId, int exerciseId) {
        String sql = "SELECT * FROM GymReports.WorkoutLogs WHERE user_id = ? AND exercise_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, exerciseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                WorkoutLogs workoutLog = new WorkoutLogs(
                    rs.getInt("workout_id"),
                    rs.getInt("user_id"),
                    rs.getInt("exercise_id"),
                    rs.getDate("log_date"),
                    rs.getInt("set"),
                    rs.getInt("reps"),
                    rs.getDouble("weight_lbs")
                );
                return workoutLog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        String sql = "SELECT * FROM GymReports.WorkoutLogs WHERE workout_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                WorkoutLogs workoutLog = new WorkoutLogs(
                    rs.getInt("workout_id"),
                    rs.getInt("user_id"),
                    rs.getInt("exercise_id"),
                    rs.getDate("log_date"),
                    rs.getInt("set"),
                    rs.getInt("reps"),
                    rs.getDouble("weight_lbs")
                );
                return workoutLog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<WorkoutLogs> getAllWorkoutLogs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllWorkoutLogs'");
    }




    //IUserRepository METHODS
    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO GymReports.Users (user_id, first_name, last_name, email, date_joined) VALUES ( ?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, user.getID());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getEmail());
            stmt.setDate(5, new java.sql.Date(user.getDateJoined().getTime()));
            stmt.executeUpdate();
            System.out.println("User created successfully");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM GymReports.Users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getDate("date_joined")
                );
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        String sql = "INSERT INTO GymReports.Exercises (exercise_id, exercise_name, muscle_group) VALUES ( ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, exercise.getID());
            stmt.setString(2, exercise.getName());
            stmt.setString(3, exercise.getMuscleGroup());
            stmt.executeUpdate();
            System.out.println("Exercise created successfully");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Exercises getExerciseById(int id) {
        String sql = "SELECT * FROM GymReports.Exercises WHERE exercise_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Exercises exercise = new Exercises(
                    rs.getInt("exercise_id"),
                    rs.getString("exercise_name"),
                    rs.getString("muscle_group")
                );
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        String sql = "INSERT INTO GymReports.Measurements (measurement_id, user_id, log_date, weight_lbs, chest_inches, arms_inches, waist_inches, bodyfat_percent) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, measurement.getMeasurementID());
            stmt.setInt(2, measurement.getUserID());
            stmt.setDate(3, new java.sql.Date(measurement.getLogDate().getTime()));
            stmt.setDouble(4, measurement.getWeight());
            stmt.setDouble(5, measurement.getChest());
            stmt.setDouble(6, measurement.getArms());
            stmt.setDouble(7, measurement.getWaist());
            stmt.setDouble(8, measurement.getBodyFat());
            stmt.executeUpdate();
            System.out.println("Measurement created successfully");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Measurements getMeasurementByUserId(int userId) {
        String sql = "SELECT * FROM GymReports.Measurements WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Measurements measurement = new Measurements(
                    rs.getInt("measurement_id"),
                    rs.getInt("user_id"),
                    rs.getDate("log_date"),
                    rs.getDouble("weight_lbs"),
                    rs.getDouble("chest_inches"),
                    rs.getDouble("arms_inches"),
                    rs.getDouble("waist_inches"),
                    rs.getDouble("bodyfat_percent")
                );
                return measurement;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        String sql = "SELECT * FROM GymReports.Measurements WHERE measurement_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, measurementId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Measurements measurement = new Measurements(
                    rs.getInt("measurement_id"),
                    rs.getInt("user_id"),
                    rs.getDate("log_date"),
                    rs.getDouble("weight_lbs"),
                    rs.getDouble("chest_inches"),
                    rs.getDouble("arms_inches"),
                    rs.getDouble("waist_inches"),
                    rs.getDouble("bodyfat_percent")
                );
                return measurement;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Measurements> getAllMeasurements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMeasurements'");
    }




    // IUserExercises METHODS
    @Override
    public void addUserExercise(UserExercises userExercise) {
        String sql = "INSERT INTO GymReports.UserExercises (user_id, exercise_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userExercise.getUserID());
            ps.setInt(2, userExercise.getExerciseID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserExercise(int exerciseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUserExercise'");
    }

    @Override
    public UserExercises getFavoriteByUserId(int userId) {
        String sql = "SELECT * FROM GymReports.UserExercises WHERE user_id = ? LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UserExercises userExercise = new UserExercises(
                    rs.getInt("user_id"),
                    rs.getInt("exercise_id")
                );
                return userExercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserExercises getFavoriteByExerciseId(int exerciseId) {
        String sql = "SELECT * FROM GymReports.UserExercises WHERE exercise_id = ? LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, exerciseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UserExercises userExercise = new UserExercises(
                    rs.getInt("user_id"),
                    rs.getInt("exercise_id")
                );
                return userExercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Exercises> getExercisesForUser(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExercisesForUser'");
    }
    

}
