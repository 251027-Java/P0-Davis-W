package org.example;

import org.example.Service.*;
import java.util.Scanner;

public class UserInteraction {

    //Fields
    private Scanner scanner;
    private UserService userService;
    private ExerciseService exerciseService;
    private MeasurementService measurementService;
    private WorkoutLogService workoutLogService;
    private UserExerciseService userExerciseService;

    //Constructor
    public UserInteraction(UserService userService, ExerciseService exerciseService, MeasurementService measurementService, WorkoutLogService workoutLogService, UserExerciseService userExerciseService) {
        this.scanner = new Scanner(System.in);
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.measurementService = measurementService;
        this.workoutLogService = workoutLogService;
        this.userExerciseService = userExerciseService;
    }

    public void start() {
        System.out.println("\n=== Welcome to Body Tracker Application ===");
        runMainMenu();
        scanner.close();
    }

    // this run method is called in the start method to initialize the CLI
    private void runMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. User Management");
            System.out.println("2. Exercise Management");
            System.out.println("3. Measurement Tracking");
            System.out.println("4. Workout Log Management");
            System.out.println("5. Favorite Exercises");
            System.out.println("0. Exit");
            System.out.print("\nSelect an option: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    userManagementMenu();
                    break;
                case 2:
                    exerciseManagementMenu();
                    break;
                case 3:
                    measurementMenu();
                    break;
                case 4:
                    workoutLogMenu();
                    break;
                case 5:
                    favoriteExerciseMenu();
                    break;
                case 0:
                    System.out.println("Thank you for using Body Tracker! Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }




    // This is the user menu and its functions
    private void userManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- USER MANAGEMENT ---");
            System.out.println("1. Create User");
            System.out.println("2. Get User by ID");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nSelect an option: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    getUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createUser() {
        System.out.print("Enter user ID: ");
        int id = getIntInput();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        var user = userService.createUser(id, firstName, lastName, email);
        if (user != null) {
            System.out.println("User created successfully!");
        } else {
            System.out.println("Failed to create user. User ID may already exist.");
        }
    }

    private void getUser() {
        System.out.print("Enter user ID: ");
        int id = getIntInput();
        var user = userService.getUser(id);
        if (user != null) {
            System.out.println("User found: " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail());
        } else {
            System.out.println("User not found.");
        }
    }

    private void updateUser() {
        System.out.print("Enter user ID to update: ");
        int id = getIntInput();
        System.out.print("Enter new first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter new last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        if (userService.updateUser(id, firstName, lastName, email)) {
            System.out.println("User updated successfully!");
        } else {
            System.out.println("Failed to update user. User may not exist or email is invalid.");
        }
    }

    private void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        int id = getIntInput();
        if (userService.deleteUser(id)) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Failed to delete user. User may not exist.");
        }
    }




    // This is the exercise menu and its functions
    private void exerciseManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- EXERCISE MANAGEMENT ---");
            System.out.println("1. Create Exercise");
            System.out.println("2. Get Exercise by ID");
            System.out.println("3. Update Exercise");
            System.out.println("4. Delete Exercise");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nSelect an option: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    createExercise();
                    break;
                case 2:
                    getExercise();
                    break;
                case 3:
                    updateExercise();
                    break;
                case 4:
                    deleteExercise();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createExercise() {
        System.out.print("Enter exercise ID: ");
        int id = getIntInput();
        System.out.print("Enter exercise name: ");
        String name = scanner.nextLine();
        System.out.print("Enter muscle group: ");
        String muscleGroup = scanner.nextLine();

        var exercise = exerciseService.createExercise(id, name, muscleGroup);
        if (exercise != null) {
            System.out.println("Exercise created successfully!");
        } else {
            System.out.println("Failed to create exercise. Exercise ID may already exist.");
        }
    }

    private void getExercise() {
        System.out.print("Enter exercise ID: ");
        int id = getIntInput();
        var exercise = exerciseService.getExercises(id);
        if (exercise != null) {
            System.out.println("Exercise found: " + exercise.getName() + " Muscle Group: " + exercise.getMuscleGroup());
        } else {
            System.out.println("Exercise not found.");
        }
    }

