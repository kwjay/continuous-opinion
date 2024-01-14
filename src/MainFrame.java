import java .awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame {
    static float threshold = 0.3f;
    static float convergence = 0.3f;
    public static void main(String[] s) {
        JFrame frame = new JFrame("Continuous Opinion Simulation");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints frameConstraints = new GridBagConstraints();
        SimFrame simFrame = new SimFrame(threshold, convergence);
        Thread simulationThread = new Thread(simFrame);
        frameConstraints.fill = GridBagConstraints.BOTH;
        frameConstraints.weightx = 1;
        frameConstraints.weighty = 1;
        frameConstraints.gridheight = 3;
        frameConstraints.gridwidth = 3;
        frame.add(simFrame, frameConstraints);
        JButton startButton = new JButton("START");
        startButton.addActionListener(e -> simulationThread.start());
        startButton.setFont(new Font("Arial", Font.BOLD, 25));
        frameConstraints.fill = GridBagConstraints.BOTH;
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 3;
        frameConstraints.gridheight = 1;
        frameConstraints.gridwidth = 1;
        frame.add(startButton, frameConstraints);
        JButton resetButton = new JButton("RESET");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        resetButton.setFont(new Font("Arial", Font.BOLD, 25));
        frameConstraints.fill = GridBagConstraints.BOTH;
        frameConstraints.gridx = 1;
        frameConstraints.gridy = 3;
        frameConstraints.gridheight = 1;
        frameConstraints.gridwidth = 1;
        frame.add(resetButton, frameConstraints);
        JSlider thresholdSlider = new JSlider();
        frameConstraints.fill = GridBagConstraints.NONE;
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 4;
        frameConstraints.gridheight = 4;
        frameConstraints.gridwidth = 4;
        frame.add(thresholdSlider, frameConstraints);
        frame.setSize(550, 550);
        frame.setMinimumSize(new Dimension(550, 550));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

