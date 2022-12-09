package logic;

abstract class Crop {
    private String  cropName;

    private double  basePrice,
                    xpYield;

    private boolean isHarvestable,
                    isWithered;

    private int     harvestTime,
                    seedCost,
                    waterNeeds,
                    waterBonus,
                    fertNeeds,
                    fertBonus,
                    minProduce,
                    maxProduce,
                    cropType, // 1 - root, 2 - flower, 3 - fruit tree
                    cropID,
                    timesWatered,
                    timesFertilized,
                    dayPlanted;

    public Crop (String cropName, double basePrice, double xpYield, 
                int harvestTime, int seedCost, int waterNeeds,
                int waterBonus, int fertNeeds, int fertBonus,
                int minProduce, int maxProduce, int cropType, int cropID) {
        this.cropName = cropName;
        this.basePrice = basePrice;
        this.xpYield = xpYield;

        this.harvestTime = harvestTime;
        this.seedCost = seedCost;
        this.waterNeeds = waterNeeds;

        this.waterBonus = waterBonus;
        this.fertNeeds = fertNeeds;
        this.fertBonus = fertBonus;

        this.minProduce = minProduce;
        this.maxProduce = maxProduce;
        this.cropType = cropType;
        this.cropID = cropID;

        this.timesFertilized = 0;
        this.timesWatered = 0;
    	this.isHarvestable = false;
    	this.isWithered = false;
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

    public int getCropType () {
        return this.cropType;
    }

    /** 
     * This method is called when the user uses fertilizer on the crop.
     * It is limited by the fertilizerBonus variable & the limit is increased when the farmer status is upgraded.
     * @param fertBonusIncrease The value of the increase in the fertilizer bonus limit.
     */
    public void fertilizeSelf (int fertBonusIncrease) {
    	if (this.getTimesFertilized() < this.getFertilizerBonus() + fertBonusIncrease)
    		    this.timesFertilized++;
    }
    
    
    /** 
     * This method is called when the user waters the crop.
     * It is limited by the waterBonus variable & the limit is increased when the farmer status is upgraded.
     * @param waterBonusIncrease The value of the increase in the water bonus limit.
     */

    public void waterSelf (int waterBonusIncrease) {
    	if (this.getTimesWatered () < this.getWaterBonus () + waterBonusIncrease)
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
    		this.setIsHarvestable(false);
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
        return this.waterNeeds;
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
        return this.fertNeeds;
    }

    
    /** 
     * This method gets the fertilizer bonus limit of the crop.
     * @return int      fertilizer bonus of crop
     */
    public int getFertilizerBonus() {
        return this.fertBonus;
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
    public void setWaterNeed(int waterNeeds) {
        this.waterNeeds = waterNeeds;
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
        this.fertNeeds = fertilizerNeed;
    }

    
    /** 
     * This method updates the fertilizer bonus limit of the crop.
     * @param fertilizerBonus   fertilizer bonus of crop
     */
    public void setFertilizerBonus(int fertilizerBonus) {
        this.fertBonus = fertilizerBonus;
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

class Turnip extends Crop {
    public Turnip () {
        super ("Turnip", 6.0, 5.0,
              2, 5, 1,
              2, 0, 1,
              1, 2, 1, 5);
    }
}

class Carrot extends Crop {
    public Carrot () {
        super ("Carrot", 9.0, 7.5,
              3, 10, 1,
              2, 0, 1,
              1, 2, 1, 6);
    }
}

class Potato extends Crop {
    public Potato () {
        super ("Potato", 3.0, 12.5,
              5, 20, 3,
              4, 1, 2,
              1, 10, 1, 7);
    }
}

class Rose extends Crop {
    public Rose () {
        super ("Rose", 5, 2.5,
            1, 5, 1,
            2, 0, 1,
            1, 1, 2, 8);
    }
}

class Tulip extends Crop {
    public Tulip () {
        super ("Tulip", 9, 5,
            2, 10, 2,
            3, 0, 1,
            1, 1, 2, 9);
    }
}

class Sunflower extends Crop {
    public Sunflower () {
        super ("Sunflower", 19, 7.5,
        3, 20, 2,
        3, 1, 2,
        1, 1, 2, 10);
    }
}

class Mango extends Crop {
    public Mango () {
        super ("Mango", 8, 25,
        10, 100, 7,
        7, 4, 4,
        5, 15, 3, 11);
    }
}

class Apple extends Crop {
    public Apple () {
        super ("Apple", 5, 25,
        10, 200, 7,
        7, 5, 5,
        10, 15, 3, 12);
    }
}
