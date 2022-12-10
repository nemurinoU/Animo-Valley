package logic;
/***
 * <p>
 * This class is used to create the coordinates using x and y axis (x, y).
 * <p>
 * This class is used for getting and setting the coordinates of the tile.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-12-10
 */
public class Coordinates {
    private int x, y;

    /**
     * This is the constructor of the coordinates
     * @param x The x value of the coordinate
     * @param y The y value of the coordinate
     */
    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    /** 
     * This method gets the x value in the coordinate system
     * @return int The x value
     */
    public int getX () {
        return this.x;
    }

    
    /** 
     * This method gets the y value in the coordinate system
     * @return int The y value
     */
    public int getY () {
        return this.y;
    }

    
    /** 
     * This method checks if the coordinates of the two objects are equal
     * @param coordsB The coordinates of another object
     * @return boolean Whether the coordinates are the same or different
     */
    public boolean isEquals (Coordinates coordsB) {
        if (this.getX() == coordsB.getX() && this.getY() == coordsB.getY()) return true;
        return false;
    }

    
    /** 
     * This method linearizes the coordinates
     * @return int the linear coordinatrs of the object
     */
    public int linearize () {
        int linear;
        
        if (this.x > 0 && this.x <= 10 && this.y > 0 && this.y <= 5) {
            linear = (this.x - 1) + 10 * (this.y - 1);
            return linear;
        }
        
        //Returns -1 meaning it cannot be linearized
        return -1;
    }
}