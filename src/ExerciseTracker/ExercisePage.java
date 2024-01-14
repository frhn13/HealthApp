package ExerciseTracker;

import MainPage.MainMenu;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

import static Constants.Constants.Fonts.*;
import static Constants.Constants.FrameSizes.*;
import static Files.FileFunctions.writeToExerciseFile;

public class ExercisePage extends JFrame implements ActionListener, MouseListener {

    String username;
    JPanel titlePanel;
    JPanel exercisePanel;
    JPanel bottomPanel;
    JLabel titleLabel;
    JLabel informationLabel;
    JLabel exerciseLabel;
    JComboBox exerciseComboBox;
    JLabel exerciseDuration;
    JTextField durationTextField;
    JLabel exerciseDate;
    JTextField dateTextField;
    JButton submitExerciseButton;
    JButton mainMenuButton;
    JButton previousExercisesButton;
    JButton monthlyExercisesButton;


    // Record hours spent exercising and on which area each day (use radio buttons)
    // Talk about amount of time spent working out each month
    // Talk about areas focused on each month

    public ExercisePage(String username) {

        this.username = username;
        String[] exercises = {"Arms", "Legs", "Abs", "Chest"};
        titleLabel = new JLabel(username + "'s Exercise Page");
        titleLabel.setFont(TITLE_FONT);
        informationLabel = new JLabel("Here you can enter exercise information.");
        informationLabel.setFont(EXERCISE_FONT);
        exerciseLabel = new JLabel("Type of exercise: ");
        exerciseLabel.setFont(EXERCISE_FONT);
        exerciseComboBox = new JComboBox(exercises);
        exerciseComboBox.setFont(EXERCISE_FONT);
        exerciseDuration = new JLabel("Duration of exercise in minutes: ");
        exerciseDuration.setFont(EXERCISE_FONT);
        durationTextField = new JTextField();
        durationTextField.setSize(80, 100);
        durationTextField.setFont(EXERCISE_FONT);
        exerciseDate = new JLabel("Date of exercise: ");
        exerciseDate.setFont(EXERCISE_FONT);
        dateTextField = new JTextField();
        dateTextField.setSize(80, 100);
        dateTextField.setFont(EXERCISE_FONT);
        submitExerciseButton = new JButton("Submit Exercise");
        submitExerciseButton.addActionListener(this);
        submitExerciseButton.addMouseListener(this);
        submitExerciseButton.setFont(PREV_EXERCISE_FONT);
        mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.addMouseListener(this);
        mainMenuButton.setFont(PREV_EXERCISE_FONT);
        previousExercisesButton = new JButton("View Previous Exercises");
        previousExercisesButton.addActionListener(this);
        previousExercisesButton.addMouseListener(this);
        previousExercisesButton.setFont(PREV_EXERCISE_FONT);
        monthlyExercisesButton = new JButton("Monthly Exercise Report");
        monthlyExercisesButton.addActionListener(this);
        monthlyExercisesButton.addMouseListener(this);
        monthlyExercisesButton.setFont(PREV_EXERCISE_FONT);

        titlePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        titlePanel.setSize(SIGNUP_PANEL_SIZE);
        titlePanel.add(titleLabel);
        titlePanel.add(informationLabel);
        exercisePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        exercisePanel.setSize(SIGNUP_PANEL_SIZE);
        exercisePanel.add(exerciseLabel);
        exercisePanel.add(exerciseComboBox);
        exercisePanel.add(exerciseDuration);
        exercisePanel.add(durationTextField);
        exercisePanel.add(exerciseDate);
        exercisePanel.add(dateTextField);
        bottomPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        bottomPanel.add(submitExerciseButton);
        bottomPanel.add(mainMenuButton);
        bottomPanel.add(previousExercisesButton);
        bottomPanel.add(monthlyExercisesButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_SIZE);
        this.setLayout(new FlowLayout());
        this.add(titlePanel);
        this.add(exercisePanel);
        this.add(bottomPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitExerciseButton) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String enteredDate = dateTextField.getText();
                formatter.parse(enteredDate);
                writeToExerciseFile(username, String.valueOf(exerciseComboBox.getSelectedItem()), Integer.parseInt(durationTextField.getText()), enteredDate);
                submitExerciseButton.setVisible(false);
                submitExerciseButton.setEnabled(false);
            }
            catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Entered date must be valid.",
                        "Invalid Date", JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Number of minutes spent exercising must be numeric.",
                        "Invalid Exercise duration", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == mainMenuButton) {
            this.dispose();
            new MainMenu(username);
        }
        if (e.getSource() == previousExercisesButton) {
            this.dispose();
            new PrevExercises(username);
        }
        if (e.getSource() == monthlyExercisesButton) {
            this.dispose();
            new MonthlyExerciseReport(username);
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
        if (e.getSource() == submitExerciseButton)
            submitExerciseButton.setBackground(new Color(0x04AF70));
        if (e.getSource() == mainMenuButton)
            mainMenuButton.setBackground(new Color(0x04AF70));
        if (e.getSource() == monthlyExercisesButton)
            monthlyExercisesButton.setBackground(new Color(0x04AF70));
        if (e.getSource() == previousExercisesButton)
            previousExercisesButton.setBackground(new Color(0x04AF70));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == submitExerciseButton)
            submitExerciseButton.setBackground(new ColorUIResource(238,238,238));
        if (e.getSource() == mainMenuButton)
            mainMenuButton.setBackground(new ColorUIResource(238,238,238));
        if (e.getSource() == monthlyExercisesButton)
            monthlyExercisesButton.setBackground(new ColorUIResource(238,238,238));
        if (e.getSource() == previousExercisesButton)
            previousExercisesButton.setBackground(new ColorUIResource(238,238,238));
    }
}
