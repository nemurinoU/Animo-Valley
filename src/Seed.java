//package src;
public class Seed {
    private String name;
    private int amount = 0;
    private double cost;
    private int id; //how do we do the identification?
    
    public Seed(String name, int amount, double cost, int id) {
        this.name = name;
        this.amount = amount;
        this.cost = cost;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
