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
		System.out.println("EXP: " + farmerXP);
		System.out.println("BAL: " + farmerCoins);
		
		System.out.println("DAY " + currentDay);
		
		for (int i = 0; i < 10; i++) 
			System.out.print ("=");
		System.out.println("");
	}

	//basically shows commands and then asks for a command, returns it and does thingy
	public static void commandList(){
		char ch;
		Scanner sc = new Scanner(System.in);

		System.out.println("[S] Select Seed");
		System.out.println("[T] Select tool");

		ch = sc.next().charAt(0);

		if (ch == 'S')
		{
			//display seeds
		}
		else if (ch == 'T'){
			//display tools
		}
	}
	public static void toolList(){
		char ch;
		Scanner sc = new Scanner (System.in);

		
	}
	
	/***
	This code is responsible for displaying the PLOTGRID and their states.
	
	[t] has turnip crop
	[T] fully grown harvestable turnip
	[_] plowed and empty
	[X] rocked
	[NP] not plowed
	***/
	public static void displayGrid(ArrayList<PlotLand> plotGrid) {
		for (int i = 0; i < plotGrid.size(); i++) {
			System.out.print ("[ ");
			if (plotGrid.get(i).getIsPlowed() == false)
				System.out.print ("NP");
			else if (plotGrid.get(i).getIsPlowed() && !plotGrid.get (i).getIsOccupied ())
				System.out.print ("_");
			else if (plotGrid.get(i).getIsOccupied ()) //&& is not harvestable
				System.out.print ("t");
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
			// display grid
			displayGrid (plotGrid);
			
			farmer.updateLvl();
			displayStats(farmer, farmer.getXp(), farmer.getCoins());
			
			System.out.println ("What would you like to do? ");
			System.out.println ("1) Go to Tile 2) Shop 3) Sleep the Night ");
			
			switch (myObj.nextInt ()) {
			case 1:
				System.out.println ("Goes to tile");
				break;
			case 2:
				System.out.println ("Open Shop");
				break;
			case 3:
				System.out.println ("Sleeping...");
				currentDay++;
				//update crops and grids or whatever
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
		
	}
}