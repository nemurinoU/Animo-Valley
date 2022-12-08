package logic;

public class Coordinates {
    int x, y;

    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX () {
        return this.x;
    }

    public int getY () {
        return this.y;
    }

    public boolean isEquals (Coordinates coordsB) {
        if (this.getX() == coordsB.getX() && this.getY() == coordsB.getY()) return true;
        return false;
    }

    public int linearize () {
        int linear;
        
        if (this.x > 0 && this.x <= 10 && this.y > 0 && this.y <= 5) {
            linear = (this.x - 1) + 10 * (this.y - 1);
            return linear;
        }
        
        return -1;
    }
}