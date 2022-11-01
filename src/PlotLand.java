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
    private int daysElapsed, x, y;
    private boolean isPlowable = true, hasRock = false;

    public PlotLand(int daysElapsed, boolean isPlowable, boolean hasRock) {
        this.daysElapsed = 0;
        this.x = 0;
        this.y = 0;
        this.isPlowable = isPlowable;
        this.hasRock = hasRock;
    }
    
    public boolean getIsPlowable() {
        return this.isPlowable;
    }

    public void setIsPlowable(boolean isPlowable) {
        this.isPlowable = isPlowable;
    }
    
    public boolean getHasRock() {
        return this.hasRock;
    }

    public void setHasRock(boolean hasRock) {
        this.hasRock = hasRock;
    }


}