    private void updateExercise() {
        System.out.print("Enter exercise ID to update: ");
        int id = getIntInput();
        System.out.print("Enter new exercise name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new muscle group: ");
        String muscleGroup = scanner.nextLine();

        if (exerciseService.updateExercises(id, name, muscleGroup)) {
            System.out.println("Exercise updated successfully!");
        } else {
            System.out.println("Failed to update exercise. Exercise may not exist.");
        }
    }

    private void deleteExercise() {
        System.out.print("Enter exercise ID to delete: ");
        int id = getIntInput();
        if (exerciseService.deleteExercise(id)) {
            System.out.println("Exercise deleted successfully!");
        } else {
            System.out.println("Failed to delete exercise. Exercise may not exist.");
        }
    }






    // This is the measurement menu and its functions
    private void measurementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- MEASUREMENT TRACKING ---");
            System.out.println("1. Create Measurement");
            System.out.println("2. Get Measurement by ID");
            System.out.println("3. Get Measurement by User ID");
            System.out.println("4. Update Measurement");
            System.out.println("5. Delete Measurement");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nSelect an option: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    createMeasurement();
                    break;
                case 2:
                    getMeasurementById();
                    break;
                case 3:
                    getMeasurementByUserId();
                    break;
                case 4:
                    updateMeasurement();
                    break;
                case 5:
                    deleteMeasurement();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createMeasurement() {
        System.out.print("Enter measurement ID: ");
        int measurementId = getIntInput();
        System.out.print("Enter user ID: ");
        int userId = getIntInput();
        System.out.print("Enter weight (lbs): ");
        double weight = getDoubleInput();
        System.out.print("Enter chest measurement (inches): ");
        double chest = getDoubleInput();
        System.out.print("Enter arm measurement (inches): ");
        double arms = getDoubleInput();
        System.out.print("Enter waist measurement (inches): ");
        double waist = getDoubleInput();
        System.out.print("Enter body fat percentage: ");
        double bodyFat = getDoubleInput();

        var measurement = measurementService.createMeasurements(measurementId, userId, weight, chest, arms, waist, bodyFat);
        if (measurement != null) {
            System.out.println("Measurement created successfully!");
        } else {
            System.out.println("Failed to create measurement. Measurement ID may already exist or User ID does not exist.");
        }
    }

    private void getMeasurementById() {
        System.out.print("Enter measurement ID: ");
        int id = getIntInput();
        var measurement = measurementService.getMeasurementsById(id);
        if (measurement != null) {
            System.out.println("Measurement found:");
            System.out.println(" User ID: " + measurement.getUserID());
            System.out.println("  Weight: " + measurement.getWeight() + " lbs");
            System.out.println("  Chest: " + measurement.getChest() + " inches");
            System.out.println("  Arms: " + measurement.getArms() + " inches");
            System.out.println("  Waist: " + measurement.getWaist() + " inches");
            System.out.println("  Body Fat: " + measurement.getBodyFat() + "%");
        } else {
            System.out.println("Measurement not found.");
        }
    }

    private void getMeasurementByUserId() {
        System.out.print("Enter user ID: ");
        int userId = getIntInput();
        var measurement = measurementService.getMeasurementsByUserID(userId);
        if (measurement != null) {
            System.out.println("Measurement found:");
            System.out.println("  Weight: " + measurement.getWeight() + " lbs");
            System.out.println("  Chest: " + measurement.getChest() + " inches");
            System.out.println("  Arms: " + measurement.getArms() + " inches");
            System.out.println("  Waist: " + measurement.getWaist() + " inches");
            System.out.println("  Body Fat: " + measurement.getBodyFat() + "%");
        } else {
            System.out.println("Measurement not found for this user.");
        }
    }

