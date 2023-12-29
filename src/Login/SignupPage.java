package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Constants.Constants.Fonts.LOGIN_FONT;
import static Constants.Constants.FrameSizes.LOGIN_SIZE;
import static Constants.Constants.FrameSizes.SIGNUP_PANEL_SIZE;

public class SignupPage extends JFrame implements ActionListener {

    JPanel usernamePanel;
    JPanel passwordPanel;
    JPanel confirmPasswordPanel;
    JPanel loginPanel;

    JTextField usernameField;
    JTextField passwordField;
    JTextField confirmPasswordField;
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
        confirmPasswordLabel = new JLabel("Confirm Password: ");
        passwordLabel.setFont(LOGIN_FONT);
        usernameField = new JTextField("Username");
        usernameField.setFont(LOGIN_FONT);
        passwordField = new JTextField("Password");
        passwordField.setFont(LOGIN_FONT);
        confirmPasswordField = new JTextField("Password");
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

        }
        if (e.getSource() == loginPage) {
            this.dispose();
            new LoginPage();
        }
    }
}
