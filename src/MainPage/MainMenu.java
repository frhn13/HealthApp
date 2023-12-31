package MainPage;

import Login.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Constants.Constants.Fonts.MENU_FONT;
import static Constants.Constants.FrameSizes.LOGIN_SIZE;

public class MainMenu extends JFrame implements ActionListener {

    JLabel introLabel;
    JButton BMIPage;
    JButton exercisePage;
    JButton logoutButton;
    String username;

    public MainMenu(String username) {

        introLabel = new JLabel("Hello " + username + "!");
        BMIPage = new JButton("BMI Page");
        exercisePage = new JButton("Exercise Page");
        logoutButton = new JButton("Logout");
        introLabel.setFont(MENU_FONT);
        BMIPage.setFont(MENU_FONT);
        exercisePage.setFont(MENU_FONT);
        logoutButton.setFont(MENU_FONT);

        BMIPage.addActionListener(this);
        exercisePage.addActionListener(this);
        logoutButton.addActionListener(this);

        this.username = username;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(LOGIN_SIZE);
        this.setLayout(new BorderLayout());

        this.add(introLabel, BorderLayout.NORTH);
        this.add(exercisePage, BorderLayout.EAST);
        this.add(BMIPage, BorderLayout.WEST);
        this.add(logoutButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            this.dispose();
            new LoginPage();
        }
    }
}
