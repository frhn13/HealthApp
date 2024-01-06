package ExerciseTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Constants.Constants.FrameSizes.DEFAULT_SIZE;

public class PrevExercises extends JFrame implements ActionListener {

    String username;

    public PrevExercises(String username) {
        this.username = username;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_SIZE);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
