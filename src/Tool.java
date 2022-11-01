public class Tool { //ok we might not need this na? i mean we can just setXP and coins-- kasi from main
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

    public int getCost() {
        return this.cost;
    }

    public double getXp() {
        return this.xp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public void setXp(double xp) {
        this.xp = xp;
    }

}
