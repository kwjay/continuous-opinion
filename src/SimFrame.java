import javax.swing.*;
import java.awt.*;

public class SimFrame extends JPanel implements Runnable{
    public boolean running = false;
    private final int lattice_size = 250;

    Agent[][] lattice = new Agent [lattice_size][lattice_size];

    public SimFrame(float threshold, float convergence) {
        setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        for (int x = 0; x < this.lattice_size; x++) {
            for (int y = 0; y < this.lattice_size; y++) {
                float opinion = (float)Math.random();
                this.lattice[x][y] = new Agent(opinion,threshold, convergence);
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    public void run() {
        while (running) {
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
        return Color.getHSBColor(0, 0, 1 - agent.getOpinion());
    }

    public void setNewThreshold(float newThreshold) {
        for (int i = 0; i < lattice_size; i++) {
            for (int j = 0; j < lattice_size; j++) {
                lattice[i][j].changeThreshold(newThreshold);
            }
        }
    }
    public void setNewConvergence(float newConvergence) {
        for (int i = 0; i < lattice_size; i++) {
            for (int j = 0; j < lattice_size; j++) {
                lattice[i][j].changeConvergence(newConvergence);
            }
        }
    }
}
