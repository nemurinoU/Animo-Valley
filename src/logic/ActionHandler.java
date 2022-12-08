package logic;

import java.util.ArrayList;
import main.KeyHandler;
import main.InfoBar;

/**
 * <h1> ActionHandler </h1>
 * <p>
 * This class stores a variety of methods to process various actions in the program flow.
 * Less of an object for a concept, more of a class for logic subroutines.
 * Used to process actions done by a farmer object.
 * Also used for error messages, and choice logic.
 * </p>

 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-07 
 */
public class ActionHandler {
    KeyHandler kh;
    Coordinates currentXY;
    MyFarm farm;

	/**
     * This constructor method creates a new instance of ActionHandler
	 *
     */
    public ActionHandler (KeyHandler kh) {
        this.kh = kh;
        this.currentXY = new Coordinates(0, 0);
    }

    /**
     * This method handles the logic when a farmer interacts with an unplowed plot
	 *
     * @param nCode		user selection code
	 * @param tempPlot	the plot being interacted with
	 * @param farmer		the player object interacting
	 *
     */
    public void plotIsRawLogic (int nCode, PlotLand tempPlot, logic.Farmer farmer) {
        
        switch (nCode){
        case 1: //farmer plows tile (0, 0)
                farmer.plowLand (tempPlot);
                break;
        case 2: //use pickaxe
           
            if (!tempPlot.getHasRock())
                    errorMessage ("There is no rock here...\n");
            else {
                    farmer.mineRock(tempPlot);
                    System.out.println("Rock destroyed!\n");
            }
            
            break;
        case 3: //use shovel
                farmer.digOut (tempPlot);
                break;
        case 0: //exit menu
                break;
        default:
                errorMessage ("Invalid command!");
                break;
        }
    }

	/**
     * This method handles the logic when a farmer interacts with a plowed plot
	 *
     * @param nCode			user selection code
	 * @param tempPlot		the plot being interacted with
	 * @param farmer			the player object interacting
	 * @param currentDay		the current day in the game world
	 * @param cropBook		holds a "database" of crops programmed in the game
	 *
     */
    public void plotIsPlowedLogic (int nCode, PlotLand tempPlot, logic.Farmer farmer, int currentDay, ArrayList<Crop> cropBook){
        int nSeedCode;

        switch (nCode){
        case 2: // farmer uses shovel @ plotGrid (0,0)
                farmer.digOut(tempPlot);
                break;
        case 1: // when farmer chooses to plant a seed
                //display list of seeds and ask user which seed to plant

                //seedChoiceLogic (nSeedCode, tempPlot, farmer, currentDay, cropBook);

                break;
        default:
                errorMessage ("Invalid command!");
                break;
        }
    }

	/**
     * This method handles the logic when a farmer plants a seed into a plot
	 *
     * @param nCode		    user selection code
	 * @param tempPlot		the plot being interacted with
	 * @param farmer			the player object interacting
	 * @param currentDay		the current day in the game world
	 * @param cropBook		holds a "database" of crops programmed in the game
	 *
     */
    public void seedChoiceLogic (int nCode, PlotLand tempPlot, logic.Farmer farmer, int currentDay, ArrayList<Crop> cropBook) {
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
                        alertMessage ("~~~ Turnip planted! ~~~");
                    }
                        
                    break;
            default:
                    errorMessage ("Seed not found!");
                    break;
            }
    }
	
	/**
    * This method handles the logic when a farmer interacts with a plot with a plant
	*
    * @param nCode			user selection code
	* @param tempPlot		the plot being interacted with
	* @param farmer			the player object interacting
	* @param currentDay		the current day in the game world
	*
    */
    public void plotIsCroppedLogic (int nCode, PlotLand tempPlot, Farmer farmer, int currentDay) {
        Crop tempCrop;
        int readyInDays;
        tempCrop = tempPlot.getCrop();

        switch (nCode){
        case 1: // farmer waters plant @ plotGrid (0,0)
                farmer.waterPlant(tempPlot);
                break;

        case 2: // farmer fertilizes plant @ plotGrid (0,0)
                farmer.fertilizePlant (tempPlot);
                break;

        case 3: // farmer uses shovel @ plotGrid (0,0)
                farmer.digOut(tempPlot);
                break;

        case 4: // harvesting the plant :)))
                if (tempPlot.getCrop().getIsHarvestable())
                    farmer.harvestCrop(tempPlot);
                else{
                    if (tempPlot.getCrop().getIsWithered())
                        alertMessage ("Crop is dead. Please use shovel...");
                    else{
                        readyInDays = tempCrop.getHarvestTime() - (currentDay  - tempCrop.getDayPlanted());
                        alertMessage ("Crop not ready yet... Ready in " + readyInDays + " days");
                        
                    }
                        
                }       
                        
                break;
        case 0: // back 
                break;
        default:
            errorMessage ("Invalid command!");
            break;
        }
    }

	/**
    * This method handles the logic when a user is prompted with Y/N questions
	*
    * @param cCode			user selection code
	* @return int			if yes 1, no 0, else -1
	*
    */
    public int isValidYN (char cCode) {
            int rCode = -1;

            switch (cCode) {
            case 'Y':
            case 'y':
                    rCode = 1;
                    break;
            case 'N':
            case 'n':
                    rCode = 0;
                    break;
            default:
                    break;
            }
            
            return rCode;
    }

	/**
    * This method shows an error message for readability and uniformity
	*
    * @param e		error message to show
	*
    */
    public void errorMessage (String e) {
            System.out.println (e + "\n");
    }

	/**
    * This method shows an alert message for readability and uniformity
	*
    * @param a		alert message to show
	*
    */
    public void alertMessage (String a) {
            System.out.println ("!!*** " + a + " ***!!\n");
    }

    public void updateLocation (Coordinates coords) {
        this.currentXY = coords;
    }

    public Coordinates getCurrentXY () {
        return this.currentXY;
    }

    public void updateLogic (InfoBar menu) {
        PlotLand tempPlot;
        Farmer farmer = menu.getMyFarm().getFarmer();
        int i = this.currentXY.linearize();

        if (i != -1) {
            // get the plot the player is looking at
            tempPlot = menu.getMyFarm().getFarmField().getPlot(i);

            if ( tempPlot.getHasRock()) { // has rock
                    if(kh.getPickaxePressed() == true){ //when tile gets picked
                        System.out.println("OMG! TILE PICKED!!");
                        
                        farmer.mineRock (tempPlot);
                    }
            }
            else if ( !tempPlot.getIsPlowed()){ // When plot is NOT plowed
                    if (kh.getPlowPressed() == true){
                        System.out.println("OMG! TILE PLOWED!! " + this.currentXY.getX() + 
                        ", " + this.currentXY.getY());

                        farmer.plowLand(tempPlot);
                    }
            }

            if(kh.getPickaxePressed() == true){
                System.out.println("OMG! TILE PICKED!!");
                //mco1.Farmer tempFarmer = this.myfarm.getFarmer();
                //farmer.setCoins(farmer.getCoins() - 50);
            }
            // update info when a tile is plowed
        }
    }

    public void updateMyFarm (MyFarm myfarm) {
        this.farm = myfarm;
    }

    
}