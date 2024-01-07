package ExerciseTracker;

import MainPage.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;

import static Constants.Constants.Fonts.*;
import static Constants.Constants.FrameSizes.DEFAULT_SIZE;
import static Files.FileFunctions.readFromExerciseFile;

public class MonthlyExerciseReport extends JFrame implements ActionListener {

    String username;
    JPanel titlePanel;
    JPanel detailsPanel;
    JPanel monthlyExercisePanel;
    JPanel buttonPanel;
    JLabel titleLabel;
    JLabel informationLabel;
    JLabel monthLabel;
    JTextField monthTextField;
    JLabel yearLabel;
    JTextField yearTextField;
    JLabel monthInfo;
    JLabel monthComparison;
    JButton viewReportButton;
    JButton exerciseButton;
    JButton mainMenuButton;


    MonthlyExerciseReport(String username) {

        this.username = username;
        titleLabel = new JLabel("Monthly Exercise Report");
        titleLabel.setFont(TITLE_FONT);
        informationLabel = new JLabel("Find your exercise information from month to month");
        informationLabel.setFont(EXERCISE_FONT);
        monthLabel = new JLabel("Enter month to view:");
        monthLabel.setFont(EXERCISE_FONT);
        monthTextField = new JTextField("Month");
        monthTextField.setFont(EXERCISE_FONT);
        yearLabel = new JLabel("Enter year to view:");
        yearLabel.setFont(EXERCISE_FONT);
        yearTextField = new JTextField("Year");
        yearTextField.setFont(EXERCISE_FONT);
        monthInfo = new JLabel();
        monthInfo.setFont(EXERCISE_FONT);
        monthComparison = new JLabel();
        monthComparison.setFont(PREV_EXERCISE_FONT);
        viewReportButton = new JButton("Generate Report");
        viewReportButton.setFont(PREV_EXERCISE_FONT);
        viewReportButton.addActionListener(this);
        exerciseButton = new JButton("Enter Another Exercise");
        exerciseButton.setFont(PREV_EXERCISE_FONT);
        exerciseButton.addActionListener(this);
        mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setFont(PREV_EXERCISE_FONT);
        mainMenuButton.addActionListener(this);

        titlePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        titlePanel.add(titleLabel);
        titlePanel.add(informationLabel);
        detailsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        detailsPanel.add(monthLabel);
        detailsPanel.add(monthTextField);
        detailsPanel.add(yearLabel);
        detailsPanel.add(yearTextField);
        monthlyExercisePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        monthlyExercisePanel.add(monthInfo);
        monthlyExercisePanel.add(monthComparison);
        buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(viewReportButton);
        buttonPanel.add(exerciseButton);
        buttonPanel.add(mainMenuButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_SIZE);
        this.setLayout(new GridLayout(4, 1, 10, 10));
        this.add(titlePanel);
        this.add(detailsPanel);
        this.add(buttonPanel);
        this.add(monthlyExercisePanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewReportButton) {
            ArrayList<ArrayList<String>> exerciseValues = readFromExerciseFile(username);
            int armsTotal = 0;
            int legsTotal = 0;
            int absTotal = 0;
            int chestTotal = 0;
            int totalExercises = 0;
            int prevTotal = 0;
            for (int x=0; x<exerciseValues.size(); x++) {
                if (exerciseValues.get(x).get(3).substring(3, 5).equals(monthTextField.getText()) && exerciseValues.get(x).get(3).substring(6, 10).equals(yearTextField.getText())) {
                    switch (exerciseValues.get(x).get(1)) {
                        case "Chest" -> chestTotal += Integer.parseInt(exerciseValues.get(x).get(2));
                        case "Arms" -> armsTotal += Integer.parseInt(exerciseValues.get(x).get(2));
                        case "Legs" -> legsTotal += Integer.parseInt(exerciseValues.get(x).get(2));
                        case "Abs" -> absTotal += Integer.parseInt(exerciseValues.get(x).get(2));
                    }
                }
                if (Integer.parseInt(exerciseValues.get(x).get(3).substring(3, 5)) == Integer.parseInt(monthTextField.getText())-1 && exerciseValues.get(x).get(3).substring(6, 10).equals(yearTextField.getText())) {
                    prevTotal += Integer.parseInt(exerciseValues.get(x).get(2));
                }
            }
            totalExercises = chestTotal + armsTotal + legsTotal + absTotal;
            monthInfo.setText("Time spent on chest: " + chestTotal + "Time spent on arms: " + armsTotal + "Time spent on legs: " + legsTotal + "Time spent on abs: " + absTotal);
            if (prevTotal == 0) {
                monthComparison.setText("No data from previous month in the same year to compare to.");
            }
            else if (totalExercises < prevTotal) {
                monthComparison.setForeground(new Color(189, 0, 0, 255));
                monthComparison.setText("You worked out for " + (prevTotal-totalExercises) + " minutes less than last month.");
            }
            else if (totalExercises > prevTotal) {
                monthComparison.setForeground(new Color(45, 224, 45, 255));
                monthComparison.setText("You worked out for " + (totalExercises-prevTotal) + " minutes more than last month.");
            }
            else {
                monthComparison.setForeground(new Color(204,204,0));
                monthComparison.setText("You worked out for the last amount of time as last month.");
            }
        }
        if (e.getSource() == exerciseButton) {
            this.dispose();
            new ExercisePage(username);
        }
        if (e.getSource() == mainMenuButton) {
            this.dispose();
            new MainMenu(username);
        }
    }
}
