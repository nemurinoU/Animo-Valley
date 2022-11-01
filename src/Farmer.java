
public class Farmer {
	private String name;
	private double xp, coins;
	private int lvl;
	 
	public Farmer (String name) {
		this.xp = 0;
		this.coins = 100;
		this.lvl = 0;
		this.name = name;
	}
	
	public double getXP () {
		return this.xp;
	}
	
	public double getCoins () {
		return this.coins;
	}
	
	public int getLvl () {
		return this.lvl;
	}
	
	public String getName () {
		return this.name;
	}
}
