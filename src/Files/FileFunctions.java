package Files;

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
            System.out.println("Login failed.");
            return false;
        } catch (IOException ex) {
            System.out.println("No users exist yet.");
            return false;
        }
    }
    public static void checkSignupFile(String username, String password) {
        try {
            FileWriter writer = new FileWriter("csvFiles/users.csv", true);
            writer.write(username + "," + password + "\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
