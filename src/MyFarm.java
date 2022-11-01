import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;

/***
 * <h1>MyFarm</h1>
 * 
 * This class emulates the entire farm, it is NOT the controller class.
 * But for simplicity's sake in PHASE 1, this shall be the controller class.
 * @author icesw
 *
 */

public class MyFarm {
	private String farmName;
	private int currentDay = 0;
	private boolean gameOver = false;
	private ArrayList<PlotLand> plotGrid = new ArrayList<PlotLand>();

	public static void main (String[] args) {
		Scanner myObj = new Scanner(System.in);
		System.out.print ("What is your name? :  ");
		
		Farmer farmer = new Farmer (myObj.nextLine());
		System.out.println ("Welcome to Animo Valley, " + farmer.getName() + ".");
		this.farmName = myObj.nextLine();
		
		while (this.gameOver){
			
			
		}
		
	}
}