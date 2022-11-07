/***
 *<h1>Crop</h1>
 *
 * There are two versions to this crop class. They can either be inside a dictionary
 * of crops storing all the significant information relating to that crop, or
 * they can be a crop inside a plot.
 * @author icesw
 *
 */
public class Crop {
    private String cropName;
    private double basePrice;
    private double xpYield;
    
    
    private int cropID, 
    			cropTypeID, //0 - root crop, 1 - flower, 2 - fruit tree for cropTypeID
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
    			
    /* CONSTRUCTOR */
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

    /* This method is called when the user plants a seed, thus activating the crop with default parameters */
    public void activateCrop (int currentDay) {
    	this.dayPlanted = currentDay;
    	this.isHarvestable = false;
    	this.isWithered = false;
    	this.timesWatered = 0;
    	this.timesFertilized = 0;
    }
    
    /* This method is called when the user uses fertilizer on the crop.
     * It is limited by the fertilizerBonus variable & the limit is increased when the farmer status is upgraded.
     */
    public void fertilizeSelf (int fertBonusLimitIncrease) {
    	if (this.getTimesFertilized() < this.getFertilizerBonus() + fertBonusLimitIncrease)
    		    this.timesFertilized++;
    }
    
    /* This method is called when the user waters the crop.
     * It is limited by the waterBonus variable & the limit is increased when the farmer status is upgraded.
     */
    public void waterSelf (int waterBonusLimitIncrease) {
    	if (this.getTimesWatered () < this.getWaterBonus () + waterBonusLimitIncrease)
    		    this.timesWatered++;
    }
    
    /* This is the method used in the growth of the plant.
     * It uses a variable daysElapsed, which is calculated by the currentDay - dayPlanted of the crop to determine the status of the
     * plant, whether or not it is ready for harvest, withered, or still growing.
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
    
    /* GETTERS */
    public int getTimesWatered () {
    	return this.timesWatered;
    }
    
    public int getTimesFertilized () {
    	return this.timesFertilized;
    }
    
    public boolean getIsHarvestable () {
    	return this.isHarvestable;
    }
    
    public boolean getIsWithered () {
    	return this.isWithered;
    }
    
    public String getCropName() {
        return this.cropName;
    }
    
    public int getCropTypeID() {
        return this.cropTypeID;
    }

    public int getHarvestTime() {
        return this.harvestTime;
    }

    public int getWaterNeed() {
        return this.waterNeed;
    }

    public int getWaterBonus() {
        return this.waterBonus;
    }

    public int getFertilizerNeed() {
        return this.fertilizerNeed;
    }

    public int getFertilizerBonus() {
        return this.fertilizerBonus;
    }

    public int getMinProduce() {
        return this.minProduce;
    }

    public int getMaxProduce() {
        return this.maxProduce;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public double getXpYield() {
        return this.xpYield;
    }

    public int getCropID() {
        return this.cropID;
    }
    
    public int getDayPlanted () {
    	return this.dayPlanted;
    }
    
    public int getSeedCost () {
    	return this.seedCost;
    }
    /* SETTERS */
    public void setCropTypeID(int cropTypeID) {
        this.cropTypeID = cropTypeID;
    }

    public void setHarvestTime(int harvestTime) {
        this.harvestTime = harvestTime;
    }

    public void setWaterNeed(int waterNeed) {
        this.waterNeed = waterNeed;
    }

    public void setWaterBonus(int waterBonus) {
        this.waterBonus = waterBonus;
    }

    public void setFertilizerNeed(int fertilizerNeed) {
        this.fertilizerNeed = fertilizerNeed;
    }

    public void setFertilizerBonus(int fertilizerBonus) {
        this.fertilizerBonus = fertilizerBonus;
    }

    public void setMinProduce(int minProduce) {
        this.minProduce = minProduce;
    }

    public void setMaxProduce(int maxProduce) {
        this.maxProduce = maxProduce;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setXpYield(double xpYield) {
        this.xpYield = xpYield;
    }

    public void setCropID(int cropID) {
        this.cropID = cropID;
    }

    public void setIsHarvestable (boolean isHarvestable) {
        this.isHarvestable = isHarvestable;
    }

    public void setIsWithered (boolean isWithered) {
        this.isWithered = isWithered;
    }
        
    public void setSeedCost(int seedCost){
        this.seedCost = seedCost;
    }
}
