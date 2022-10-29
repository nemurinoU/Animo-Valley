public class Tool {
    private String name;
    private int cost;
    private double xp;

    public Tool(String name, int cost, double xp) {
        this.name = name;
        this.cost = cost;
        this.xp = xp;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getXp() {
        return this.xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

}
