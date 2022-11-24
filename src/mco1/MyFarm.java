package mco1;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.FlowLayout;

/***
 * <h1>MyFarm</h1>
 * <p>
 * This class emulates the entire farm, it is NOT the controller class.
 * But for simplicity's sake in PHASE 1, this shall be the controller class.
 * It displays all the information on the farmer and farm status such as the days
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-07 
 */

public class MyFarm {
	/**
	* Private Variable Instantiation
	* > farmName is the farm's entire name
	* > currentDay is the current day in the game
	* > gameOver tells us if the game is over
	* > plotGrid is the entirety of the plots to be tilled and planted on
	* > cropBook is a "database" of existing crops in the game
	*/
	private static String farmName;
	private static int currentDay = 0;
	private static boolean gameOver = false;
	private static ArrayList<PlotLand> plotGrid = new ArrayList<PlotLand>();
	private static ArrayList<Crop> cropBook = new ArrayList<Crop>();
	

	/*in the future, the game will load plant data from a json file
	 * 
	 * 
	 * Crop(String cropName, int cropTypeID, int harvestTime, 
    			int waterNeed, int waterBonus, int fertilizerNeed, 
    			int fertilizerBonus, int minProduce, int maxProduce, 
    			double basePrice, double xpYield, int cropID) 
	 *
	 *	
	 *
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
	 * It makes use of the tools and seeds to be planted based on the commands shown.
	 * It also uses an ActionHandler object to verify the logic of the tiles shown on the CLI.
	 *
	 * @param farmer		player object to move to tile
	 * @param tempPlot		plot object to be interacted with
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
	
	/* registerFarmer() takes in a Farmer object as a parameter and uses
	 * getters and setters to modify the farmer types.
	 * The farmer must have at least 5 coins to register each time (minimum cost of a seed) to upgrade
	 * their farmer type and receive bonuses.
	 * An ActionHandler object is used to display the messages given certain conditions.
	 *
	 * @param farmer	player object to register
	 *
	 */
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
	
	/* sleepTheNight() method is used to proceed to the next day, and
	 * increase the growth rate of all the crops on the plot land.
	 *
	 * @param plotGrid		the entire farm plant grid to go through for plants to grow
	 */
	public static void sleepTheNight (ArrayList<PlotLand> plotGrid) {
		System.out.println ("Sleeping...");
		currentDay++;
		
		for (int i = 0; i < plotGrid.size (); i++) {
			if (plotGrid.get(i).getCrop() != null)
				plotGrid.get(i).getCrop().grow(currentDay);
		}
	}
	
	/***
		Main function, where the program flow happens.
		ANIMO VALLEY MAIN FUNCTION
	*/
	public static void main (String[] args) {
		Board promptGUI = new Board ();
		Board mainGUI = new Board ();
		Scanner myObj = new Scanner(System.in);
		boolean startUp = true;
		//String farmerName; do we need this? if yes, keep; else delete
		PlotLand tempPlot;
		//generate crop list dictionary (contains only Turnip for MCO1)
		generateCropBook ();	

		
		/* ASK USER FOR THEIR FARMER NAME AND FARM NAME*/
		Farmer farmer = new Farmer ();
		mainGUI.initializeNamePrompt(farmer);
		
		/***
		DEV NOTES: This should honestly be in a class of methods OR just subroutines in this file that manages what is displayed on the screen.
		But this is okay for now. 
		We were a *little bit* lazy to put those in a subroutine but at least it works.
		 */
		
		/***
		================================
		START OF PLOT GRID GENERATION
		================================
		NOTES for the future: This part of the code will generate the entire farm plot grid system. There will be a subroutine responsible for
		generating the rocks randomly in the grid.
		 */
		plotGrid.add(new PlotLand(true, false, false));

		// Connecting the plot grid object to the GUI
		// should probably add a thing where the GUI gets updated every action on the plotGrid
		/*** END OF PLOT GRID CODE */

		/*** 
		================================
		START OF DAILY LIFE UPDATES CODE 
		================================
		*/
		
		while (!gameOver){
				if (farmer.getName () != null && startUp) { // this is for first time start up
					mainGUI.initializeDisplayStats (farmer, MyFarm.getCurrentDay ());
					mainGUI.initializePlotTiles ();
					mainGUI.reloadFrame ();
					startUp = false;
				}
				
				if (!startUp) { // this is for when the day cycle, the gui updates
					mainGUI.updateDisplayStats (farmer, MyFarm.getCurrentDay ());
				}

				System.out.println ("What would you like to do? ");
				System.out.println ("1) Go to Tile 2) Register 3) Sleep the Night Away");
				System.out.print("> ");
				
				/** 
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
				default:
					break;
				}***/
				if (farmer.getCoins() <= 0){
					System.out.println("Game over! You ran out of money.");
					gameOver = true;
				}
		}
		/*** END OF DAILY LIFE UPDATES CODE */
		myObj.close();
	}

	/* GETTERS FOR VARIABLES TO BE USED IN DISPLAY.JAVA */
	
	/**
    * This method gets the current game day
    * 
    * @return int		the current game day
    */
	public static int getCurrentDay(){
		return currentDay;
	}

	/**
    * This method gets the farm name
    * 
    * @return String		the farm name
    */
	public static String getFarmName(){
		return farmName;
	}
	
	/**
    * This method gets the farm plot grid
    * 
    * @return ArrayList<PlotLand>		the entire grid object in the farm
    */
	public static ArrayList<PlotLand> getPlotGrid(){
		return plotGrid;
	}
}