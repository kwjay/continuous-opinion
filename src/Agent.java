public class Agent {
    private float opinion;
    private final float threshold;
    private final float convergence;

    public Agent(float opinion, float threshold, float convergence) {
        this.opinion = opinion;
        this.threshold = threshold;
        this.convergence = convergence;
    }
    public void agentMet(float agents_opinion) {
        if (Math.abs(this.opinion - agents_opinion) < threshold) {
            opinionAdjust(agents_opinion);
        }
    }
    public float getOpinion() {
        return this.opinion;
    }
    private void opinionAdjust(float agents_opinion) {
        this.opinion = this.opinion + this.convergence * (agents_opinion - this.opinion);
    }
}
