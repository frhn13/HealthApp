package Login;

import MainPage.MainMenu;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Constants.Constants.Fonts.LOGIN_FONT;
import static Constants.Constants.Fonts.TITLE_FONT;
import static Constants.Constants.FrameSizes.LOGIN_SIZE;
import static Constants.Constants.FrameSizes.LOGIN_PANEL_SIZE;
import static Files.FileFunctions.checkLoginFile;

public class LoginPage extends JFrame implements ActionListener, MouseListener {

    JPanel titlePanel;
    JPanel usernamePanel;
    JPanel passwordPanel;
    JPanel loginPanel;

    JLabel titleLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JButton loginButton;
    JButton signupPage;

    public LoginPage() {

        titlePanel = new JPanel();
        usernamePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        passwordPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        loginPanel = new JPanel();

        titlePanel.setSize(LOGIN_PANEL_SIZE);
        usernamePanel.setSize(LOGIN_PANEL_SIZE);
        passwordPanel.setSize(LOGIN_PANEL_SIZE);
        loginPanel.setSize(LOGIN_PANEL_SIZE);

        titleLabel = new JLabel("Login Page");
        titleLabel.setFont(TITLE_FONT);
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(LOGIN_FONT);
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(LOGIN_FONT);
        usernameField = new JTextField();
        usernameField.setFont(LOGIN_FONT);
        passwordField = new JPasswordField();
        passwordField.setFont(LOGIN_FONT);
        loginButton = new JButton("Login");
        loginButton.setFont(LOGIN_FONT);
        signupPage = new JButton("Sign Up Page");
        signupPage.setFont(LOGIN_FONT);
        loginButton.addActionListener(this);
        signupPage.addActionListener(this);
        loginButton.addMouseListener(this);
        signupPage.addMouseListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(LOGIN_SIZE);
        this.setLayout(new GridLayout(4, 1, 10, 100));

        titlePanel.add(titleLabel);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signupPage);

        this.add(titlePanel);
        this.add(usernamePanel);
        this.add(passwordPanel);
        this.add(loginPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            loginButton.setBackground(Color.GREEN);
            if (checkLoginFile(usernameField.getText(), passwordField.getText())) {
                this.dispose();
                new MainMenu(usernameField.getText());
            }
        }
        if (e.getSource() == signupPage) {
            signupPage.setBackground(Color.GREEN);
            this.dispose();
            new SignupPage();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == loginButton) {
            loginButton.setBackground(new Color(0x04AF70));
        }
        if (e.getSource() == signupPage) {
            signupPage.setBackground(new Color(0x04AF70));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginButton)
            loginButton.setBackground(new ColorUIResource(238,238,238));
        if (e.getSource() == signupPage)
            signupPage.setBackground(new ColorUIResource(238,238,238));
    }
}
