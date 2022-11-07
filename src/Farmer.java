import java.util.Random;

/***
 * <h1>Farmer</h1>
 * <p>
 * This class emulates the player, its attributes, and responsibilities.
 * It interacts with classes such as MyFarm, Crop, PlotLand.
 * It describes the player such as their stats and actions.
 *
 * </p>
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-07 
 */
public class Farmer {
	/**
	* Private Variable Instantiation
	* > name is the name of the farmer/player
	* > farmerTitle is the title of the player (i.e., farmer, registered, distinguished...)
	* > xp is the xp amount in double
	* > coins is the wallet
	* > lvl is current player level, dependent on the xp attributes
	* > farmerType is connected to farmerTitle, except this time in a integer code
	*/
	private String name, farmerTitle;
	private double xp, coins;
	private int lvl,
				farmerType;

	/* CONSTRUCTOR */
	/***
	 * This constructors makes a new Farmer/player object. It starts off
	 * with the default values for a new player.
	 *
	 * @param name		what the player should be called
	 *
	 */
	public Farmer(String name){
		this.name = name;
		this.coins = 100.0;
		this.xp = 0.0;
		this.farmerType = 0;
		this.farmerTitle = "<Farmer>";
		this.lvl = 0;
	}
	
	/* FARMER ACTIONS */
	/**
    * This method is what the player does when shoveling up a tile.
    * 
    * @param plot		the tile in question to be interacted with
    */
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
	
	/**
    * This method is what the player does when mining a rock on a tile.
    * 
    * @param plot		the tile in question to be interacted with
    */
	public void mineRock (PlotLand plot) {
		plot.setIsOccupied (false);
		plot.setHasRock (false);
		
		// using the pickaxe costs 50 coins
		this.setCoins (this.getCoins() - 50);
		
		// farmer earns 15 xp from pickaxe
		this.setXp (this.getXp() + 15);
	}
	
	/**
    * This method is what the player does when plowing a tile.
    * 
    * @param plot		the tile in question to be interacted with
    */
	public void plowLand (PlotLand plot) {
		plot.setIsPlowed (true);
		plot.setIsOccupied (false);
		
		// farmer earns 0.5 xp from plowing
		this.setXp(this.getXp () + 0.5);
		System.out.println("~~~ Plot has been plowed! ~~~\n");
	}
	
	/**
    * This method is what the player does when fertilizing a plant on a tile.
    * 
    * @param plot		the tile in question to be interacted with
    */
	public void fertilizePlant (PlotLand plot) {
		// increment the amt of times fertilized a crop
		// inside a plot
		plot.getCrop ().fertilizeSelf ( this.getFertilizerBonusLimitIncrease ( this.getFarmerType ()));		
		
		// farmer earns 4 xp from fertilizing
		this.setXp(this.getXp () + 4);
		
		// farmer loses 10 coins per fertilize
		this.setCoins (this.getCoins () - 10);
		System.out.println("~~~ Fertilizer used! ~~~\n");
	}
	
	/**
    * This method is what the player does when watering a crop on a tile.
    * 
    * @param plot		the tile in question to be interacted with
    */
	public void waterPlant (PlotLand plot) {
		// increment the amount of times watered of the crop
		// inside the plot
		plot.getCrop ().waterSelf ( this.getWaterBonusLimitIncrease ( this.getFarmerType ()));
		
		// farmer earns 0.5 xp from watering
		this.setXp(this.getXp () + 0.5);
		System.out.println("~~~ Watering can used! ~~~\n");
	}
	
	/**
    * This method is what the player does when planting a NEW crop on a tile.
    * 
    * @param plot		the tile in question to be interacted with
	* @param seedling	the Crop object to be put inside plot
	* @param currentDay	what the current game day is for growing reference
    */
	public void plantCrop (PlotLand plot, Crop seedling, int currentDay) {
		//activate the crop so that it's not a dictionary type anymore
		seedling.activateCrop (currentDay);

		//subtract seed cost from wallet w/ seed cost reduction
		this.setCoins (this.getCoins() - (seedling.getSeedCost () - this.getSeedCostReduction ( this.getFarmerType ()) ) );
		
		//put seedling inside plot, occupied is now true
		plot.setCrop((seedling));
		plot.setIsOccupied (true);
	}
	
