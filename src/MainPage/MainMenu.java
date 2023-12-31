package MainPage;

import BMI.BMIPage;
import ExerciseTracker.ExercisePage;
import Login.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Constants.Constants.Fonts.MENU_FONT;
import static Constants.Constants.FrameSizes.DEFAULT_SIZE;

public class MainMenu extends JFrame implements ActionListener {

    JLabel introLabel;
    JButton BMIButton;
    JButton exerciseButton;
    JButton logoutButton;
    String username;

    public MainMenu(String username) {

        introLabel = new JLabel("Hello " + username + "!");
        BMIButton = new JButton("BMI Page");
        exerciseButton = new JButton("Exercise Page");
        logoutButton = new JButton("Logout");
        introLabel.setFont(MENU_FONT);
        BMIButton.setFont(MENU_FONT);
        exerciseButton.setFont(MENU_FONT);
        logoutButton.setFont(MENU_FONT);

        BMIButton.addActionListener(this);
        exerciseButton.addActionListener(this);
        logoutButton.addActionListener(this);

        this.username = username;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_SIZE);
        this.setLayout(new BorderLayout());

        this.add(introLabel, BorderLayout.NORTH);
        this.add(exerciseButton, BorderLayout.EAST);
        this.add(BMIButton, BorderLayout.WEST);
        this.add(logoutButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            this.dispose();
            new LoginPage();
        }
        if (e.getSource() == BMIButton) {
            this.dispose();
            new BMIPage(username);
        }
        if (e.getSource() == exerciseButton) {
            this.dispose();
            new ExercisePage();
        }
    }
}
