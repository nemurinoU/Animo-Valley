import java.util.ArrayList;
public class Farmer {
	private String name;
	private double xp = 0, coins = 100;
	private int lvl = 0;
	private ArrayList<Item> items;

	public Farmer(String name, double xp, double coins, int lvl, ArrayList<Item> items) {
		this.name = name;
		this.xp = xp;
		this.coins = coins;
		this.lvl = lvl;
		this.items = new ArrayList<Item>();
	}

	public Farmer(String name){
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getXp() {
		return this.xp;
	}

	public void setXp(double xp) {
		this.xp = xp;
	}

	public double getCoins() {
		return this.coins;
	}

	public void setCoins(double coins) {
		this.coins = coins;
	}

	public int getLvl() {
		return this.lvl;
	}

	public void updateLvl() {
		this.lvl = (int)this.xp / 100;
	}

	public ArrayList<Item> getItems() {
		return this.items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
}
