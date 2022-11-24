package mco1;

/***
 * <h1>MyFarm</h1>
 * <p>
 * This class describes a single crop, including all the attributes needed for a Crop object.
 * It also checks whether or not the crop is harvestable or has withered.
 * It also indirectly updates the farmer's coins and XP
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-07 
 */
public class Crop {
    /**
     * Private Variable Instantiation
     * > cropName is the crop's name
     * > cropTypeID is the crop type's ID (0 - Root crop, 1 - Flower, 2 - Fruit tree)
     * > harvestTime is the time it takes until it is harvestable
     * > waterNeed is the amount of water needed for the crop to grow
     * > waterBonus is the bonus limit when adding water
     * > fertilizerNeed is the amount of fertilizer needed for the crop to grow
     * > fertilizerBonus is the bonus limit when adding fertilizer
     * > minProduce is the minimum number of crops produced when harvested
     * > maxProduce is the maximum number of crops produced when harvested
     * > basePrice is the base price of the crop when harvested
     * > xpYield is the XP yield of the crop when harvested
     * > cropID is the individual crop ID
     * > seedCost is the cost of the seed to plant on the plot land
     * > day planted is the day of when the crop was planted
     * > timesWatered is the amount of times the crop was watered
     * > timesFertilized is the amount of times the crop was fertilized
     * > isHarvestable is the state of the crop, whether it is harvestable or not
     * > isWithered is the state of the crop, whether it is withered or not
	 */
    private String cropName;
    private double basePrice;
    private double xpYield;
    
    
    private int cropID, 
    			cropTypeID,
    			harvestTime,
    			waterNeed,
    			waterBonus,
    			fertilizerNeed,
    			fertilizerBonus,
    			minProduce,
    			maxProduce,
    			seedCost;
    
    private int dayPlanted,
    			timesWatered,
    			timesFertilized;
    
    private boolean isHarvestable,
    				isWithered;
    			
    /**
     * This constructor is used to instantiate a Crop object when it is planted on the plot land.
     * @param cropName The crop's name
     * @param cropTypeID The crop type's ID (0 - Root crop, 1 - Flower, 2 - Fruit tree)
     * @param harvestTime The time it takes until it is harvestable
     * @param waterNeed The amount of water needed for the crop to grow
     * @param waterBonus The bonus limit when adding water
     * @param fertilizerNeed The amount of fertilizer needed for the crop to grow
     * @param fertilizerBonus The bonus limit when adding fertilizer
     * @param minProduce The minimum number of crops produced when harvested
     * @param maxProduce The maximum number of crops produced when harvested
     * @param basePrice The base price of the crop when harvested
     * @param xpYield The XP yield of the crop when harvested
     * @param cropID The individual crop ID
     * @param seedCost The cost of the seed to plant on the plot land
     */
    public Crop(String cropName, int cropTypeID, int harvestTime, 
    			int waterNeed, int waterBonus, int fertilizerNeed, 
    			int fertilizerBonus, int minProduce, int maxProduce, 
    			double basePrice, double xpYield, int cropID,
    			int seedCost) {
    	this.cropID = cropID;
        this.cropName = cropName;
        this.cropTypeID = cropTypeID;
        this.harvestTime = harvestTime;
        this.waterNeed = waterNeed;
        this.waterBonus = waterBonus;
        this.fertilizerNeed = fertilizerNeed;
        this.fertilizerBonus = fertilizerBonus;
        this.minProduce = minProduce;
        this.maxProduce = maxProduce;
        this.basePrice = basePrice;
        this.xpYield = xpYield;
        this.cropID = cropID;
        this.seedCost = seedCost;
    }

    
    /** 
     * This method is called when the user plants a seed, thus activating the crop with default values.
     * @param currentDay The current day of the game.
     */
    public void activateCrop (int currentDay) {
    	this.dayPlanted = currentDay;
    	this.isHarvestable = false;
    	this.isWithered = false;
    	this.timesWatered = 0;
    	this.timesFertilized = 0;
    }
    
