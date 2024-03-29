package BMI;

import MainPage.MainMenu;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static Constants.Constants.Fonts.BMI_FONT;
import static Constants.Constants.Fonts.TITLE_FONT;
import static Constants.Constants.FrameSizes.LOGIN_PANEL_SIZE;
import static Constants.Constants.FrameSizes.LOGIN_SIZE;
import static Files.FileFunctions.writeToBMIFile;

public class BMIPage extends JFrame implements ActionListener, MouseListener {

    String username;
    JLabel title;
    JLabel informationLabel;
    JLabel heightLabel;
    JTextField heightField;
    JLabel weightLabel;
    JTextField weightField;
    JButton submitBMI;
    JButton viewPreviousBMI;
    JLabel BMIValue;
    JButton returnButton;
    JLabel dateLabel;
    JTextField dateField;

    JPanel titlePanel;
    JPanel heightWeightPanel;
    JPanel submitPanel;

    public BMIPage(String username) {
        title = new JLabel(username + "'s BMI Page");
        title.setFont(TITLE_FONT);
        informationLabel = new JLabel("Here you can enter your height and weight to get your BMI");
        informationLabel.setFont(BMI_FONT);
        heightLabel = new JLabel("Enter your height in centimetres: ");
        heightLabel.setFont(BMI_FONT);
        heightField = new JTextField();
        heightField.setFont(BMI_FONT);
        weightLabel = new JLabel("Enter your weight in kilograms: ");
        weightLabel.setFont(BMI_FONT);
        weightField = new JTextField();
        weightField.setFont(BMI_FONT);
        dateLabel = new JLabel("Enter the date of the BMI: ");
        dateLabel.setFont(BMI_FONT);
        dateField = new JTextField();
        dateField.setFont(BMI_FONT);
        submitBMI = new JButton("Calculate BMI");
        submitBMI.setFont(BMI_FONT);
        viewPreviousBMI = new JButton("View Previous BMIs");
        viewPreviousBMI.setFont(BMI_FONT);
        BMIValue = new JLabel();
        BMIValue.setFont(BMI_FONT);
        BMIValue.setVisible(false);
        returnButton = new JButton("Return to Main Menu");
        returnButton.setFont(BMI_FONT);

        submitBMI.addActionListener(this);
        viewPreviousBMI.addActionListener(this);
        returnButton.addActionListener(this);
        submitBMI.addMouseListener(this);
        viewPreviousBMI.addMouseListener(this);
        returnButton.addMouseListener(this);

        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setSize(LOGIN_PANEL_SIZE);
        heightWeightPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        heightWeightPanel.setSize(LOGIN_PANEL_SIZE);
        submitPanel = new JPanel(new BorderLayout());
        submitPanel.setSize(LOGIN_PANEL_SIZE);

        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.add(informationLabel, BorderLayout.SOUTH);
        heightWeightPanel.add(heightLabel);
        heightWeightPanel.add(heightField);
        heightWeightPanel.add(weightLabel);
        heightWeightPanel.add(weightField);
        heightWeightPanel.add(dateLabel);
        heightWeightPanel.add(dateField);
        submitPanel.add(BMIValue, BorderLayout.EAST);
        submitPanel.add(submitBMI, BorderLayout.WEST);
        submitPanel.add(viewPreviousBMI, BorderLayout.NORTH);
        submitPanel.add(returnButton, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(LOGIN_SIZE);
        this.setLayout(new BorderLayout());
        this.username = username;
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(heightWeightPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBMI) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String enteredDate = dateField.getText();
                formatter.parse(enteredDate);
                double BMI = Math.round((Double.parseDouble(weightField.getText()) / Math.pow(Double.parseDouble(heightField.getText()) / 100, 2)*100)/100);
                if (BMI < 18.5) {
                    BMIValue.setForeground(new Color(189, 0, 0, 255));
                    BMIValue.setText("BMI: " + BMI + ", This indicates you are underweight");
                }
                else if (BMI <= 25) {
                    BMIValue.setForeground(new Color(45, 224, 45, 255));
                    BMIValue.setText("BMI: " + BMI + ", This indicates you have a healthy weight");
                }
                else if (BMI <= 30) {
                    BMIValue.setForeground(new Color(204,204,0));
                    BMIValue.setText("BMI: " + BMI + ", This indicates you are overweight");
                }
                else {
                    BMIValue.setForeground(new Color(189, 0, 0, 255));
                    BMIValue.setText("BMI: " + BMI + ", This indicates that you are obese");
                }
                submitBMI.setVisible(false);
                submitBMI.setEnabled(false);
                BMIValue.setVisible(true);
                writeToBMIFile(username, heightField.getText(), weightField.getText(), String.valueOf(BMI), enteredDate);
            }
            catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Entered date must be valid.",
                        "Invalid Date", JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Height and weight entered must be numeric.",
                        "Invalid BMI values", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == viewPreviousBMI) {
            this.dispose();
            new PrevBMI(username);
        }
        if (e.getSource() == returnButton) {
            this.dispose();
            new MainMenu(username);
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
        if (e.getSource() == submitBMI)
            submitBMI.setBackground(new Color(0x04AF70));
        if (e.getSource() == viewPreviousBMI)
            viewPreviousBMI.setBackground(new Color(0x04AF70));
        if (e.getSource() == returnButton)
            returnButton.setBackground(new Color(0x04AF70));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == submitBMI)
            submitBMI.setBackground(new ColorUIResource(238,238,238));
        if (e.getSource() == viewPreviousBMI)
            viewPreviousBMI.setBackground(new ColorUIResource(238,238,238));
        if (e.getSource() == returnButton)
            returnButton.setBackground(new ColorUIResource(238,238,238));
    }
}
