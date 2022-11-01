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
		System.out.println("Current day: " + currentDay);
		System.out.println("Level: " + farmer.getLvl());
		System.out.println("EXP: " + farmerXP + "/ 100");
		System.out.println("Balance: " + farmerCoins);
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
	
	public static void main (String[] args) {
		Scanner myObj = new Scanner(System.in);
		String farmerName;

		/* ASK USER FOR THEIR FARMER NAME */
		System.out.print ("What is your name? :  ");
		Farmer farmer = new Farmer (myObj.nextLine());
		
		System.out.println ("Welcome to Animo Valley, " + farmer.getName() + ".");
		/***
		DEV NOTES: This should honestly be in a class of methods OR just subroutines in this file that manages what is displayed on the screen.
		But this is okay for now. 
		 */

		/* ASK USER FOR FARM NAME */
		System.out.print("Input farm name: ");
		String farmName = myObj.nextLine();

		System.out.println("Your farm name is : " + farmName);
		
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
		while (gameOver){
			
			System.out.println("Input command: ");
			//pre should we make a commandList() method? just display all commands
			//definitely. cant keep on copypasting "println(1.2.3.)"


			/***
			I imagine the system to look like this.
			Select an action: ()
			 */
			if (plotGrid.get(0).getIsOccupied()){
				//ask if use seed or tool
			}
			
			farmer.updateLvl();
			displayStats(farmer, farmer.getXp(), farmer.getCoins());
			
			
		}
		/*** END OF DAILY LIFE UPDATES CODE */
		
	}
}