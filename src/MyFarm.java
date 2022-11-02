import java.awt.datatransfer.SystemFlavorMap;
import java.io.*;

import java.lang.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Scanner;

/***
 * <h1>MyFarm</h1>
 * <p>
 * This class emulates the entire farm, it is NOT the controller class.
 * But for simplicity's sake in PHASE 1, this shall be the controller class.
 * It displays all the information on the farmer and farm status such as the days
 * </p>
 * @author icesw
 * @author richjpexfromtiktok
 */

public class MyFarm {
	private static String farmName;
	private static int currentDay = 0;
	private static boolean gameOver = false;
	private static ArrayList<PlotLand> plotGrid = new ArrayList<PlotLand>();
	private static ArrayList<Crop> cropBook = new ArrayList<Crop>();
	
	public static void displayStats(Farmer farmer, double farmerXP, double farmerCoins){
		System.out.println ("[" + farmName + "]");
		
		System.out.println (farmer.getName() + " - DAY " + currentDay);
		System.out.println ("LVL: " + farmer.getLvl());
		System.out.println ("EXP: " + farmerXP + " / " + (farmer.getLvl() + 1) * 100);
		System.out.println ("BAL: " + farmerCoins + " coins");
		
		
		for (int i = 0; i < 10; i++) 
			System.out.print ("=");
		System.out.println("\n");
	}

	/* METHODS FOR DISPLAYING COMMANDS */
	
	public static void unplowedCommands(){
		System.out.println("[1] Plow - plow land (0 coins)");
		System.out.println("[2] Pickaxe - remove rock (50 coins)");
		System.out.println("[3] Shovel - remove plant (7 coins)");
		
	}
	
	public static void plowedCommands(){
		System.out.println("[1] Watering can - water plot (0 coins)");
		System.out.println("[2] Fertilizer - fertilize plot (10 coins)");
		System.out.println("[3] Shovel - remove plant (7 coins)");
		System.out.println("[4] Seed - plant seed");
		System.out.println("[5] Harvest - harvest plant ");
	}

	public static void seedList(){
		System.out.println("Select seed you want to plant: ");
		System.out.println("[1] Turnip");

	}
	
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
			else{
				if (plotGrid.get(i).getIsPlowed()){ //plowed
					if (!plotGrid.get(i).getIsOccupied()) //plowed & not occupied
						System.out.print ("-");
					else if (plotGrid.get(i).getCrop().getIsHarvestable())
						System.out.print ("T");
					else if (plotGrid.get(i).getCrop().getIsWithered ())
						System.out.print ("X");
					else if (plotGrid.get(i).getIsOccupied())//plowed & occupied
						System.out.print ("t");
					
				} 
				
			}
			
			//else if (plotGrid.get(i).getIsOccupied() == true) 
				
