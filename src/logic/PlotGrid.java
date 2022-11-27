package logic;

import java.util.ArrayList;

public class PlotGrid {
    private ArrayList<PlotLand> plotGrid;

    public PlotGrid () {
        this.plotGrid = new ArrayList<PlotLand>();
    }

    public ArrayList<PlotLand> getPlotGrid () {
        return this.plotGrid;
    }
  
    public void update (int currentDay) {
        for (int i = 0; i < plotGrid.size(); i++) {
            if (plotGrid.get(i).getCrop() != null) {
               plotGrid.get(i).getCrop().grow(currentDay);
            }
       }
    }
}