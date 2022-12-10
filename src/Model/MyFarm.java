package Model;
import java.util.ArrayList;

/***
 * <p>
 * This class describes a the game world. It contains an instance of the Farmer and PlotGrid class.[
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-07 
 */
public class MyFarm {
    private Model.Farmer farmer;
    
    private String farmName;
    private int currentDay = 0;
    private PlotGrid farmField;

    public MyFarm (String farmName, String farmerName){
        this.farmName = farmName;
        this.farmer = new Model.Farmer(farmerName);
        this.farmField = new PlotGrid ();
    }

    public PlotGrid getFarmField() {
        return this.farmField;
    }

    public void setFarmField (PlotGrid tempGrid) {
        this.farmField = tempGrid;
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

    public Model.Farmer getFarmer () {
        return this.farmer;
    }

    public boolean gameOver () {
        // this function checks for the gameOver condition:
        // 1) Not enough money to plant a new seed
        // 2) No active growing plants

        ArrayList<PlotLand> temp;
        boolean rCode = true;

        temp = this.farmField.getPlotGrid();

        if (this.farmer.getCoins() >= 5)
            rCode = false; // game goes on
        else { // if not enough money, check 2nd condition
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getCrop() != null && // if the plot has a crop (not null)
                    !temp.get(i).getCrop().getIsWithered()) rCode = false;
            }
        }
        
        return rCode;
    }

}
