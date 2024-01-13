import java .awt.*;
import javax.swing.*;

public class MainFrame {
    static float threshold = 0.3f;
    static float convergence = 0.3f;
    public static void main(String[] s) {
        JFrame frame = new JFrame("Continuous Opinion Simulation");
        SimFrame simFrame = new SimFrame(threshold, convergence);
        frame.add(simFrame);
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 400);
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        while (true) {
            simFrame.runSim();
        }

    }
}

