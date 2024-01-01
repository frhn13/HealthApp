package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static Constants.Constants.Fonts.LOGIN_FONT;
import static Constants.Constants.FrameSizes.LOGIN_SIZE;
import static Constants.Constants.FrameSizes.SIGNUP_PANEL_SIZE;
import static Files.FileFunctions.checkSignupFile;

public class SignupPage extends JFrame implements ActionListener {

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
    JButton loginPage;
    JButton signupButton;

    SignupPage() {
        usernamePanel = new JPanel();
        passwordPanel = new JPanel();
        confirmPasswordPanel = new JPanel();
        loginPanel = new JPanel();

        usernamePanel.setSize(SIGNUP_PANEL_SIZE);
        passwordPanel.setSize(SIGNUP_PANEL_SIZE);
        confirmPasswordPanel.setSize(SIGNUP_PANEL_SIZE);
        loginPanel.setSize(SIGNUP_PANEL_SIZE);

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
        this.setLayout(new BorderLayout());

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPasswordField);
        loginPanel.add(signupButton);
        loginPanel.add(loginPage);

        this.add(usernamePanel, BorderLayout.NORTH);
        this.add(passwordPanel, BorderLayout.WEST);
        this.add(confirmPasswordPanel, BorderLayout.EAST);
        this.add(loginPanel, BorderLayout.SOUTH);
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
                System.out.println("Passwords aren't the same");
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
                System.out.println("Password should contain at least one upper and lower case letter, one number and one special symbol.");
                validDetails = false;
            }
            if (passwordField.getPassword().length > 20 || passwordField.getPassword().length < 8) {
                System.out.println("Password should be between 8 and 20 characters.");
                validDetails = false;
            }
            if (usernameField.getText().length() > 15 || usernameField.getText().length() < 5) {
                System.out.println("Username should be between 5 and 15 characters.");
                validDetails = false;
            }
            for (int x=0; x<usernameField.getText().length(); x++)
                if (usernameField.getText().charAt(x) == ' ') hasSpaces = true;
            if (hasSpaces) {
                System.out.println("Username shouldn't have any spaces");
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
