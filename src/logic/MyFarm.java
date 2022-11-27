package logic;

public class MyFarm {
    private mco1.Farmer farmer;
    
    private String farmName;
    private int currentDay = 0;
    private PlotGrid farmField;


    public MyFarm (String farmName, String farmerName){
        this.farmName = farmName;
        this.farmer = new mco1.Farmer(farmerName);

        this.farmField = new PlotGrid ();
    }

    public PlotGrid getFarmField() {
        return this.farmField;
    }
    
    public void incrementCurrentDay () {
        this.currentDay++;
    }

    public int getCurrentDay () {
        return this.currentDay;
    }

    public String getFarmName () {
        return this.farmName;
    }

    public mco1.Farmer getFarmer () {
        return this.farmer;
    }

    public void incrementCurrentDay () {
        this.currentDay ++;
    }
}
