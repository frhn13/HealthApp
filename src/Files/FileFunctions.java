package Files;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileFunctions {
    public static boolean checkLoginFile(String username, String password) {
        String line;
        ArrayList<ArrayList<String>> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("csvFiles/users.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] row = line.split(","); // Comma is the delimiter between each attribute
                ArrayList<String> details = new ArrayList<>();
                details.add(row[0]);
                details.add(row[1]);
                users.add(details);
            }
            for (int x = 0; x < users.size(); x++) {
                if (username.equals(users.get(x).get(0)) && password.equals(users.get(x).get(1))) {
                    return true;
                }
            }
            JOptionPane.showMessageDialog(null, "Invalid login details.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No users exist yet.",
                    "Login Failed", JOptionPane.QUESTION_MESSAGE);
            return false;
        }
    }
    public static void checkSignupFile(String username, String password) {
        try {
            FileWriter writer = new FileWriter("csvFiles/users.csv", true);
            writer.write(username + "," + password + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkSignupDetails(String username) {
        String line;
        ArrayList<ArrayList<String>> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("csvFiles/users.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] row = line.split(","); // Comma is the delimiter between each attribute
                ArrayList<String> details = new ArrayList<>();
                details.add(row[0]);
                details.add(row[1]);
                users.add(details);
            }
            for (ArrayList<String> user : users) {
                if (username.equals(user.get(0)))
                    return true;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void writeToBMIFile(String username, String height, String weight, String BMI, String date) {
        try {
            FileWriter writer = new FileWriter("csvFiles/BMI_values.csv", true);
            writer.write(username + "," + height + "," + weight + "," + BMI + "," + date + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<ArrayList<String>> readFromBMIFile(String username) {
        String line;
        ArrayList<ArrayList<String>> BMIValues = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("csvFiles/BMI_values.csv"))){
            while ((line = br.readLine()) != null) {
                String[] row = line.split(","); // Comma is the delimiter between each attribute
                if (row[0].equals(username)) {
                    ArrayList<String> BMIDetails = new ArrayList<>();
                    BMIDetails.add(row[0]);
                    BMIDetails.add(row[1]);
                    BMIDetails.add(row[2]);
                    BMIDetails.add(row[3]);
                    BMIDetails.add(row[4]);
                    BMIValues.add(BMIDetails);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No BMI values entered yet.",
                    "BMI not Found", JOptionPane.QUESTION_MESSAGE);
        }
        return BMIValues;
    }
    public static void writeToExerciseFile(String username, String exerciseType, int duration, String date) {
        try {
            FileWriter writer = new FileWriter("csvFiles/exercise_values.csv", true);
            writer.write(username + "," + exerciseType + "," + duration + "," + date + "\n");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<ArrayList<String>> readFromExerciseFile(String username) {
        String line;
        ArrayList<ArrayList<String>> exerciseValues = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("csvFiles/exercise_values.csv"))){
            while ((line = br.readLine()) != null) {
                String[] row = line.split(","); // Comma is the delimiter between each attribute
                if (row[0].equals(username)) {
                    ArrayList<String> exerciseDetails = new ArrayList<>();
                    exerciseDetails.add(row[0]);
                    exerciseDetails.add(row[1]);
                    exerciseDetails.add(row[2]);
                    exerciseDetails.add(row[3]);
                    exerciseValues.add(exerciseDetails);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No exercise values entered yet.",
                    "Exercise not Found", JOptionPane.QUESTION_MESSAGE);
        }
        return exerciseValues;
    }
}
