import java.util.ArrayList;

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

    public PlotLand(boolean isOccupied, boolean hasRock, boolean isPlowed) {
        /*Let's be mathematically consistent with cartesian values and start with <0,0>*/
        this.x = 0; 
        this.y = 0;
        this.isPlowed = false; 
        this.isOccupied = isOccupied;
        this.hasRock = hasRock;
        this.isPlowed = isPlowed;
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