    private void updateMeasurement() {
        System.out.print("Enter measurement ID to update: ");
        int measurementId = getIntInput();
        System.out.print("Enter user ID: ");
        int userId = getIntInput();
        System.out.print("Enter new weight (lbs): ");
        double weight = getDoubleInput();
        System.out.print("Enter new chest measurement (inches): ");
        double chest = getDoubleInput();
        System.out.print("Enter new arm measurement (inches): ");
        double arms = getDoubleInput();
        System.out.print("Enter new waist measurement (inches): ");
        double waist = getDoubleInput();
        System.out.print("Enter new body fat percentage: ");
        double bodyFat = getDoubleInput();

        if (measurementService.updateMeasurement(measurementId, userId, weight, chest, arms, waist, bodyFat)) {
            System.out.println("Measurement updated successfully!");
        } else {
            System.out.println("Failed to update measurement. Measurement may not exist or User ID does not exist.");
        }
    }

    private void deleteMeasurement() {
        System.out.print("Enter measurement ID to delete: ");
        int id = getIntInput();
        if (measurementService.deleteMeasurement(id)) {
            System.out.println("Measurement deleted successfully!");
        } else {
            System.out.println("Failed to delete measurement. Measurement may not exist.");
        }
    }






    // This is the workout log menus and its functions
    private void workoutLogMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- WORKOUT LOG MANAGEMENT ---");
            System.out.println("1. Create Workout Log");
            System.out.println("2. Get Workout Log by ID");
            System.out.println("3. Get Workout Logs by User");
            System.out.println("4. Get Workout Log by Exercise");
            System.out.println("5. Update Workout Log");
            System.out.println("6. Delete Workout Log");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nSelect an option: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    createWorkoutLog();
                    break;
                case 2:
                    getWorkoutLogById();
                    break;
                case 3:
                    getWorkoutLogsByUser();
                    break;
                case 4:
                    getWorkoutLogByExercise();
                    break;
                case 5:
                    updateWorkoutLog();
                    break;
                case 6:
                    deleteWorkoutLog();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createWorkoutLog() {
        System.out.print("Enter workout ID: ");
        int workoutId = getIntInput();
        System.out.print("Enter user ID: ");
        int userId = getIntInput();
        System.out.print("Enter exercise ID: ");
        int exerciseId = getIntInput();
        System.out.print("Enter number of sets: ");
        int sets = getIntInput();
        System.out.print("Enter number of reps: ");
        int reps = getIntInput();
        System.out.print("Enter weight (lbs): ");
        double weight = getDoubleInput();

        var log = workoutLogService.createWorkoutLog(workoutId, userId, exerciseId, sets, reps, weight);
        if (log != null) {
            System.out.println("Workout log created successfully!");
        } else {
            System.out.println("Failed to create workout log. Workout ID may already exist, or User ID/Exercise ID does not exist.");
        }
    }

    private void getWorkoutLogById() {
        System.out.print("Enter workout ID: ");
        int id = getIntInput();
        var log = workoutLogService.getWorkoutLog(id);
        if (log != null) {
            System.out.println("Workout log found:");
            System.out.println("  User ID: " + log.getUserID());
            System.out.println("  Exercise ID: " + log.getExerciseID());
            System.out.println("  Sets: " + log.getSets());
            System.out.println("  Reps: " + log.getReps());
            System.out.println("  Weight: " + log.getWeight() + " lbs");
            System.out.println("  Date: " + log.getLogDate());
        } else {
            System.out.println("Workout log not found.");
        }
    }

    private void getWorkoutLogsByUser() {
        System.out.print("Enter user ID: ");
        int userId = getIntInput();
        var log = workoutLogService.getWorkoutLogsByUser(userId);
        if (log != null) {
            System.out.println("Workout log found:");
            System.out.println("  Exercise ID: " + log.getExerciseID());
            System.out.println("  Sets: " + log.getSets());
            System.out.println("  Reps: " + log.getReps());
            System.out.println("  Weight: " + log.getWeight() + " lbs");
            System.out.println("  Date: " + log.getLogDate());
        } else {
            System.out.println("No workout logs found for this user.");
        }
    }

