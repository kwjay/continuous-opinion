import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class ActionPanel extends JPanel{

    public JButton resetButton = new JButton("RESET");
    public JButton startButton = new JButton("START");
    public JSlider thresholdSlider;
    public JSlider convergenceSlider;
    public ActionPanel(){
        setLayout(new GridLayout(4, 1));
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(startButton);
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(resetButton);
        JPanel sliderPanel1 = new JPanel(new GridLayout(2,1));
        JLabel sliderLabel1 = new JLabel("THRESHOLD");
        sliderLabel1.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel sliderPanel2 = new JPanel(new GridLayout(2,1));
        JLabel sliderLabel2 = new JLabel("CONVERGENCE");
        sliderLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        thresholdSlider = newThresholdSlider();
        convergenceSlider = newConvergenceSlider();
        sliderPanel1.add(sliderLabel1);
        sliderPanel1.add(thresholdSlider);
        sliderPanel2.add(sliderLabel2);
        sliderPanel2.add(convergenceSlider);
        add(sliderPanel1);
        add(sliderPanel2);
    }



    private JSlider newThresholdSlider() {
        JSlider slider = new JSlider(0, 100, 30);
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(100, new JLabel("1.0"));
        labelTable.put(75, new JLabel("0.75"));
        labelTable.put(50, new JLabel("0.50"));
        labelTable.put(25, new JLabel("0.25"));
        labelTable.put(0, new JLabel("0.0"));
        slider.setLabelTable( labelTable );
        slider.setPaintLabels(true);
        return slider;
    }

    private JSlider newConvergenceSlider(){
        JSlider slider = new JSlider(0, 50, 25);
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(50, new JLabel("0.50"));
        labelTable.put(25, new JLabel("0.25"));
        labelTable.put(0, new JLabel("0.0"));
        slider.setLabelTable( labelTable );
        slider.setPaintLabels(true);
        return slider;
    }
}
