package Model;

/***
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
    private Coordinates coords;
    private boolean isOccupied = false, hasRock = false, isPlowed = false;
    private Crop plantedCrop;

	/**
    * This constructor method creates a new instance of PlotLand with NO CROP, basic initialization
	*
    * @param isOccupied		if tile is occupied
	* @param hasRock		if tile has rock
	* @param isPlowed		if tile is plowed or not
    * @param x the x coordinate
    * @param y the y coordinate
	*
    */
    //Basic __INIT with NO crop yet
    public PlotLand(boolean isOccupied, boolean hasRock, boolean isPlowed, int x, int y) {
        this.coords = new Coordinates (x, y);
        this.isOccupied = isOccupied;
        this.hasRock = hasRock;
        this.isPlowed = isPlowed;
        this.plantedCrop = null;
    }

    
    /** 
     * This method gets the current coordinates
     * @return Coordinates current coordinates
     */
    public Coordinates getCoords () {
        return this.coords;
    }

    
    /** 
     * This method checks if the crop on the plot is harvestable
     * @return boolean Value (true/false) on whether the crop is harvestable or not
     */
    public boolean isHarvestable() {
        if (this.getCrop() != null)
            return this.getCrop().getIsHarvestable();

        return false;
    }

    
    /** 
     * This method checks whether or not the crop on the plot is withered
     * @return boolean Value (true/false) of plant if withered or not
     */
    public boolean isWithered () {
        if (this.getCrop() != null)
            return this.getCrop().getIsWithered();

        return false;
    }

    
    /** 
     * This method returns the public ID of the crop
     * @return int The current crop ID + 2
     */
    public int getPublicID () {
        if (this.getCrop() != null)
            return this.getCrop().getCropID() + 2;
    
        return 0;    
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
     * This method gets the current x coordinate of the plot
     * @return int X component of coordinate
     */
    public int getX() {
        return this.coords.getX();
    }

    
    /** 
     * This method gets the current y coordinate of the plot
     * @return int Y component of coordinate
     */
    public int getY() {
        return this.coords.getY();
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
