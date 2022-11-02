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
 * @author richjpex
 */

public class MyFarm {
	private static String farmName;
	private static int currentDay = 0;
	private static boolean gameOver = false;
	private static ArrayList<PlotLand> plotGrid = new ArrayList<PlotLand>();
	private static ArrayList<Crop> cropBook = new ArrayList<Crop>();
	
	public static void displayStats(Farmer farmer, double farmerXP, double farmerCoins){
		for (int i = 0; i < 17; i++) 
			System.out.print ("=");

		System.out.println ("\n[" + farmName + "]");
		
		System.out.println (farmer.getName() + " - DAY " + currentDay);
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
		System.out.println("[3] Seed - plant seed");
		System.out.println("[4] Shovel - remove plant (7 coins)");
		System.out.println("[5] Harvest - harvest plant ");
		System.out.println("[0] Go back");
		System.out.println("------------------------");
		System.out.print("> ");
	}

	public static void seedList(){
		System.out.println("Select seed you want to plant: ");
		System.out.println("[1] Turnip");
		System.out.println("[0] Go back");
		System.out.println("------------------------");
		System.out.print("> ");

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
					else if (plotGrid.get(i).getCrop().getIsHarvestable()) //plowed & harvestable
						System.out.print ("T");
					else if (plotGrid.get(i).getCrop().getIsWithered ()) //plowed & withered
						System.out.print ("X");
					else if (plotGrid.get(i).getIsOccupied())//plowed & occupied
						System.out.print ("t");
					
				} 
				
			}
			
			//else if (plotGrid.get(i).getIsOccupied() == true) 
				
			System.out.println (" ]");
		}
	}
	/* METHODS FOR MAIN MENU OPTIONS (1, 2, 3) 
	 * goToTile accepts a Farmer and PlotLand parameter
	 * It makes use of the tools and seeds to be planted based on the commands show
	*/
	public static void goToTile (Farmer farmer, PlotLand tempPlot) {
		int nCode;
		Scanner sc = new Scanner(System.in);
		System.out.println ("Goes to tile (0, 0)");
		System.out.println("------------------------");
		displayGrid (plotGrid);
		System.out.println("------------------------");
		if (tempPlot.getIsPlowed() == false){ //if plot is not plowed
			unplowedCommands();
			
			switch (sc.nextInt()){
			case 1: //farmer plows tile (0, 0)
				farmer.plowLand (tempPlot);
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
			case 0:
				
				break;
			default:
				System.out.println("Invalid command!\n");
				break;
			}
		
		}
		else if (tempPlot.getIsPlowed()){ //else if plot is plowed
			
			if (tempPlot.getIsOccupied() == false){
				plowedNoCropCommands();
				switch (sc.nextInt()){
		
					case 2: // farmer uses shovel @ plotGrid (0,0)
						farmer.digOut(tempPlot);
						break;
		
					case 1:
						//display list of seeds and ask user which seed to plant
						seedList();
						
		
						nCode = sc.nextInt();
						
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
							else{
								farmer.plantCrop(tempPlot, cropBook.get(nCode - 1), currentDay);
								System.out.println("~~~ Turnip planted! ~~~\n");
							}
								
							break;
						default:
							System.out.println("More seeds coming soon...");
							break;
						}
						
						break;
					default:
						System.out.println("Invalid command!\n");
						break;
					}
			}
			else {
				plowedWithCropCommands();
				switch (sc.nextInt()){
					case 1: // farmer waters plant @ plotGrid (0,0)
						farmer.waterPlant(tempPlot);
						break;
		
					case 2: // farmer fertilizes plant @ plotGrid (0,0)
						farmer.fertilizePlant (tempPlot);
						break;
		
					case 4: // farmer uses shovel @ plotGrid (0,0)
						farmer.digOut(tempPlot);
						break;
		
					case 3:
						//display list of seeds and ask user which seed to plant
						seedList();
						
		
						nCode = sc.nextInt();
						
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
							else{
								if (tempPlot.getCrop().getIsWithered())
									System.out.println ("Crop is dead. Please use shovel...\n");
								else
									System.out.println ("Crop not ready yet...\n");
							}
								
						break;
					default:
						System.out.println("Invalid command!\n");
						break;
					}
					
			}

		}
		
	}
	
	/* This could definitely be better but I got the logic down for basis */
	public static void registerFarmer (Farmer farmer) {
		Scanner sc = new Scanner (System.in);
		switch (farmer.getFarmerType()) {
			case 0:
				//System.out.print ("Upgrade to "); took this out kasi maging Upgrade to Insufficient Level!
				if (farmer.getLvl () >= 5) {
					System.out.println ("Upgrade to Registered Farmer? [Y/N]");
					System.out.println ("Fee: 200 coins");
					System.out.print("> ");
					
					switch (sc.next().charAt(0)) {
					case 'Y':
					case 'y':
					if (farmer.getCoins() - 200 > 0){
						farmer.setFarmerType(farmer.getFarmerType() + 1);
						farmer.setFarmerTitle(farmer.getFarmerType());
						farmer.setCoins(farmer.getCoins() - 200);
					}
					else System.out.println("Not enough money to upgrade!");
						break;
					case 'N':
					case 'n':
						System.out.println("Registration cancelled.");
						break;
					default:
						System.out.println ("Invalid choice!");
						break;
					}
				}
				else System.out.println ("Insufficient Level!\n");
				break;
				
			case 1:
				if (farmer.getLvl () >= 10) {
					System.out.println ("Upgrade to Distinguished Farmer? [Y/N]");
					System.out.println ("Fee: 300 coins");
					System.out.print("> ");

					switch (sc.next().charAt(0)) {
					case 'Y':
					case 'y':
					if (farmer.getCoins() - 300 > 0){
						farmer.setFarmerType(farmer.getFarmerType() + 1);
						farmer.setFarmerTitle(farmer.getFarmerType());
						farmer.setCoins(farmer.getCoins() - 300);
					}
					else System.out.println("Not enough money to upgrade!");
						break;
					case 'N':
					case 'n':
						System.out.println("Registration cancelled.");
						break;
					default:
						System.out.println ("Invalid choice!");
						break;
					}
				}
				else System.out.println ("Insufficient Level!\n");
				break;
			case 2:
				if (farmer.getLvl () >= 15) {
					System.out.println ("Upgrade to Legendary Farmer? [Y/N]");
					System.out.println ("Fee: 400 coins");
					System.out.print("> ");

					switch (sc.next().charAt(0)) {
					case 'Y':
					case 'y':
					if (farmer.getCoins() - 400 > 0){
						farmer.setFarmerType(farmer.getFarmerType() + 1);
						farmer.setFarmerTitle(farmer.getFarmerType());
						farmer.setCoins(farmer.getCoins() - 400);
					}
					else System.out.println("Not enough money to upgrade!");
						break;
					case 'N':
					case 'n':
						System.out.println("Registration cancelled.");
						break;
					default:
						System.out.println ("Invalid choice!");
						break;
					}
				}
				else System.out.println ("Insufficient Level!\n");
				break;
			default:
				System.out.println ("Highest farmer class achieved!");
				break;
			}
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
		//int nCode; 
		/* yo what is nCode for? i made it a local var nalang sa goToTile method. u can change it
		to accept nCode parameter cuz idk what this is */
		int nCode;
		//generate crop list dictionary (contains only Turnip for MCO1)
		generateCropBook ();
		
		/* ASK USER FOR THEIR FARMER NAME */
		System.out.print ("Enter name: ");
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
			displayStats(farmer, farmer.getXp(), farmer.getCoins());
			
		
			System.out.println ("What would you like to do? ");
			System.out.println ("1) Go to Tile 2) Register 3) Sleep the Night Away");
			System.out.print("> ");
			
			switch (myObj.nextInt ()) {
			case 1:
				tempPlot = plotGrid.get(0);
				goToTile(farmer, tempPlot);
				
				break;
			case 2:
				/*this really needs subroutines for checking 
				ice can u do that nalang or idk if i did it right-rich*/
				
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
}