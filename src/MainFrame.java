import java .awt.*;
import javax.swing.*;

public class MainFrame {
    static float threshold = 0.3f;
    static float convergence = 0.3f;
    static boolean started = false;
    static Thread simThread;
    static SimFrame simFrame;
    static GridBagConstraints simConstraints = new GridBagConstraints();
    static Dimension windowDimension = new Dimension(700, 450);
    static Color defaultColor;
    public static void main(String[] s) {
        JFrame frame = new JFrame("Continuous Opinion Simulation");
        frame.setLayout(new GridBagLayout());
        simConstraints.fill = GridBagConstraints.NONE;
        simConstraints.weightx = 1;
        simConstraints.weighty = 1;
        simConstraints.gridheight = 1;
        simConstraints.gridwidth = 1;
        simConstraints.gridx = 0;
        simConstraints.gridy = 0;
        simFrame = new SimFrame(threshold, convergence);
        frame.add(simFrame, simConstraints);
        ActionPanel actionPanel = new ActionPanel();
        GridBagConstraints frameConstraints = new GridBagConstraints();
        frameConstraints.fill = GridBagConstraints.HORIZONTAL;
        frameConstraints.anchor = GridBagConstraints.CENTER;
        frameConstraints.gridx = 4;
        frameConstraints.gridy = 0;
        frameConstraints.gridheight = 0;
        frameConstraints.gridwidth = 0;
        frame.add(actionPanel, frameConstraints);
        frame.setMinimumSize(windowDimension);
        frame.setSize(windowDimension);
        frame.setMinimumSize(windowDimension);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        defaultColor = actionPanel.startButton.getBackground();

        actionPanel.startButton.addActionListener(e -> {
            if(!started) {
                actionPanel.startButton.setText("STOP");
                actionPanel.startButton.setBackground(Color.RED);
                simThread = new Thread(simFrame);
                simFrame.running = true;
                simThread.start();
                started = true;
            } else {
                actionPanel.startButton.setText("PLAY");
                actionPanel.startButton.setBackground(Color.GREEN);
                simFrame.running = false;
                simThread = new Thread(simFrame);
                started = false;
            }
            actionPanel.convergenceSlider.setEnabled(false);
            actionPanel.thresholdSlider.setEnabled(false);
        });
        actionPanel.resetButton.addActionListener(e -> {
            if(simFrame.running) {
                simFrame.running = false;
            }
            actionPanel.startButton.setText("START");
            actionPanel.startButton.setBackground(defaultColor);
            frame.remove(simFrame);
            simFrame = new SimFrame(threshold, convergence);
            frame.add(simFrame, simConstraints);
            frame.revalidate();
            frame.repaint();
            started = false;
            actionPanel.convergenceSlider.setEnabled(true);
            actionPanel.thresholdSlider.setEnabled(true);
        });
        actionPanel.thresholdSlider.addChangeListener(e -> {
            threshold = (float) actionPanel.thresholdSlider.getValue() / 100;
            if (!started) {
                simFrame.setNewThreshold(threshold);
            }
        });
        actionPanel.convergenceSlider.addChangeListener(e -> {
            convergence = (float) actionPanel.convergenceSlider.getValue() / 100;
            if(!started) {
                simFrame.setNewConvergence(convergence);
            }
        });
    }

}