    /** 
     * This method is called when the user uses fertilizer on the crop.
     * It is limited by the fertilizerBonus variable & the limit is increased when the farmer status is upgraded.
     * @param fertBonusLimitIncrease The value of the increase in the fertilizer bonus limit.
     */
    public void fertilizeSelf (int fertBonusLimitIncrease) {
    	if (this.getTimesFertilized() < this.getFertilizerBonus() + fertBonusLimitIncrease)
    		    this.timesFertilized++;
    }
    
    
    /** 
     * This method is called when the user waters the crop.
     * It is limited by the waterBonus variable & the limit is increased when the farmer status is upgraded.
     * @param waterBonusLimitIncrease The value of the increase in the water bonus limit.
     */

    public void waterSelf (int waterBonusLimitIncrease) {
    	if (this.getTimesWatered () < this.getWaterBonus () + waterBonusLimitIncrease)
    		    this.timesWatered++;
    }
    
    
    /** 
     * This is the method used in the growth of the plant.
     * It uses a variable daysElapsed, which is calculated by the currentDay - dayPlanted of the crop to determine the status of the
     * plant, whether or not it is ready for harvest, withered, or still growing.
     * @param currentDay The current day of the game.
     */
    public void grow (int currentDay) {
    	int daysElapsed;
    	
    	daysElapsed = currentDay - this.getDayPlanted();
    	
    	if (daysElapsed < this.getHarvestTime ()) //If the plant does not yet reach the harvestTime
    		System.out.println ("Plants growing...");
    	else if (daysElapsed == this.getHarvestTime ()) { //Else if the daysElapsed has finally reached the harevstTime
    		if (this.getTimesWatered () >= this.getWaterNeed () && this.getTimesFertilized () >= this.getFertilizerNeed()) //If it is watered enough and fertilizer enough
    			this.setIsHarvestable (true); //The crop becomes harevstable
    		else //Else if not enough water or fertilizer
    			this.setIsWithered (true); //The crop withers
    	}
    	else if (daysElapsed > this.getHarvestTime () && !this.getIsWithered()) { //Else if daysElapsed > harvestTime and plant is not yet withered
    		this.setIsHarvestable(false); //Not harvestable
    		this.setIsWithered (true); //Becomes withered
    	}
    		
    }
    
    // GETTERS

    /** 
     * This method gets the amount of times the crop is watered.
     * @return int      the times watered
     */
    public int getTimesWatered () {
    	return this.timesWatered;
    }
    
    
    /** 
     * This method gets the amount of times the crop is fertilized.
     * @return int      the times fertilized
     */
    public int getTimesFertilized () {
    	return this.timesFertilized;
    }
    
    
    /** 
     * This method gets the boolean state if the crop is harvestable or not.
     * @return boolean      if harvestable or not
     */
    public boolean getIsHarvestable () {
    	return this.isHarvestable;
    }
    
    
    /** 
     * This method gets the boolean state if the crop is withered or not.
     * @return boolean      if withered or not
     */
    public boolean getIsWithered () {
    	return this.isWithered;
    }
    
    
    /** 
     * This method gets the crop's name.
     * @return String       crop's name
     */
    public String getCropName() {
        return this.cropName;
    }
    
    
    /** 
     * This method gets the crop type's ID.
     * @return int      the crop type's IDs
     */
    public int getCropTypeID() {
        return this.cropTypeID;
    }

    
    /** 
     * This method gets the time it takes until the crop is harvestable.
     * @return int      harvest time of crop
     */
    public int getHarvestTime() {
        return this.harvestTime;
    }

    
    /** 
     * This method gets the amount of water needed for the crop.
     * @return int      water needed for crop
     */
    public int getWaterNeed() {
        return this.waterNeed;
    }

    
    /** 
     * This method gets the water bonus limit of the crop.
     * @return int      water bonus of crop
     */
    public int getWaterBonus() {
        return this.waterBonus;
    }

    
    /** 
     * This method gets the amount of fertilizer needed for the plant.
     * @return int      fertilizer needed for crop
     */
    public int getFertilizerNeed() {
        return this.fertilizerNeed;
    }

    
    /** 
     * This method gets the fertilizer bonus limit of the crop.
     * @return int      fertilizer bonus of crop
     */
    public int getFertilizerBonus() {
        return this.fertilizerBonus;
    }

    
    /** 
     * This method gets the minimum crops produced.
     * @return int      minimum number of cropss
     */
    public int getMinProduce() {
        return this.minProduce;
    }

    
    /** 
     * This method gets the maximum crops produced.
     * @return int      maximum number of crops
     */
    public int getMaxProduce() {
        return this.maxProduce;
    }

    
    /** 
     * This method gets the base price of the crop.
     * @return double       base price of crop
     */
    public double getBasePrice() {
        return this.basePrice;
    }

    
    /** 
     * This method gets the XP yield when the crop is harvested.
     * @return double       XP yield of crop
     */
    public double getXpYield() {
        return this.xpYield;
    }

    
    /** 
     * This method gets the individual crop ID.
     * @return int      crop's ID
     */
    public int getCropID() {
        return this.cropID;
    }
    
    
    /** 
     * This method gets the day it was planted.
     * @return int      day crop was planted
     */
    public int getDayPlanted () {
    	return this.dayPlanted;
    }
    
    
    /** 
     * This method gets the seed cost.
     * @return int      cost of seed
     */
    public int getSeedCost () {
    	return this.seedCost;
    }
    

