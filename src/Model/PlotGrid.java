package Model;

import java.util.ArrayList;
/***
 * <p>
 * This class creates a PlotGrid object.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-07
 */
public class PlotGrid {
    private ArrayList<PlotLand> plotGrid;

    public PlotGrid () {
        this.plotGrid = new ArrayList<PlotLand>();
    }

    public ArrayList<PlotLand> getPlotGrid () {
        return this.plotGrid;
    }

    public PlotLand getPlot(int i) {
        return this.plotGrid.get(i);
    }
  
    public void update (int currentDay) {
        for (int i = 0; i < plotGrid.size(); i++) {
            if (plotGrid.get(i).getCrop() != null) {
               plotGrid.get(i).getCrop().grow(currentDay);
            }
       }
    }

    public void addPlot (PlotLand plot) {
        this.plotGrid.add(plot);
    }
}