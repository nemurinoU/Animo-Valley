package logic;

/***
 * <h1>MyFarm</h1>
 * <p>
 * This class describes a the game world. It contains an instance of the Farmer and PlotGrid class.[
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-07 
 */
public class MyFarm {
    private logic.Farmer farmer;
    
    private String farmName;
    private int currentDay = 0;
    private PlotGrid farmField;

    public MyFarm (String farmName, String farmerName){
        this.farmName = farmName;
        this.farmer = new logic.Farmer(farmerName);
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

    public logic.Farmer getFarmer () {
        return this.farmer;
    }

}
