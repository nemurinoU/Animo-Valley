package logic;

public class MyFarm {
    private logic.Farmer farmer;
    
    private String farmName = "Ram Ranch";
    private int currentDay = 0;


<<<<<<< Updated upstream
    public MyFarm (){
        this.farmer = new mco1.Farmer();
=======
    public MyFarm (String farmName, String farmerName){
        this.farmName = farmName;
        this.farmer = new logic.Farmer(farmerName);

        this.farmField = new PlotGrid ();
    }

    public PlotGrid getFarmField() {
        return this.farmField;
>>>>>>> Stashed changes
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

    public logic.Farmer getFarmer () {
        return this.farmer;
    }

    public void incrementCurrentDay () {
        this.currentDay ++;
    }
}
