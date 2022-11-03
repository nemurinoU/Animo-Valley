/***
 * <h1>PlotLand</h1>
 * 
 * <p>
 * This class describes a singular plot of land and has various attributes depending
 * on what the plot is being used for. Multiple instances of this class aggregate into
 * the entire farm plot grid.
 * </p>
 * 
 * @author icesw
 *
 */
public class PlotLand {
    private int x, y;
    private boolean isOccupied = false, hasRock = false, isPlowed = false;
    private Crop plantedCrop;

    //Basic __INIT with NO crop yet
    public PlotLand(boolean isOccupied, boolean hasRock, boolean isPlowed) {
        /*Let's be mathematically consistent with cartesian values and start with <0,0>*/
        this.x = 0; 
        this.y = 0;
        this.isOccupied = isOccupied;
        this.hasRock = hasRock;
        this.isPlowed = isPlowed;
        this.plantedCrop = null;
    }
    
    public Crop getCrop () {
    	return this.plantedCrop;
    }
    
    public void setCrop (Crop crop) {
    	plantedCrop = crop;
    }
    
    public void delCrop () {
    	plantedCrop = null;
    }
    /* GETTERS */

    public boolean getIsOccupied(){
        return this.isOccupied;
    }
    
    public boolean getHasRock() {
        return this.hasRock;
    }

    public boolean getIsPlowed(){
        return this.isPlowed;
    }
    
    /* SETTERS */
    
    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public void setHasRock(boolean hasRock) {
        this.hasRock = hasRock;
    }

    public void setIsPlowed(boolean isPlowed){
        this.isPlowed = isPlowed;
    }
}
