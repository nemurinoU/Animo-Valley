package logic;

public class MyFarm {
    private mco1.Farmer farmer;
    
    private String farmName = "Ram Ranch";
    private int currentDay = 0;


    public MyFarm (){
        this.farmer = new mco1.Farmer();
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
