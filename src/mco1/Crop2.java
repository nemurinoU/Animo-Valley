package mco1;

interface Sellable {

}

abstract class Crop {
    private String  cropName;

    private double  basePrice,
                    xpYield;

    private int     harvestTime,
                    seedCost,
                    waterNeeds,
                    waterBonusLimit,
                    fertNeeds,
                    fertBonusLimit,
                    minProduce,
                    maxProduce;

    public Crop (String cropName, double basePrice, double xpYield, 
                int harvestTime, int seedCost, int waterNeeds,
                int waterBonusLimit, int fertNeeds, int fertBonusLimit,) {
        this.cropName = cropName;
        this.basePrice = basePrice;
        this.xpYield = xpYield;
    }


}
