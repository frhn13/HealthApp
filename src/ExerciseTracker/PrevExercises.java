package ExerciseTracker;

import MainPage.MainMenu;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static Constants.Constants.Fonts.*;
import static Constants.Constants.FrameSizes.DEFAULT_SIZE;
import static Files.FileFunctions.readFromExerciseFile;

public class PrevExercises extends JFrame implements ActionListener, MouseListener {

    String username;
    JPanel titlePanel;
    JPanel oldExercises;
    JPanel buttonPanel;
    JScrollPane scrollPane;
    JLabel titleLabel;
    JLabel exerciseLabel;
    JButton exerciseButton;
    JButton mainMenuButton;

    public PrevExercises(String username) {
        this.username = username;
        ArrayList<ArrayList<String>> exercises = readFromExerciseFile(username);

        oldExercises = new JPanel(new GridLayout(exercises.size(), 1, 10, 10));
        for (ArrayList<String> row : exercises) {
            exerciseLabel = new JLabel("User: " + row.get(0) + ", Type: " + row.get(1) + ", Duration: " + row.get(2) + ", Date: " + row.get(3));
            exerciseLabel.setFont(PREV_EXERCISE_FONT);
            oldExercises.add(exerciseLabel);
        }
        scrollPane = new JScrollPane(oldExercises, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        titlePanel = new JPanel();
        buttonPanel = new JPanel(new BorderLayout());

        titleLabel = new JLabel(username + "'s Previous Exercises");
        titleLabel.setFont(TITLE_FONT);
        exerciseButton = new JButton("Enter Another Exercise");
        exerciseButton.setFont(EXERCISE_FONT);
        mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setFont(EXERCISE_FONT);
        exerciseButton.addActionListener(this);
        mainMenuButton.addActionListener(this);
        exerciseButton.addMouseListener(this);
        mainMenuButton.addMouseListener(this);

        titlePanel.add(titleLabel);
        buttonPanel.add(exerciseButton, BorderLayout.WEST);
        buttonPanel.add(mainMenuButton, BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_SIZE);
        this.setLayout(new FlowLayout());
        this.add(titlePanel);
        this.add(scrollPane);
        this.add(buttonPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuButton) {
            this.dispose();
            new MainMenu(username);
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
        if (e.getSource() == exerciseButton)
            exerciseButton.setBackground(new Color(0x04AF70));
        if (e.getSource() == mainMenuButton)
            mainMenuButton.setBackground(new Color(0x04AF70));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == exerciseButton)
            exerciseButton.setBackground(new ColorUIResource(238,238,238));
        if (e.getSource() == mainMenuButton)
            mainMenuButton.setBackground(new ColorUIResource(238,238,238));
    }
}
