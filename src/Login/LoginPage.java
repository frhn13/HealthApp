package Login;

import MainPage.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static Constants.Constants.Fonts.LOGIN_FONT;
import static Constants.Constants.FrameSizes.LOGIN_SIZE;
import static Constants.Constants.FrameSizes.LOGIN_PANEL_SIZE;
import static Files.FileFunctions.checkLoginFile;

public class LoginPage extends JFrame implements ActionListener {

    JPanel usernamePanel;
    JPanel passwordPanel;
    JPanel loginPanel;

    JTextField usernameField;
    JPasswordField passwordField;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JButton loginButton;
    JButton signupPage;

    public LoginPage() {

        usernamePanel = new JPanel();
        passwordPanel = new JPanel();
        loginPanel = new JPanel();

        usernamePanel.setSize(LOGIN_PANEL_SIZE);
        passwordPanel.setSize(LOGIN_PANEL_SIZE);
        loginPanel.setSize(LOGIN_PANEL_SIZE);

        usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(LOGIN_FONT);
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(LOGIN_FONT);
        usernameField = new JTextField("Username");
        usernameField.setFont(LOGIN_FONT);
        passwordField = new JPasswordField("PasswordEnter");
        passwordField.setFont(LOGIN_FONT);
        loginButton = new JButton("Login");
        loginButton.setFont(LOGIN_FONT);
        signupPage = new JButton("Sign Up Page");
        signupPage.setFont(LOGIN_FONT);
        loginButton.addActionListener(this);
        signupPage.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(LOGIN_SIZE);
        this.setLayout(new BorderLayout());

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signupPage);

        this.add(usernamePanel, BorderLayout.NORTH);
        this.add(passwordPanel, BorderLayout.CENTER);
        this.add(loginPanel, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (checkLoginFile(usernameField.getText(), passwordField.getText())) {
                this.dispose();
                new MainMenu(usernameField.getText());
            }
//            String line;
//            ArrayList<ArrayList<String>> users = new ArrayList<>();
//
//            try (BufferedReader br = new BufferedReader(new FileReader("csvFiles/users.csv"))) {
//                while ((line = br.readLine()) != null) {
//                    String[] row = line.split(","); // Comma is the delimiter between each attribute
//                    ArrayList<String> details = new ArrayList<>();
//                    details.add(row[0]);
//                    details.add(row[1]);
//                    users.add(details);
//                }
//                for (int x = 0; x < users.size(); x++) {
//                    if (usernameField.getText().equals(users.get(x).get(0)) && passwordField.getText().equals(users.get(x).get(1))) {
//                        this.dispose();
//                        new MainMenu(usernameField.getText());
//                    }
//                }
//                System.out.println("Login failed.");
//            } catch (IOException ex) {
//                System.out.println("No users exist yet.");
//            }
        }
        if (e.getSource() == signupPage) {
            this.dispose();
            new SignupPage();
        }
    }
}
