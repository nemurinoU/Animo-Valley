package mco1;

/***
 * <h1>PlotLand</h1>
 * 
 * <p>
 * This class describes a singular plot of land and has various attributes depending
 * on what the plot is being used for. Multiple instances of this class aggregate into
 * the entire farm plot grid.
 * </p>
 * 
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-07 
 */
public class PlotLand {
	/**
	* Private Variable Instantiation
	* > x, y describe the coordinates of a tile inside a plotGrid (a collection of PlotLand objects)
	* > isOccupied tells us if the PlotLand object is occupied by a crop
	* > hasRock tells us if the PlotLand objects has a rock on it
	* > isPlowed tells us if the tile is plowed or not
	* > plantedCrop is an object instance from the Crop class "planted" inside the PlotLand
	*
	*/
    private int x, y;
    private boolean isOccupied = false, hasRock = false, isPlowed = false;
    private Crop plantedCrop;

	/**
    * This constructor method creates a new instance of PlotLand with NO CROP, basic initialization
	*
    * @param isOccupied		if tile is occupied
	* @param hasRock		if tile has rock
	* @param isPlowed		if tile is plowed or not
	*
    */
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
    
	/**
    * This method gets the Crop object
    * 
    * @return Crop		crop object planted on tile
    */
    public Crop getCrop () {
    	return this.plantedCrop;
    }
    
	/**
    * This method sets the Crop object
    * 
    * @param crop		crop obj to put into the tile
    */
    public void setCrop (Crop crop) {
    	plantedCrop = crop;
    }
    
	/**
    * This method deletes the crop object on the tile
    * 
    * 
    */
    public void delCrop () {
    	plantedCrop = null;
    }
    /* GETTERS */

	/**
    * This method gets isOccupied
    * 
    * @return boolean	if tile is occupied by plant or not
    */
    public boolean getIsOccupied(){
        return this.isOccupied;
    }
    
	/**
    * This method gets hasRock
    * 
    * @return boolean	if tile has a rock or not
    */
    public boolean getHasRock() {
        return this.hasRock;
    }

	/**
    * This method gets isPlowed
    * 
    * @return boolean	if tile is plowed or not
    */
    public boolean getIsPlowed(){
        return this.isPlowed;
    }
    
    /* SETTERS */
    /**
    * This method sets isOccupied
    * 
    * @param isOccupied		tells us if occupied or not
    */
    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

	/**
    * This method sets hasRock
    * 
    * @param hasRock		if has rock or not
    */
    public void setHasRock(boolean hasRock) {
        this.hasRock = hasRock;
    }

	/**
    * This method sets isPlowed
    * 
    * @param isPlowed		tells us if plowed or not
    */
    public void setIsPlowed(boolean isPlowed){
        this.isPlowed = isPlowed;
    }
}
