package logic;

import java.util.ArrayList;
/***
 * <h1>Display</h1>
 * <p>
 * This class is used to display the statistics of the farmer.
 * It also displays the main menu options that the user can select from.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-07 
 */
public class Display {
	
	/** This method displays the statistics of the farmer, which is called as a subroutine in the MyFarm class.
	 * @param farmer The Farmer object
	 * @param farmerXP The amount of XP the farmer has
	 * @param farmerCoins The coins balance of the farmer
	 */
	public void displayStats(Farmer farmer, double farmerXP, double farmerCoins){
		for (int i = 0; i < 17; i++) 
			System.out.print ("=");

		System.out.println ("\n[" + MyFarm.getFarmName() + "]");
		
		System.out.println (farmer.getName() + " - DAY " + MyFarm.getCurrentDay());
		System.out.println (farmer.getFarmerTitle());
		System.out.println ("LVL: " + farmer.getLvl());
		System.out.println ("EXP: " + farmerXP + " / " + (farmer.getLvl() + 1) * 100);
		System.out.printf ("BAL: %.2f%s%n", farmerCoins, " coins");

		for (int i = 0; i < 17; i++) 
			System.out.print ("=");
		System.out.println("\n");
	}

	/* METHODS FOR DISPLAYING COMMANDS */
	/**
	 * This method simply displays the command list when a plot of land is unplowed.
	 */
	public void unplowedCommands(){
		System.out.println("[1] Plow - plow land (0 coins)");
		System.out.println("[2] Pickaxe - remove rock (50 coins)");
		System.out.println("[3] Shovel - dig plot (7 coins)");
		System.out.println("[0] Go back");
		System.out.println("------------------------");
		System.out.print("> ");
		
	}

	/**
	 * This method simply displays the command list when a plot of land is plowed but has no crop planted on it.
	 */
	public void plowedNoCropCommands(){
		System.out.println("[1] Seed - plant seed");
		System.out.println("[2] Shovel - dig plot (7 coins)");
		System.out.println("[0] Go back");
		System.out.println("------------------------");
		System.out.print("> ");
	}

	public static void plowedWithCropCommands(){
		System.out.println("[1] Watering can - water plot (0 coins)");
		System.out.println("[2] Fertilizer - fertilize plot (10 coins)");
		System.out.println("[3] Shovel - remove plant (7 coins)");
		System.out.println("[4] Harvest - harvest plant ");
		System.out.println("[0] Go back");
		System.out.println("------------------------");
		System.out.print("> ");
	}

	/**
	 * This method is responsible for displaying the plot grid and their respective states. 
	 * Legend:
	 * [t] has turnip crop
	 * [T] fully grown harvestabel turnip
	 * [-] plowed and empty
	 * [_] not plowed
	 * [R] rocked
	 * [X] withered
	 */
    public void displayGrid(logic.PlotGrid tempGrid) {
		ArrayList<logic.PlotLand> plotGrid = tempGrid.getPlotGrid();

		for (int i = 0; i < plotGrid.size(); i++) {
			if (i % 10 == 0) System.out.print("\n");
			
			System.out.print ("[ ");
			
			if (plotGrid.get(i).getHasRock() == true) {
				System.out.print ("R");
			}
			else if (plotGrid.get(i).getIsPlowed() == false){ //not plowed 
				System.out.print ("_");
			} 
			else if (plotGrid.get(i).getIsPlowed()){//plowed
					if (!plotGrid.get(i).getIsOccupied()) //plowed & not occupied
						System.out.print ("-");
					else if (plotGrid.get(i).getCrop().getIsHarvestable()) //plowed & harvestable
						System.out.print ("T");
					else if (plotGrid.get(i).getCrop().getIsWithered ()) //plowed & withered
						System.out.print ("X");
					else if (plotGrid.get(i).getIsOccupied())//plowed & occupied
						System.out.print ("t");
			}

			System.out.print (" ]");
		}
	}
	
	/**
	 * This method simply displays the seeds available for planting.
	 */
	public void seedList(){
		System.out.println("Select seed you want to plant: ");
		System.out.println("[1] Turnip");
		System.out.println("[0] Go back");
		System.out.println("------------------------");
		System.out.print("> ");

	}
}
