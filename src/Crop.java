package src;
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
    
    //0 - root crop, 1 - flower, 2 - fruit tree for cropTypeID
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

    
    public void activateCrop (int currentDay) {
    	this.dayPlanted = currentDay;
    	this.isHarvestable = false;
    	this.isWithered = false;
    	this.timesWatered = 0;
    	this.timesFertilized = 0;
    }
    
    public void fertilizeSelf () {
    	if (this.getTimesFertilized() < this.getFertilizerBonus())
    		this.timesFertilized++;
    }
    
    public void waterSelf () {
    	if (this.getTimesWatered () < this.getWaterBonus ())
    		this.timesWatered++;
    }
    
    
    public void grow (int currentDay) {
    	int daysElapsed;
    	
    	daysElapsed = currentDay - this.getDayPlanted();
    	
    	if (daysElapsed < this.getHarvestTime ())
    		System.out.println ("Plants growing...");
    	else if (daysElapsed == this.getHarvestTime ()) {
    		if (this.getTimesWatered () >= this.getWaterNeed () && this.getTimesFertilized () >= this.getFertilizerNeed())
    			this.setIsHarvestable (true);
    		else
    			this.setIsWithered (true);
    	}
    	else if (daysElapsed > this.getHarvestTime () && !this.getIsWithered()) {
    		this.setIsHarvestable(false);
    		this.setIsWithered (true);
    	}
    		
    }
    
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

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public int getCropTypeID() {
        return this.cropTypeID;
    }

    public void setCropTypeID(int cropTypeID) {
        this.cropTypeID = cropTypeID;
    }

    public int getHarvestTime() {
        return this.harvestTime;
    }

    public void setHarvestTime(int harvestTime) {
        this.harvestTime = harvestTime;
    }

    public int getWaterNeed() {
        return this.waterNeed;
    }

    public void setWaterNeed(int waterNeed) {
        this.waterNeed = waterNeed;
    }

    public int getWaterBonus() {
        return this.waterBonus;
    }

    public void setWaterBonus(int waterBonus) {
        this.waterBonus = waterBonus;
    }

    public int getFertilizerNeed() {
        return this.fertilizerNeed;
    }

    public void setFertilizerNeed(int fertilizerNeed) {
        this.fertilizerNeed = fertilizerNeed;
    }

    public int getFertilizerBonus() {
        return this.fertilizerBonus;
    }

    public void setFertilizerBonus(int fertilizerBonus) {
        this.fertilizerBonus = fertilizerBonus;
    }

    public int getMinProduce() {
        return this.minProduce;
    }

    public void setMinProduce(int minProduce) {
        this.minProduce = minProduce;
    }

    public int getMaxProduce() {
        return this.maxProduce;
    }

    public void setMaxProduce(int maxProduce) {
        this.maxProduce = maxProduce;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getXpYield() {
        return this.xpYield;
    }

    public void setXpYield(double xpYield) {
        this.xpYield = xpYield;
    }

    public int getCropID() {
        return this.cropID;
    }

    public void setCropID(int cropID) {
        this.cropID = cropID;
    }
    
    public int getDayPlanted () {
    	return this.dayPlanted;
    }
    
    public void setIsHarvestable (boolean isHarvestable) {
    	this.isHarvestable = isHarvestable;
    }
    
    public void setIsWithered (boolean isWithered) {
    	this.isWithered = isWithered;
    }
    
    public int getSeedCost () {
    	return this.seedCost;
    }

    public void setSeedCost(int seedCost){
        this.seedCost = seedCost;
    }
}
