package BMI;

import MainPage.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Constants.Constants.Fonts.BMI_FONT;
import static Constants.Constants.Fonts.TITLE_FONT;
import static Constants.Constants.FrameSizes.LOGIN_PANEL_SIZE;
import static Constants.Constants.FrameSizes.LOGIN_SIZE;

public class BMIPage extends JFrame implements ActionListener {

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
        heightField = new JTextField("Height");
        heightField.setFont(BMI_FONT);
        weightLabel = new JLabel("Enter your weight in kilograms: ");
        weightLabel.setFont(BMI_FONT);
        weightField = new JTextField("Weight");
        weightField.setFont(BMI_FONT);
        submitBMI = new JButton("Calculate BMI");
        submitBMI.setFont(BMI_FONT);
        viewPreviousBMI = new JButton("View Previous BMIs");
        viewPreviousBMI.setFont(BMI_FONT);
        BMIValue = new JLabel();
        BMIValue.setFont(BMI_FONT);
        BMIValue.setVisible(false);
        returnButton = new JButton("Return to Main Menu");

        submitBMI.addActionListener(this);
        viewPreviousBMI.addActionListener(this);
        returnButton.addActionListener(this);

        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setSize(LOGIN_PANEL_SIZE);
        heightWeightPanel = new JPanel();
        heightWeightPanel.setSize(LOGIN_PANEL_SIZE);
        submitPanel = new JPanel(new BorderLayout());
        submitPanel.setSize(LOGIN_PANEL_SIZE);

        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.add(informationLabel, BorderLayout.SOUTH);
        heightWeightPanel.add(heightLabel);
        heightWeightPanel.add(heightField);
        heightWeightPanel.add(weightLabel);
        heightWeightPanel.add(weightField);
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
            submitBMI.setVisible(false);
            submitBMI.setEnabled(false);
            double BMI = Double.parseDouble(weightField.getText()) / Math.pow(Double.parseDouble(heightField.getText())/100, 2);
            BMIValue.setText("BMI: " + Math.round(BMI*100.0)/100.0);
            BMIValue.setVisible(true);
        }
        if (e.getSource() == viewPreviousBMI) {

        }
        if (e.getSource() == returnButton) {
            this.dispose();
            new MainMenu(username);
        }
    }
}
