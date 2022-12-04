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
}