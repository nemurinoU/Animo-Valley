public class Crop {
    private String cropName, cropType;
    private int cropTypeID; //0 - root crop, 1 - flower, 2 - fruit tree
    private int harvestTime;
    private int waterNeed, waterBonus;
    private int fertilizerNeed, fertilizerBonus;
    private int minProduce, maxProduce;
    private double basePrice;
    private double xpYield;
    private int cropID;


    public Crop(String cropName, String cropType, int cropTypeID, int harvestTime, int waterNeed, int waterBonus, int fertilizerNeed, int fertilizerBonus, int minProduce, int maxProduce, double basePrice, double xpYield, int cropID) {
        this.cropName = cropName;
        this.cropType = cropType;
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
    }

    public String getCropName() {
        return this.cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropType() {
        return this.cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
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
    

}