    // SETTERS
    /** 
     * This method updates the crop type's ID.
     * @param cropTypeID        the crop type's IDs   
     */
    
    public void setCropTypeID(int cropTypeID) {
        this.cropTypeID = cropTypeID;
    }

    
    /** 
     * This method updates the time it takes until the crop is harvestable.
     * @param harvestTime       harvest time of crop
     */
    public void setHarvestTime(int harvestTime) {
        this.harvestTime = harvestTime;
    }

    
    /** 
     * This method updates the amount of water needed for the crop.
     * @param waterNeed         water needed for crop
     */
    public void setWaterNeed(int waterNeed) {
        this.waterNeed = waterNeed;
    }

    
    /** 
     * This method updates the water bonus limit of the crop.
     * @param waterBonus        water bonus of crop
     */
    public void setWaterBonus(int waterBonus) {
        this.waterBonus = waterBonus;
    }

    
    /** 
     * This method updates the amount of fertilizer needed for the plant.
     * @param fertilizerNeed    fertilizer needed for crop
     */
    public void setFertilizerNeed(int fertilizerNeed) {
        this.fertilizerNeed = fertilizerNeed;
    }

    
    /** 
     * This method updates the fertilizer bonus limit of the crop.
     * @param fertilizerBonus   fertilizer bonus of crop
     */
    public void setFertilizerBonus(int fertilizerBonus) {
        this.fertilizerBonus = fertilizerBonus;
    }

    
    /** 
     * This method updates the minimum crops produced.
     * @param minProduce        minimum number of cropss
     */
    public void setMinProduce(int minProduce) {
        this.minProduce = minProduce;
    }

    
    /** 
     * This method updates the maximum crops produced.
     * @param maxProduce        maximum number of crops
     */
    public void setMaxProduce(int maxProduce) {
        this.maxProduce = maxProduce;
    }

    
    /** 
     * This method updates the base price of the crop.
     * @param basePrice         base price of crop
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    
    /** 
     * This method updates the XP yield when the crop is harvested.
     * @param xpYield           XP yield of crop
     */
    public void setXpYield(double xpYield) {
        this.xpYield = xpYield;
    }

    
    /** 
     * This method updates the individual crop ID.
     * @param cropID            crop's ID
     */
    public void setCropID(int cropID) {
        this.cropID = cropID;
    }

    
    /** 
     * This method update the boolean state if the crop is harvestable or not.
     * @param isHarvestable     if harvestable or not
     */
    public void setIsHarvestable (boolean isHarvestable) {
        this.isHarvestable = isHarvestable;
    }

    
    /** 
     * This method updates the boolean state if the crop is withered or not.
     * @param isWithered        if withered or not
     */
    public void setIsWithered (boolean isWithered) {
        this.isWithered = isWithered;
    }
        
    
    /** 
     * This method updates the seed cost.
     * @param seedCost          cost of seed
     */
    public void setSeedCost(int seedCost){
        this.seedCost = seedCost;
    }
}
