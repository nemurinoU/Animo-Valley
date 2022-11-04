import java.util.ArrayList;
public class Display {
    public static void displayStats(Farmer farmer, double farmerXP, double farmerCoins){
		for (int i = 0; i < 17; i++) 
			System.out.print ("=");

		System.out.println ("\n[" + MyFarm.getFarmName() + "]");
		
		System.out.println (farmer.getName() + " - DAY " + MyFarm.getCurrentDay());
		System.out.println (farmer.getFarmerTitle());
		System.out.println ("LVL: " + farmer.getLvl());
		System.out.println ("EXP: " + farmerXP + " / " + (farmer.getLvl() + 1) * 100);
		System.out.println ("BAL: " + farmerCoins + " coins");
		
		
		for (int i = 0; i < 17; i++) 
			System.out.print ("=");
		System.out.println("\n");
	}

	/* METHODS FOR DISPLAYING COMMANDS */
	
	public static void unplowedCommands(){
		System.out.println("[1] Plow - plow land (0 coins)");
		System.out.println("[2] Pickaxe - remove rock (50 coins)");
		System.out.println("[3] Shovel - dig plot (7 coins)");
		System.out.println("[0] Go back");
		System.out.println("------------------------");
		System.out.print("> ");
		
	}
	
	public static void plowedNoCropCommands(){
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

    /***
        This code is responsible for displaying the PLOTGRID and their states.
        
        [t] has turnip crop
        [T] fully grown harvestable turnip
        [-] plowed and empty
        [R] rocked
        [_] not plowed
        [X] withered
        ***/
    public static void displayGrid(ArrayList<PlotLand> plotGrid) {
		for (int i = 0; i < plotGrid.size(); i++) {

			System.out.print ("[ ");

			if (plotGrid.get(i).getIsPlowed() == false){ //not plowed 
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

			System.out.println (" ]");
		}
	}

	public void seedList(){
		System.out.println("Select seed you want to plant: ");
		System.out.println("[1] Turnip");
		System.out.println("[0] Go back");
		System.out.println("------------------------");
		System.out.print("> ");

	}
}
