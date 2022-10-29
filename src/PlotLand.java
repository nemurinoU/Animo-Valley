import java.util.ArrayList;

public class PlotLand {
    private String name;
    private int day;
    private boolean isPlowable = true, hasRock = false;
    private int[] landSize; //0 is x and 1 is y

    public PlotLand(String name, int day, boolean isPlowable, boolean hasRock, int[] landSize) {
        this.name = name;
        this.day = day;
        this.isPlowable = isPlowable;
        this.hasRock = hasRock;
        this.landSize[0] = 1;
        this.landSize[1] = 1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isIsPlowable() {
        return this.isPlowable;
    }

    public boolean getIsPlowable() {
        return this.isPlowable;
    }

    public void setIsPlowable(boolean isPlowable) {
        this.isPlowable = isPlowable;
    }

    public boolean isHasRock() {
        return this.hasRock;
    }

    public boolean getHasRock() {
        return this.hasRock;
    }

    public void setHasRock(boolean hasRock) {
        this.hasRock = hasRock;
    }

    public int[] getLandSize() {
        return this.landSize;
    }

    public void setLandSize(int[] landSize) {
        this.landSize = landSize;
    }

}