			System.out.println (" ]");
		}
	}
	
	public static void goToTile () {
		
	}
	
	public static void registerFarmer () {
		
	}
	
	public static void sleepTheNight () {
		
	}
	
	public static void main (String[] args) {
		Scanner myObj = new Scanner(System.in);
		String farmerName;
		PlotLand tempPlot;
		int nCode;
		
		generateCropBook ();
		
		/* ASK USER FOR THEIR FARMER NAME */
		System.out.print ("What is your name:  ");
		Farmer farmer = new Farmer (myObj.nextLine());
		
		System.out.println ("Welcome to Animo Valley, " + farmer.getName() + ".\n");
		/***
		DEV NOTES: This should honestly be in a class of methods OR just subroutines in this file that manages what is displayed on the screen.
		But this is okay for now. 
		 */

		/* ASK USER FOR FARM NAME */
		System.out.print("Input farm name: ");
		farmName = myObj.nextLine();

		System.out.println("\nYour farm name is : " + farmName + "\n");
		
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
			displayStats(farmer, farmer.getXp(), farmer.getCoins());
			
		
			System.out.println ("What would you like to do? ");
			System.out.println ("1) Go to Tile 2) Register 3) Sleep the Night ");
			
			switch (myObj.nextInt ()) {
			case 1:

				System.out.println ("Goes to tile (0, 0)");
				System.out.println("------------------------");
				displayGrid (plotGrid);
				System.out.println("------------------------");
				
				tempPlot = plotGrid.get(0);
				
				if (tempPlot.getIsPlowed() == false){ //if plot is not plowed
					unplowedCommands();
					switch (myObj.nextInt()){
					case 1: //farmer plows tile (0, 0)
						
						farmer.plowLand (tempPlot);
						System.out.println("Plot has been plowed!\n");
						
						break;

					case 2:
						//use pickaxe
						if (!tempPlot.getHasRock())
							System.out.println("There is no rock here.\n");
						else{
							farmer.mineRock(tempPlot);
							System.out.println("Rock destroyed!\n");
						}
						
						break;
					case 3:
						//use shovel
						farmer.digOut (tempPlot);
						break;
					default:
						System.out.println("Invalid command!\n");
						break;
					}
				
				}
				else if (tempPlot.getIsPlowed()){ //else if plot is plowed
					plowedCommands();
					
					switch (myObj.nextInt()){
					case 1: // farmer waters plant @ plotGrid (0,0)
						farmer.waterPlant(tempPlot);
						break;

					case 2: // farmer fertilizes plant @ plotGrid (0,0)
						farmer.fertilizePlant (tempPlot);
						break;

					case 3: // farmer uses shovel @ plotGrid (0,0)
						farmer.digOut(tempPlot);
						break;

					case 4:
						//display list of seeds and ask user which seed to plant
						seedList();
						nCode = myObj.nextInt();
						
						/*we should use a subroutine to check for validity of codes...*/
						// if valid nCode, then only one line for farmer.plantCrop ();
						switch (nCode){
						case 1:
							// farmer does the planting
							// farmer USES plotGrid given coordinate reference
							// farmer USES cropBook to copy TURNIP info and put into plot
							// nCode - 1 because seedList() starts at 1
							if (tempPlot.getIsOccupied())
								System.out.println ("You cannot plant on an occupied tile!\n");
							else
								farmer.plantCrop(tempPlot, cropBook.get(nCode - 1), currentDay);
							
							break;
						default:
							System.out.println("More seeds coming soon...");
							
							break;
						}
						
						break;
					case 5:
						//
						if (tempPlot.getIsOccupied ())
							if (tempPlot.getCrop().getIsHarvestable())
								farmer.harvestCrop(tempPlot);
							else
								System.out.println ("Crop not ready yet...\n");
						break;
					default:
						System.out.println("Invalid command!\n");
						break;
					}
				}
				break;
			case 2:
				/*this really needs subroutines for checking*/
				System.out.print ("Upgrade to ");
				switch (farmer.getFarmerType()) {
				case 0:
					if (farmer.getLvl () >= 5) {
						System.out.println ("Registered Farmer? [1/0]");
						System.out.println ("Fee: 200 coins\n");
						
						switch (myObj.nextInt()) {
						case 1:
							break;
						case 0:
						default:
							System.out.println ("Invalid choice!");
							break;
						}
					}
					else System.out.println ("Insufficient Level!\n");
					break;
					
				case 1:
				case 2:
					
				default:
					System.out.println ("Highest farmer class achieved!");
					break;
				}
				break;
			case 3:
				System.out.println ("Sleeping...");
				currentDay++;
				
				for (int i = 0; i < plotGrid.size (); i++) {
					if (plotGrid.get(i).getCrop() != null)
						plotGrid.get(i).getCrop().grow(currentDay);
				}
					
				
				
				break;
			default:
				break;
			}
		}
		/*** END OF DAILY LIFE UPDATES CODE */
		myObj.close();
	}
}