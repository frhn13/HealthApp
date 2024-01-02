package BMI;

import MainPage.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Constants.Constants.Fonts.*;
import static Constants.Constants.FrameSizes.DEFAULT_SIZE;
import static Files.FileFunctions.readFromBMIFile;

public class PrevBMI extends JFrame implements ActionListener {

    String username;
    JPanel titlePanel;
    JPanel oldBMIs;
    JPanel buttonPanel;
    JScrollPane scrollPane;
    JLabel titleLabel;
    JLabel BMILabel;
    JButton setBMIButton;
    JButton mainMenuButton;

    public PrevBMI(String username) {
        ArrayList<ArrayList<String>> BMIs = readFromBMIFile(username);

        titlePanel = new JPanel();
        oldBMIs = new JPanel();
        oldBMIs.setLayout(new GridLayout(BMIs.size(), 1, 10, 10));
        buttonPanel = new JPanel(new BorderLayout());

        titleLabel = new JLabel(username +"'s Previous BMIs");
        titleLabel.setFont(TITLE_FONT);
        for (ArrayList<String> row : BMIs) {
            BMILabel = new JLabel("User: " + row.get(0) + ", Height: " + row.get(1) + ", Weight: " + row.get(2) + ", BMI: " + row.get(3) + ", Date: " + row.get(4));
            BMILabel.setFont(PREV_BMI_FONT);
            oldBMIs.add(BMILabel);
        }
        scrollPane = new JScrollPane(oldBMIs, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setBMIButton = new JButton("Calculate BMI");
        setBMIButton.setFont(BMI_FONT);
        mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setFont(BMI_FONT);
        setBMIButton.addActionListener(this);
        mainMenuButton.addActionListener(this);

        titlePanel.add(titleLabel);
        buttonPanel.add(setBMIButton, BorderLayout.WEST);
        buttonPanel.add(mainMenuButton, BorderLayout.EAST);

        this.username = username;
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
        if (e.getSource() == setBMIButton) {
            new BMIPage(username);
            this.dispose();
        }
        if (e.getSource() == mainMenuButton) {
            new MainMenu(username);
            this.dispose();
        }
    }
}
