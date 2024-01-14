package MainPage;

import BMI.BMIPage;
import ExerciseTracker.ExercisePage;
import Login.LoginPage;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Constants.Constants.Fonts.MENU_FONT;
import static Constants.Constants.FrameSizes.DEFAULT_SIZE;

public class MainMenu extends JFrame implements ActionListener, MouseListener {

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
        BMIButton.addMouseListener(this);
        exerciseButton.addMouseListener(this);
        logoutButton.addMouseListener(this);

        this.username = username;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_SIZE);
        this.setLayout(new GridLayout(4, 1, 10, 100));

        this.add(introLabel);
        this.add(exerciseButton);
        this.add(BMIButton);
        this.add(logoutButton);

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
            new ExercisePage(username);
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
        if (e.getSource() == BMIButton)
            BMIButton.setBackground(new Color(0x04AF70));
        if (e.getSource() == exerciseButton)
            exerciseButton.setBackground(new Color(0x04AF70));
        if (e.getSource() == logoutButton)
            logoutButton.setBackground(new Color(0x04AF70));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == BMIButton)
            BMIButton.setBackground(new ColorUIResource(238,238,238));
        if (e.getSource() == exerciseButton)
            exerciseButton.setBackground(new ColorUIResource(238,238,238));
        if (e.getSource() == logoutButton)
            logoutButton.setBackground(new ColorUIResource(238,238,238));
    }
}
