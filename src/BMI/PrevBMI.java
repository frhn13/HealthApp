package BMI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Constants.Constants.FrameSizes.DEFAULT_SIZE;

public class PrevBMI extends JFrame implements ActionListener {

    String username;

    public PrevBMI(String username) {

        this.username = username;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_SIZE);
        this.setLayout(new ScrollPaneLayout());
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