	/**
    * This method is what the player does when harvesting a crop inside a tile. This includes
	* the calculations in income from harvesting a crop.
    * 
    * @param plot		the tile in question to be interacted with
    */
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
		
		//show earnings from harvest
		System.out.println ("You earned " + earnings + " coins!");
		this.setCoins (this.getCoins () + earnings);
		
		//remove crop from plot land
		plot.delCrop ();
	}
	
	/**
    * This method is a subroutine to calculate the final income of a player when harvesting a single crop.
    * 
    * @param plot				the tile in question to be interacted with
	* @param yield				the pieces of plant received in harvesting
	* @param basePrice			the price per piece of plant
	* @param farmerTypeBonus	extra cash from being a registered farmer
	*
	* @return double			the final calculated income for one crop harvest
    */
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
	
	/**
    * This method updates the current lvl of a player with respect to XP.
	*
    */
	public void updateLvl() {
		this.lvl = (int)this.xp / 100;
	}
	
	/**
    * This method returns the amount of coinage reduced from being a certain farmer type.
	*
	* @param farmerType		the code of farmer you are
	* 
	* @return int			the amount deducted from a seed cost
    */
	public int getSeedCostReduction (int farmerType) {
		return farmerType;
	}

	/**
    * This method returns the bonus water limit increase depending on the farmer type you are
	*
	* @param farmerType		the code of farmer you are
	* 
	* @return int			the amount increased from the water limit of a crop
    */
	public int getWaterBonusLimitIncrease (int farmerType) {
			int rCode = 0;

			switch (farmerType) {
			case 0:
			case 1:
					break;
			case 2:
					rCode = 1;
					break;
			case 3:
					rCode = 2;
					break;
			default:
					break;
			}

			return rCode;
	}

	/**
    * This method returns the bonus fertilizer limit increase depending on the farmer type you are
	*
	* @param farmerType		the code of farmer you are
	* 
	* @return int			the amount increased from the fertilizer limit of a crop
    */
	public int getFertilizerBonusLimitIncrease (int farmerType) {
			int rCode = 0;

			if (farmerType == 3)
					rCode = 1;

			return rCode;
	}

	

	/* GETTERS */
	/**
    * This method gets the player name
    * 
    * @return String		player name
    */
	public String getName() {
		return this.name;
	}

	/**
    * This method gets the player xp in total
    * 
    * @return double		total player xp
    */
	public double getXp() {
		return this.xp;
	}
	
	/**
    * This method gets the amount in the player's wallet
    * 
    * @return double		player coin balance
    */
	public double getCoins() {
		return this.coins;
	}
	
	/**
    * This method gets the player level
    * 
    * @return int		player level
    */
	public int getLvl() {
		return this.lvl;
	}
	
	/**
    * This method gets the farmer type code
    * 
    * @return int		farmer type code
    */
	public int getFarmerType () {
		return this.farmerType;
	}

	/**
    * This method gets the bonus earnings you get from being a certain farmer type
    * @param farmerType farmer type code of player
	*
    * @return int		bonus earnings
    */
	public int getFTBEarning (int farmerType) {
		int bonus = 0; //bonus earnings
		
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
	
	/**
    * This method gets the farmer title of the player in words
    * 
    * @return String	farmer title in words
    */
	public String getFarmerTitle(){
		return this.farmerTitle;
	}
	

	/* SETTERS */
	/**
    * This method sets the player name
    * @param name		name of the farmer
    */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
    * This method sets the player xp/updates lvl visually too
    * @param xp			XP of the player
    */
	public void setXp(double xp) {
		this.xp = xp;
		this.updateLvl();
	}
	
	/**
    * This method sets/updates the amount of coins a player has
    * @param coins		coins of the player
    */
	public void setCoins(double coins) {
		// there can be no negative currency
		if (coins >= 0)
			this.coins = coins;
		else
			this.coins = 0;
	}

	/**
    * This method sets the farmer type code
    * @param farmerType		farmer type code of player
    */
	public void setFarmerType(int farmerType){
		this.farmerType = farmerType;
	}
	
	/**
    * This method sets the farmer type of the player in words
    * @param farmerType 	farmer type code of player
    */
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
}
