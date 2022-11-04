//import java.io.*;

//import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;

/***
 * <h1>MyFarm</h1>
 * <p>
 * This class emulates the entire farm, it is NOT the controller class.
 * But for simplicity's sake in PHASE 1, this shall be the controller class.
 * It displays all the information on the farmer and farm status such as the days
 * </p>
 * @author icesw
 * @author richjpex
 */

public class MyFarm {
	private static String farmName;
	private static int currentDay = 0;
	private static boolean gameOver = false;
	private static ArrayList<PlotLand> plotGrid = new ArrayList<PlotLand>();
	private static ArrayList<Crop> cropBook = new ArrayList<Crop>();
	
	
	//hi
	//hello srry HAHHAHA
	//it would be funny if we leave all these comments behind and sir would read them LMAO HAHAHAHA HI SIR ED WE ARE CRA- i mean making last minute changes

	/*in the future, the game will load plant data from a json file
	 * 
	 * 
	 * Crop(String cropName, int cropTypeID, int harvestTime, 
    			int waterNeed, int waterBonus, int fertilizerNeed, 
    			int fertilizerBonus, int minProduce, int maxProduce, 
    			double basePrice, double xpYield, int cropID) 
	 * */
	public static void generateCropBook () {
		
		cropBook.add(new Crop("Turnip", 1, 2, 
    			1, 2, 0, 
    			1, 1, 2, 
    			6, 5, 0,
    			5) );
	}
	
	
	
	/* METHODS FOR MAIN MENU OPTIONS (1, 2, 3) 
	 * goToTile accepts a Farmer and PlotLand parameter
	 * It makes use of the tools and seeds to be planted based on the commands show
	*/
	public static void goToTile (Farmer farmer, PlotLand tempPlot) {
		int nCode;
		Scanner sc = new Scanner(System.in);
		ActionHandler ah = new ActionHandler();
		
		//there should be code here that asks which tile the farmer goes to
		System.out.println (farmer.getName() + " goes to tile (0, 0)...");

		System.out.println("------------------------");
		Display.displayGrid (plotGrid);
		System.out.println("------------------------");

		if ( !tempPlot.getIsPlowed()){ // When plot is NOT plowed
				Display.unplowedCommands();
				
				nCode = sc.nextInt ();
				ah.plotIsRawLogic (nCode, tempPlot, farmer);
		}
		else if (tempPlot.getIsPlowed()){ // When plot is plowed
			
			if ( !tempPlot.getIsOccupied()){ // When plowed plot is NOT occupied (i.e., no crop on it)
				Display.plowedNoCropCommands();
				nCode = sc.nextInt();
				ah.plotIsPlowedLogic(nCode, tempPlot, farmer, currentDay, cropBook);
			}
			else { //When plowed plot IS occupied, actions are: water, fertilize, shovel
				Display.plowedWithCropCommands();
				nCode = sc.nextInt ();

				ah.plotIsCroppedLogic (nCode, tempPlot, farmer, currentDay);
			}

		}
		
	}
	
