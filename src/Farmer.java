//package src;

//import java.util.ArrayList;
import java.util.Random;

public class Farmer {
	private String name, farmerTitle;
	private double xp, coins;
	private int lvl,
				farmerType;

	public Farmer(String name, double xp, double coins, int lvl) {
		this.name = name;
		this.xp = xp;
		this.coins = coins;
		this.lvl = lvl;
	}

	public Farmer(String name){
		this.name = name;
		this.coins = 100.0;
		this.xp = 0.0;
		this.farmerType = 0;
		this.farmerTitle = "<Farmer>";
		this.lvl = 0;
	}
	
	
	public void digOut (PlotLand plot) {
		plot.setIsOccupied (false);
		plot.setIsPlowed ((false));
		plot.delCrop ();
		
		// digging costs 7 coins
		this.setCoins(this.getCoins() - 7);
		
		// farmer earns 2xp from shoveling
		this.setXp (this.getXp() + 2);
		System.out.println("~~~ Shovel used! ~~~\n");
	}
	
	public void mineRock (PlotLand plot) {
		plot.setIsOccupied (false);
		plot.setHasRock (false);
		
		// using the pickaxe costs 50 coins
		this.setCoins (this.getCoins() - 50);
		
		// farmer earns 15 xp from pickaxe
		this.setXp (this.getXp() + 15);
	}
	
	public void plowLand (PlotLand plot) {
		plot.setIsPlowed (true);
		plot.setIsOccupied (false);
		
		// farmer earns 0.5 xp from plowing
		this.setXp(this.getXp () + 0.5);
		System.out.println("~~~ Plot has been plowed! ~~~\n");
	}
	
	public void fertilizePlant (PlotLand plot) {
		// increment the amt of times fertilized a crop
		// inside a plot
		plot.getCrop ().fertilizeSelf ();		
		
		// farmer earns 4 xp from fertilizing
		this.setXp(this.getXp () + 4);
		
		// farmer loses 10 coins per fertilize
		this.setCoins (this.getCoins () - 10);
		System.out.println("~~~ Fertilizer used! ~~~\n");
	}
	
	public void waterPlant (PlotLand plot) {
		// increment the amount of times watered of the crop
		// inside the plot
		plot.getCrop ().waterSelf ();
		
		// farmer earns 0.5 xp from watering
		this.setXp(this.getXp () + 0.5);
		System.out.println("~~~ Watering can used! ~~~\n");
	}
	
	public void harvestCrop (PlotLand plot) {
		Random random = new Random ();
		int nYield, min, max;
		double earnings;
		
		min = plot.getCrop().getMinProduce();
		max = plot.getCrop().getMaxProduce();
		
		plot.setIsOccupied (false);
		plot.setIsPlowed ((false));
		
		// randomize harvest yield
		nYield = random.nextInt (max - min + 1) + min;
		System.out.println ("You harvested " + nYield + " " + plot.getCrop().getCropName() + "!");
		
		// earn xp from harvesting
		this.setXp (this.getXp() + plot.getCrop().getXpYield() * nYield);
		
		// earn coins from this harvest
		earnings = calculateFinalHarvestPrice (plot, nYield, plot.getCrop().getBasePrice(), 
												this.getFTBEarning( this.getFarmerType()));
		
		System.out.println ("You earned " + earnings + " coins!");
		this.setCoins (this.getCoins () + earnings);
		
		plot.delCrop ();
	}
	
	public double calculateFinalHarvestPrice (PlotLand plot, int yield, double basePrice, int farmerTypeBonus) {
		double  HarvestTotal,
				WaterBonus,
				FertilizerBonus,
				FinalHarvestPrice;
		
		// DEV NOTES: We will implement no BONUSES for Fruit Tree types
		//harvest total = products produced x (basesellingpriceperpiece + farmertypeearningbonus)
		HarvestTotal = yield * (basePrice + farmerTypeBonus);
		
		//water bonus = harvesttotal * 0.2 * (timescropwaswatered - 1)
		WaterBonus = HarvestTotal * 0.2 * (plot.getCrop().getTimesWatered () - 1);
		
		//fertilizer bonus = harvesttotal * 0.5 * timescropwasfertilized
		FertilizerBonus = HarvestTotal * 0.5 * plot.getCrop().getTimesFertilized();
		
		FinalHarvestPrice = HarvestTotal + WaterBonus + FertilizerBonus;
		
		return FinalHarvestPrice; 
	}
	
	public void plantCrop (PlotLand plot, Crop seedling, int currentDay) {
		//activate the crop so that it's not a dictionary type anymore
		seedling.activateCrop (currentDay);
		//subtract seed cost from wallet
		this.setCoins (this.getCoins() - seedling.getSeedCost());
		
		//put seedling inside plot, occupied is now true
		plot.setCrop((seedling));
		plot.setIsOccupied (true);
	}
	
	public void updateLvl() {
		this.lvl = (int)this.xp / 100;
	}

	
	public int getFTBEarning (int farmerType) {
		int bonus = 0;
		
		switch (farmerType) {

		case 1: //registered farmer
			bonus = 1;
			break;
		case 2: //distinguished farmer
			bonus = 2;
			break;
		case 3: //legendary farmer
			bonus = 4;
			break;
		
		default:
			break;
		}
		
		return bonus;
	}
	
	public int getFarmerType () {
		return this.farmerType;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setXp(double xp) {
		this.xp = xp;
		this.updateLvl();
	}
	
	public void setCoins(double coins) {
		// there can be no negative currency
		if (coins >= 0)
			this.coins = coins;
		else
			this.coins = 0;
	}

	public void setFarmerType(int farmerType){
		this.farmerType = farmerType;
	}
	public String getFarmerTitle(){
		return this.farmerTitle;
	}
	
	public void setFarmerTitle(int farmerType){
		switch (farmerType){
			case 0:
				this.farmerTitle = "<Farmer>";
				break;
			case 1:
				this.farmerTitle = "<Registered Farmer>";
				break;
			case 2:
				this.farmerTitle = "<Distinguished Farmer>";
				break;
			case 3:
				this.farmerTitle = "<Legendary Farmer>";
				break;
			default:
				break;
		}
	}

	public String getName() {
		return this.name;
	}

	public double getXp() {
		return this.xp;
	}

	
	public double getCoins() {
		return this.coins;
	}
	

	public int getLvl() {
		return this.lvl;
	}
	
	
}
