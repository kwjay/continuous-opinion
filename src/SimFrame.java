import javax.swing.*;
import java.awt.*;

public class SimFrame extends JPanel implements Runnable{
    private final int lattice_size = 500;
    Agent[][] lattice = new Agent [lattice_size][lattice_size];

    public SimFrame(float threshold, float convergence) {
        setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        for (int x = 0; x < this.lattice_size; x++) {
            for (int y = 0; y < this.lattice_size; y++) {
                float opinion = (float)Math.random();
                this.lattice[x][y] = new Agent(opinion,threshold, convergence);
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

    public void run() {
        while (true) {
            int agentX = (int) (Math.random() * lattice_size - 1);
            int agentY = (int) (Math.random() * lattice_size - 1);
            int[] neighbourXY = findNeighbour(agentX, agentY);
            int neighbourX = neighbourXY[0];
            int neighbourY = neighbourXY[1];
            float agentOpinion = lattice[agentX][agentY].getOpinion();
            float neighbourOpinion = lattice[neighbourX][neighbourY].getOpinion();
            lattice[agentX][agentY].agentMet(neighbourOpinion);
            lattice[neighbourX][neighbourY].agentMet(agentOpinion);
            repaint();
        }
    }

    private int[] findNeighbour(int agentX, int agentY) {
        int[] neighbourXY = new int[2];
        while (true) {
            float random_value = (float)Math.random();
            if (random_value <= 0.25 && agentX > 0) {
                neighbourXY[0] = agentX - 1;
                neighbourXY[1] = agentY;
                break;
            } else if (random_value <= 0.5 && agentX < lattice_size) {
                neighbourXY[0] = agentX + 1;
                neighbourXY[1] = agentY;
                break;
            } else if (random_value <= 0.75 && agentY > 0) {
                neighbourXY[0] = agentX;
                neighbourXY[1] = agentY - 1;
                break;
            } else if (random_value <= 1.0 && agentY < lattice_size) {
                neighbourXY[0] = agentX;
                neighbourXY[1] = agentY + 1;
                break;
            }
        }
        return neighbourXY;
    }

    public void paintComponent(Graphics frameGraphics){
        super.paintComponent(frameGraphics);
        for (int x = 0; x < lattice_size - 1; x++) {
            for (int y = 0; y < lattice_size - 1; y++) {
                frameGraphics.setColor(calcColor(lattice[x][y]));
                int square_size = 5;
                frameGraphics.fillRect(x * square_size, y * square_size,
                        square_size, square_size);
            }
        }
    }

    private Color calcColor(Agent agent) {
        return new Color(1 * agent.getOpinion(), 1 * agent.getOpinion(), 1 * agent.getOpinion());
    }
}
