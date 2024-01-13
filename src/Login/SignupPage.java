package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static Constants.Constants.Fonts.LOGIN_FONT;
import static Constants.Constants.Fonts.TITLE_FONT;
import static Constants.Constants.FrameSizes.LOGIN_SIZE;
import static Constants.Constants.FrameSizes.SIGNUP_PANEL_SIZE;
import static Files.FileFunctions.checkSignupDetails;
import static Files.FileFunctions.checkSignupFile;

public class SignupPage extends JFrame implements ActionListener {

    JPanel titlePanel;
    JPanel usernamePanel;
    JPanel passwordPanel;
    JPanel confirmPasswordPanel;
    JPanel loginPanel;

    JTextField usernameField;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel confirmPasswordLabel;
    JLabel titleLabel;
    JButton loginPage;
    JButton signupButton;

    SignupPage() {
        titlePanel = new JPanel();
        usernamePanel = new JPanel();
        passwordPanel = new JPanel();
        confirmPasswordPanel = new JPanel();
        loginPanel = new JPanel();

        titlePanel.setSize(SIGNUP_PANEL_SIZE);
        usernamePanel.setSize(SIGNUP_PANEL_SIZE);
        passwordPanel.setSize(SIGNUP_PANEL_SIZE);
        confirmPasswordPanel.setSize(SIGNUP_PANEL_SIZE);
        loginPanel.setSize(SIGNUP_PANEL_SIZE);

        titleLabel = new JLabel("Sign-Up Page");
        titleLabel.setFont(TITLE_FONT);
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(LOGIN_FONT);
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(LOGIN_FONT);
        passwordLabel.setFont(LOGIN_FONT);
        confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setFont(LOGIN_FONT);
        usernameField = new JTextField("Username");
        usernameField.setFont(LOGIN_FONT);
        passwordField = new JPasswordField("PasswordEnter");
        passwordField.setFont(LOGIN_FONT);
        confirmPasswordField = new JPasswordField("PasswordEnter");
        confirmPasswordField.setFont(LOGIN_FONT);
        loginPage = new JButton("Login Page");
        loginPage.setFont(LOGIN_FONT);
        signupButton = new JButton("Sign Up");
        signupButton.setFont(LOGIN_FONT);
        loginPage.addActionListener(this);
        signupButton.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(LOGIN_SIZE);
        this.setLayout(new GridLayout(5, 1, 10, 50));

        titlePanel.add(titleLabel);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPasswordField);
        loginPanel.add(signupButton);
        loginPanel.add(loginPage);

        this.add(titlePanel);
        this.add(usernamePanel);
        this.add(passwordPanel);
        this.add(confirmPasswordPanel);
        this.add(loginPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            boolean isUpper = false;
            boolean isLower = false;
            boolean isNumeric = false;
            boolean isSymbol = false;
            boolean hasSpaces = false;
            boolean validDetails = true;
            if (!Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {
                JOptionPane.showMessageDialog(null,
                        "Passwords aren't the same.",
                        "Invalid Password", JOptionPane.ERROR_MESSAGE);
                validDetails = false;
            }
            for (int x=0; x<passwordField.getPassword().length; x++) {
                if (Character.isUpperCase(passwordField.getPassword()[x])) isUpper = true;
                if (Character.isLowerCase(passwordField.getPassword()[x])) isLower = true;
                if (Character.isDigit(passwordField.getPassword()[x])) isNumeric = true;
                if (passwordField.getPassword()[x] != 32 &&
                        (passwordField.getPassword()[x] < 65 || passwordField.getPassword()[x] > 90) &&  //not an uppercase alphabet
                        (passwordField.getPassword()[x] < 97 || passwordField.getPassword()[x] > 122)) isSymbol = true;  //not a lowercase alphabet)
            }
            if (!isLower || !isUpper || !isNumeric || !isSymbol) {
                JOptionPane.showMessageDialog(null,
                        "Password should contain at least one upper and lower case letter, one number and one special symbol.",
                        "Invalid Password", JOptionPane.ERROR_MESSAGE);
                validDetails = false;
            }
            if (passwordField.getPassword().length > 20 || passwordField.getPassword().length < 8) {
                JOptionPane.showMessageDialog(null, "Password should be between 8 and 20 characters.",
                        "Invalid Password", JOptionPane.ERROR_MESSAGE);
                validDetails = false;
            }
            if (usernameField.getText().length() > 15 || usernameField.getText().length() < 5) {
                JOptionPane.showMessageDialog(null, "Username should be between 5 and 15 characters.",
                        "Invalid Password", JOptionPane.ERROR_MESSAGE);
                validDetails = false;
            }
            for (int x=0; x<usernameField.getText().length(); x++)
                if (usernameField.getText().charAt(x) == ' ') hasSpaces = true;
            if (hasSpaces) {
                JOptionPane.showMessageDialog(null, "Username shouldn't have any spaces.",
                        "Invalid Password", JOptionPane.ERROR_MESSAGE);
                validDetails = false;
            }
            if (checkSignupDetails(usernameField.getText())) {
                JOptionPane.showMessageDialog(null, "Username already exists.",
                        "Invalid Password", JOptionPane.ERROR_MESSAGE);
                validDetails = false;
            }
            if (validDetails) {
                checkSignupFile(usernameField.getText(), passwordField.getText());
                this.dispose();
                new LoginPage();
            }
        }
        if (e.getSource() == loginPage) {
            this.dispose();
            new LoginPage();
        }
    }
}