	/* This could definitely be better but I got the logic down for basis */
	public static void registerFarmer (Farmer farmer) {
		Scanner sc = new Scanner (System.in);
		ActionHandler ah = new ActionHandler ();
		String[] farmerTier = {"Farmer", "Registered Farmer", "Distinguished Farmer", "Legendary Farmer"};
		int[] tierFees = {200, 300, 400}, tierLvl = {0, 5, 10, 15};
		int farmerType, rCode;

		farmerType = farmer.getFarmerType ();
		if (farmerType < 3) { // make sure no out of bounds stuff happens
				if (farmer.getLvl () >= tierLvl[farmerType + 1]) {  // check if lvl is sufficient
						System.out.println ("Current Tier: " + farmerTier[farmerType]); //check msgr
						System.out.println ("Upgrade to " + farmerTier[farmerType + 1] + " [Y/N]");
						System.out.println ("Fee: " + tierFees[farmerType]);
						System.out.println ("NOTE: You need at least 5 extra coins to upgrade, otherwise you will have insufficient coins to buy seeds.");
						System.out.print("> ");

						rCode = ah.isValidYN(sc.next().charAt(0));

						if (rCode == -1) // if not Y/N
								ah.errorMessage ("Invalid input.");
						else if (rCode == 0) // when N
								System.out.println ("Registration cancelled.");
						else // when Y
								if (farmer.getCoins () - tierFees[farmerType] >= 5) {
										farmer.setCoins(farmer.getCoins() - tierFees[farmerType]);
										farmer.setFarmerType(farmer.getFarmerType() + 1);
										farmer.setFarmerTitle(farmer.getFarmerType());
										ah.alertMessage("Farmer Status Upgraded!");
								}
								else {
										ah.errorMessage ("Insufficient coins.");
								}
				}
				else 
						ah.errorMessage("Insufficient level to register higher tier. Required Lvl: " + tierLvl[farmerType + 1]);
				
		}
		else
				ah.alertMessage ("Maximum Farmer Tier reached!");
		
	}
	
	public static void sleepTheNight (ArrayList<PlotLand> plotGrid) {
		System.out.println ("Sleeping...");
		currentDay++;
		
		for (int i = 0; i < plotGrid.size (); i++) {
			if (plotGrid.get(i).getCrop() != null)
				plotGrid.get(i).getCrop().grow(currentDay);
		}
	}
	
	public static void main (String[] args) {
		Scanner myObj = new Scanner(System.in);
		//String farmerName; do we need this? if yes, keep; else delete
		PlotLand tempPlot;
		//generate crop list dictionary (contains only Turnip for MCO1)
		generateCropBook ();
		
		/* ASK USER FOR THEIR FARMER NAME */
		System.out.print ("\nEnter name: ");
		Farmer farmer = new Farmer (myObj.nextLine());
		
		System.out.println ("\nWelcome to Animo Valley, " + farmer.getName() + ".\n");
		/***
		DEV NOTES: This should honestly be in a class of methods OR just subroutines in this file that manages what is displayed on the screen.
		But this is okay for now. 
		 */

		/* ASK USER FOR FARM NAME */
		System.out.print("Enter farm name: ");
		farmName = myObj.nextLine();

		System.out.println("\nYour farm name is: " + farmName + "\n");
		
		/***
		================================
		START OF PLOT GRID GENERATION
		================================
		NOTES for the future: This part of the code will generate the entire farm plot grid system. There will be a subroutine responsible for
		generating the rocks randomly in the grid.
		 */
		plotGrid.add(new PlotLand(true, false, false));
		/*** END OF PLOT GRID CODE */
		
		/*** 
		================================
		START OF DAILY LIFE UPDATES CODE 
		================================
		*/
		while (!gameOver){
			//actually farmer should updateLvl everytime XP is gained...
			farmer.updateLvl();
			Display.displayStats(farmer, farmer.getXp(), farmer.getCoins());
			
		
			System.out.println ("What would you like to do? ");
			System.out.println ("1) Go to Tile 2) Register 3) Sleep the Night Away");
			System.out.print("> ");
			
			switch (myObj.nextInt ()) {
			case 1:
				tempPlot = plotGrid.get(0);
				
				goToTile(farmer, tempPlot);
				
				break;
			case 2:
				registerFarmer(farmer);
				break;
			case 3:
				sleepTheNight(plotGrid);
				break;
			case 0:
				System.out.println();
			default:
				break;
			}
		}
		/*** END OF DAILY LIFE UPDATES CODE */
		myObj.close();
	}

	public static int getCurrentDay(){
		return currentDay;
	}

	public static String getFarmName(){
		return farmName;
	}

	public static ArrayList<PlotLand> getPlotGrid(){
		return plotGrid;
	}
}