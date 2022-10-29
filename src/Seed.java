public class Seed {
    private String name;
    private int amount = 0;
    private int cost;
    private int id;
    
    public Seed(String name, int amount, int cost, int id) {
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

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}