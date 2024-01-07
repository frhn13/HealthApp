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
    JPanel topPanel;
    JPanel bottomPanel;
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
    JLabel chestInfo;
    JLabel absInfo;
    JLabel legsInfo;
    JLabel armsInfo;
    JLabel totalInfo;
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
        monthTextField = new JTextField();
        monthTextField.setFont(EXERCISE_FONT);
        yearLabel = new JLabel("Enter year to view:");
        yearLabel.setFont(EXERCISE_FONT);
        yearTextField = new JTextField();
        yearTextField.setFont(EXERCISE_FONT);
        chestInfo = new JLabel();
        chestInfo.setFont(PREV_EXERCISE_FONT);
        absInfo = new JLabel();
        absInfo.setFont(PREV_EXERCISE_FONT);
        legsInfo = new JLabel();
        legsInfo.setFont(PREV_EXERCISE_FONT);
        armsInfo = new JLabel();
        armsInfo.setFont(PREV_EXERCISE_FONT);
        totalInfo = new JLabel();
        totalInfo.setFont(PREV_EXERCISE_FONT);
        monthComparison = new JLabel();
        monthComparison.setFont(PREV_EXERCISE_FONT);
        viewReportButton = new JButton("Generate Report");
        viewReportButton.setFont(PREV_EXERCISE_FONT);
        viewReportButton.addActionListener(this);
        viewReportButton.setSize(200, 50);
        exerciseButton = new JButton("Enter Another Exercise");
        exerciseButton.setFont(PREV_EXERCISE_FONT);
        exerciseButton.addActionListener(this);
        exerciseButton.setSize(200, 50);
        mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setFont(PREV_EXERCISE_FONT);
        mainMenuButton.addActionListener(this);
        mainMenuButton.setSize(200, 50);

        topPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        bottomPanel = new JPanel(new BorderLayout());
        titlePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        titlePanel.add(titleLabel);
        titlePanel.add(informationLabel);
        detailsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        detailsPanel.add(monthLabel);
        detailsPanel.add(monthTextField);
        detailsPanel.add(yearLabel);
        detailsPanel.add(yearTextField);
        buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(viewReportButton);
        buttonPanel.add(exerciseButton);
        buttonPanel.add(mainMenuButton);
        monthlyExercisePanel = new JPanel(new GridLayout(6, 1, 10, 5));
        monthlyExercisePanel.add(chestInfo);
        monthlyExercisePanel.add(absInfo);
        monthlyExercisePanel.add(legsInfo);
        monthlyExercisePanel.add(armsInfo);
        monthlyExercisePanel.add(totalInfo);
        monthlyExercisePanel.add(monthComparison);
        topPanel.add(titlePanel);
        topPanel.add(detailsPanel);
        bottomPanel.add(monthlyExercisePanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_SIZE);
        this.setLayout(new GridLayout(2, 1, 10, 10));
        this.add(topPanel);
        this.add(bottomPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewReportButton) {
            try {
                ArrayList<ArrayList<String>> exerciseValues = readFromExerciseFile(username);
                int armsTotal = 0;
                int legsTotal = 0;
                int absTotal = 0;
                int chestTotal = 0;
                int totalExercises = 0;
                int prevTotal = 0;
                for (ArrayList<String> exerciseValue : exerciseValues) {
                    if (exerciseValue.get(3).substring(3, 5).equals(monthTextField.getText()) && exerciseValue.get(3).substring(6, 10).equals(yearTextField.getText())) {
                        switch (exerciseValue.get(1)) {
                            case "Chest" -> chestTotal += Integer.parseInt(exerciseValue.get(2));
                            case "Arms" -> armsTotal += Integer.parseInt(exerciseValue.get(2));
                            case "Legs" -> legsTotal += Integer.parseInt(exerciseValue.get(2));
                            case "Abs" -> absTotal += Integer.parseInt(exerciseValue.get(2));
                        }
                    }
                    if (Integer.parseInt(exerciseValue.get(3).substring(3, 5)) == Integer.parseInt(monthTextField.getText()) - 1 && Integer.parseInt(exerciseValue.get(3).substring(6, 10)) == Integer.parseInt(yearTextField.getText())) {
                        prevTotal += Integer.parseInt(exerciseValue.get(2));
                    }
                }
                totalExercises = chestTotal + armsTotal + legsTotal + absTotal;

                chestInfo.setText("Minutes spent on chest: " + chestTotal);
                absInfo.setText("Minutes spent on arms: " + armsTotal);
                legsInfo.setText("Minutes spent on legs: " + legsTotal);
                armsInfo.setText("Minutes spent on abs: " + absTotal);
                totalInfo.setText("Minutes spent in total: " + totalExercises);
                if (prevTotal == 0) {
                    monthComparison.setText("No data from previous month in the same year to compare to.");
                } else if (totalExercises < prevTotal) {
                    monthComparison.setForeground(new Color(189, 0, 0, 255));
                    monthComparison.setText("You worked out for " + (prevTotal - totalExercises) + " minutes less than last month.");
                } else if (totalExercises > prevTotal) {
                    monthComparison.setForeground(new Color(45, 224, 45, 255));
                    monthComparison.setText("You worked out for " + (totalExercises - prevTotal) + " minutes more than last month.");
                } else {
                    monthComparison.setForeground(new Color(204, 204, 0));
                    monthComparison.setText("You worked out for the last amount of time as last month.");
                }
            }
            catch (NumberFormatException ex) {
                System.out.println("Month and year entered must be in a numeric form.");
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