    private void getWorkoutLogByExercise() {
        System.out.print("Enter user ID: ");
        int userId = getIntInput();
        System.out.print("Enter exercise ID: ");
        int exerciseId = getIntInput();
        var log = workoutLogService.getWorkoutLogByExercise(userId, exerciseId);
        if (log != null) {
            System.out.println("Workout log found:");
            System.out.println("  Sets: " + log.getSets());
            System.out.println("  Reps: " + log.getReps());
            System.out.println("  Weight: " + log.getWeight() + " lbs");
            System.out.println("  Date: " + log.getLogDate());
        } else {
            System.out.println("No workout log found for this user and exercise.");
        }
    }

    private void updateWorkoutLog() {
        System.out.print("Enter workout ID to update: ");
        int workoutId = getIntInput();
        System.out.print("Enter new number of sets: ");
        int sets = getIntInput();
        System.out.print("Enter new number of reps: ");
        int reps = getIntInput();
        System.out.print("Enter new weight (lbs): ");
        double weight = getDoubleInput();

        if (workoutLogService.updateWorkoutLog(workoutId, sets, reps, weight)) {
            System.out.println("Workout log updated successfully!");
        } else {
            System.out.println("Failed to update workout log. Workout log may not exist.");
        }
    }

    private void deleteWorkoutLog() {
        System.out.print("Enter workout ID to delete: ");
        int id = getIntInput();
        if (workoutLogService.deleteWorkoutLog(id)) {
            System.out.println("Workout log deleted successfully!");
        } else {
            System.out.println("Failed to delete workout log. Workout log may not exist.");
        }
    }




    // This is the favorite exercises menu and its functions
    private void favoriteExerciseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- FAVORITE EXERCISES ---");
            System.out.println("1. Add Favorite Exercise");
            System.out.println("2. Get Favorite Exercise by User");
            System.out.println("3. Update Favorite Exercise");
            System.out.println("4. Delete Favorite Exercise");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nSelect an option: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addFavoriteExercise();
                    break;
                case 2:
                    getFavoriteExercise();
                    break;
                case 3:
                    updateFavoriteExercise();
                    break;
                case 4:
                    deleteFavoriteExercise();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addFavoriteExercise() {
        System.out.print("Enter user ID: ");
        int userId = getIntInput();
        System.out.print("Enter exercise ID: ");
        int exerciseId = getIntInput();

        var favorite = userExerciseService.addFavoriteExercise(userId, exerciseId);
        if (favorite != null) {
            System.out.println("Favorite exercise added successfully!");
        } else {
            System.out.println("Failed to add favorite exercise. User may already have a favorite exercise, or User ID/Exercise ID does not exist.");
        }
    }

    private void getFavoriteExercise() {
        System.out.print("Enter user ID: ");
        int userId = getIntInput();
        var favorite = userExerciseService.getFavoritesByUser(userId);
        if (favorite != null) {
            System.out.println("Favorite exercise found:");
            System.out.println("  User ID: " + favorite.getUserID());
            System.out.println("  Exercise ID: " + favorite.getExerciseID());
        } else {
            System.out.println("No favorite exercise found for this user.");
        }
    }

    private void updateFavoriteExercise() {
        System.out.print("Enter user ID: ");
        int userId = getIntInput();
        System.out.print("Enter new exercise ID: ");
        int exerciseId = getIntInput();

        if (userExerciseService.updateFavoriteExercise(userId, exerciseId)) {
            System.out.println("Favorite exercise updated successfully!");
        } else {
            System.out.println("Failed to update favorite exercise. Favorite exercise may not exist or Exercise ID does not exist.");
        }
    }

    private void deleteFavoriteExercise() {
        System.out.print("Enter exercise ID to remove from favorites: ");
        int exerciseId = getIntInput();
        if (userExerciseService.deleteFavoriteExercise(exerciseId)) {
            System.out.println("Favorite exercise deleted successfully!");
        } else {
            System.out.println("Failed to delete favorite exercise. Favorite exercise may not exist.");
        }
    }








    // Helper Methods to handle incorrect input from the user

    //Makes sure the input is an int
    private int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }


    // makes sure the input is a double
    private double getDoubleInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}

