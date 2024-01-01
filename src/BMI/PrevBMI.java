package BMI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Constants.Constants.FrameSizes.DEFAULT_SIZE;
import static Files.FileFunctions.readFromBMIFile;

public class PrevBMI extends JFrame implements ActionListener {

    String username;
    JPanel titlePanel;
    JPanel oldBMIs;

    public PrevBMI(String username) {
        this.username = username;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_SIZE);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        ArrayList<ArrayList<String>> BMIs = readFromBMIFile(username);

        for (ArrayList<String> row : BMIs) {
            for (String column : row) {
                System.out.print(column + ", ");
            }
            System.out.println();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
