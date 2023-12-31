package Login;

import MainPage.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static Constants.Constants.Fonts.LOGIN_FONT;
import static Constants.Constants.FrameSizes.LOGIN_SIZE;
import static Constants.Constants.FrameSizes.LOGIN_PANEL_SIZE;

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

    Map<String, String> login_details = new HashMap<>();


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

        login_details.put("User1", "Password1");
        login_details.put("User2", "Password2");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (passwordField.getText().equals(login_details.get(usernameField.getText()))) {
                this.dispose();
                new MainMenu(usernameField.getText());
            }
            else
                System.out.println("Login failed");
        }
        if (e.getSource() == signupPage) {
            this.dispose();
            new SignupPage();
        }
    }
}
