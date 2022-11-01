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
	private String farmName;
	private static int currentDay = 0;
	private static boolean gameOver = false;
	private static ArrayList<PlotLand> plotGrid = new ArrayList<PlotLand>();
	
	public static void displayStats(Farmer farmer, double farmerXP, double farmerCoins){
		for (int i = 0; i < 10; i++) 
			System.out.print ("=");
		System.out.println("");
		
		System.out.println (farmer.getName());
		System.out.println("LVL: " + farmer.getLvl());
		System.out.println("EXP: " + farmerXP + " / 100");
		System.out.println("BAL: " + farmerCoins + " coins");
		System.out.println("DAY " + currentDay);
		
		for (int i = 0; i < 10; i++) 
			System.out.print ("=");
		System.out.println("");
	}

	//basically shows commands and then asks for a command, returns it and does thingy
	
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
	}

	public static void seedList(){
		System.out.println("Select seed you want to plant: ");
		System.out.println("[1] Turnip");
	}
	/***
	This code is responsible for displaying the PLOTGRID and their states.
	
	[t] has turnip crop
	[T] fully grown harvestable turnip
	[-] plowed and empty
	[X] rocked
	[_] not plowed
	***/
	public static void displayGrid(ArrayList<PlotLand> plotGrid) {
		for (int i = 0; i < plotGrid.size(); i++) {
			System.out.print ("[ ");
			if (plotGrid.get(i).getIsPlowed() == false){ //not plowed 
				System.out.print ("_");
			} 
			else{
				if (plotGrid.get(i).getIsPlowed() == true){ //plowed
					if (plotGrid.get(i).getIsOccupied() == false) //plowed & not occupied
						System.out.print ("-");
					else //plowed & occupied
						System.out.print ("t");
				} 
				
			}
			
			//else if (plotGrid.get(i).getIsOccupied() == true) 
				
			System.out.println (" ]");
		}
	}
	
	public static void main (String[] args) {
		Scanner myObj = new Scanner(System.in);
		String farmerName;

		/* ASK USER FOR THEIR FARMER NAME */
		System.out.print ("What is your name? :  ");
		Farmer farmer = new Farmer (myObj.nextLine());
		
		System.out.println ("Welcome to Animo Valley, " + farmer.getName() + ".\n");
		/***
		DEV NOTES: This should honestly be in a class of methods OR just subroutines in this file that manages what is displayed on the screen.
		But this is okay for now. 
		 */

		/* ASK USER FOR FARM NAME */
		System.out.print("Input farm name: ");
		String farmName = myObj.nextLine();

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
			Crop crop;
			displayStats(farmer, farmer.getXp(), farmer.getCoins());
			
		
			System.out.println ("What would you like to do? ");
			System.out.println ("1) Go to Tile 2) Shop 3) Sleep the Night ");
			
			switch (myObj.nextInt ()) {
			case 1:
				
				System.out.println ("Goes to tile");
				System.out.println("------------------------");
				displayGrid (plotGrid);
				System.out.println("------------------------");
				if (plotGrid.get(0).getIsPlowed() == false){ //if plot is not plowed
					unplowedCommands();
					switch (myObj.nextInt()){
						case 1:
							//plow tool
							System.out.println("Plot has been plowed!");
							plotGrid.get(0).setIsPlowed(true);
							plotGrid.get(0).setIsOccupied(false);
							break;

						case 2:
							//use pickaxe
							if (plotGrid.get(0).getHasRock() == false)
								System.out.println("There is no rock here.");
							else{
								plotGrid.get(0).setHasRock(false);
								System.out.println("Rock destroyed!");
								farmer.setCoins(farmer.getCoins() - 50);
							}
							break;
						case 3:
							//use shovel
							plotGrid.get(0).setIsOccupied(false);
							plotGrid.get(0).setIsPlowed(false);
							farmer.setCoins(farmer.getCoins() - 7);
							break;
						default:
							System.out.println("Invalid command");
							break;
					}
				
				}
				else if (plotGrid.get(0).getIsPlowed() == true){ //else if plot is plowed
					plowedCommands();
					switch (myObj.nextInt()){
						case 1:
							//watering can
							break;

						case 2:
							//fertilizer
							
							break;

						case 3:
							//use shovel
							plotGrid.get(0).setIsOccupied(false);
							plotGrid.get(0).setIsPlowed(false);
							crop = null; //delete crop object
							farmer.setCoins(farmer.getCoins() - 7);
							break;

						case 4:
							//use seed and create crop object
							seedList();
							switch (myObj.nextInt()){
								case 1:
									crop = new Crop("Turnip", "Root crop", 0, 2, 1, 2, 0, 1, 1, 2, 6, 5, 0); 
									//IDK WHERE TO PUT THIS ^ TBH
									plotGrid.get(0).setIsOccupied(true);
									break;
								default:
									System.out.println("Not available");
									break;
							}
							
						default:
							System.out.println("Invalid command");
							break;
					}
				}
				break;
			/***
			If you don't mind, ako bahala sa pag code ng logic sa case 2 and 3. maybe even case 1.
			@richjpexfromtiktok
			I really can't code at night... my head rlly fkin hurts...
			Pa-update nalang what you've done and don't be afraid to assign tasks to me rich <3
			mwa mwa you're doing great
			 */
			case 2:
				System.out.println ("Welcome to the shop!");
				break;
			case 3:
				System.out.println ("Sleeping...");
				currentDay++;
				//update crops and grids or whatever ikaw na dito ice
				break;
			default:
				break;
			}
			// if go to tile, choose (x, y)
			//		if not plowed, then choose to plow
			//		if plowed, then water or fertilizer
			// if shop, then buy whatever
			// if sleep the night, then update all the grids and crops
		}
		/*** END OF DAILY LIFE UPDATES CODE */
		myObj.close();
	}
}