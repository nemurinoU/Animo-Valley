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
    int currentSeed;
    ArrayList<Crop> tempCrops;
    Crop tempCrop;

	/**
     * This constructor method creates a new instance of ActionHandler
	 *
     */
    public ActionHandler (KeyHandler kh) {
        this.kh = kh;
        this.currentXY = new Coordinates(0, 0);
        this.currentSeed = 0;
       
        tempCrop = new Turnip();
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
    public boolean seedChoiceLogic (PlotLand tempPlot, Farmer farmer, int currentDay, Crop crop, ArrayList<String> tempMsgs) {
            // farmer does the planting
            // farmer USES plotGrid given coordinate reference
            // farmer USES cropBook to copy TURNIP info and put into plot
            boolean success = false;

            if (tempPlot.getIsOccupied()) {
                System.out.println ("You cannot plant on an occupied tile!\n");
                tempMsgs.add("You cannot plant on an occupied tile!\n");
            }
            else{
                success = farmer.plantCrop(tempPlot, crop, currentDay);
                tempMsgs.add("~~~ Crop planted! ~~~\n");
                alertMessage ("~~~ Crop planted! ~~~" + tempPlot.getX() + "," + tempPlot.getY());
            }

            return success;
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

    public boolean updateLogic (InfoBar menu) {
        PlotLand tempPlot;
        Farmer farmer = menu.getMyFarm().getFarmer();
        int i = this.currentXY.linearize();
        boolean success = true;
        ArrayList<String> tempMsgs = new ArrayList<String>();
        String tempMsgFinal = "", cropInfo = "";

        tempCrops = new ArrayList<Crop>();
        
        tempCrops.add (new Turnip());
        tempCrops.add (new Carrot());
        tempCrops.add (new Potato());
        tempCrops.add (new Rose());
        tempCrops.add (new Tulip());
        tempCrops.add (new Sunflower());
        tempCrops.add (new Mango());
        tempCrops.add (new Apple());
        
        
        if (kh.getNextSeed() && !kh.getIsHeld()) {
            kh.setIsHeld(true);
            if (currentSeed < tempCrops.size() - 1) currentSeed++;
            tempCrop = tempCrops.get(this.currentSeed);

            System.out.println("next");
            cropInfo += tempCrop.getCropName() + " (" + tempCrop.getSeedCost() + " coins)";

            menu.updateFeedback(cropInfo);
        }
        else if (kh.getPrevSeed() && !kh.getIsHeld()) {
            kh.setIsHeld(true);
            if (currentSeed != 0) currentSeed--;
            tempCrop = tempCrops.get(this.currentSeed);

            System.out.println("prev");
            cropInfo += tempCrop.getCropName() + " (" + tempCrop.getSeedCost() + " coins)";

            menu.updateFeedback(cropInfo);
        }

        if (i != -1) {
            // get the plot the player is looking at

            tempPlot = menu.getMyFarm().getFarmField().getPlot(i);
            /***
             * KEYBOARD CONTROLS
             * P - pickaxe
             * SPACE - plow
             * U - plant seed
             * I - water plant
             */
            cropInfo += tempCrop.getCropName() + " (" + tempCrop.getSeedCost() + " coins)";
            if ( tempPlot.getHasRock()) { // has rock
                    if(kh.getPickaxePressed() == true){ //when tile gets picked
                        //System.out.println("OMG! TILE PICKED!!");
                        
                        success = farmer.mineRock (tempPlot);

                        if (!success) tempMsgFinal = "~~~ Not Enough Money! ~~~";
                        else {
                            tempMsgs.add ("~~~ Rock Mined! ~~~");
                            tempMsgs.add ("Gained xp: 15");
                            tempMsgs.add ("You paid 50 coins.");
                            tempMsgFinal = concatMsg (tempMsgs);
                        }

                        menu.updateFeedback(cropInfo, tempMsgFinal);
                        
                    }
            }
            else if ( !tempPlot.getIsPlowed()){ // When plot is NOT plowed
                    if (kh.getPlowPressed() == true){ // plow
                        System.out.println("OMG! TILE PLOWED!! " + this.currentXY.getX() + 
                        ", " + this.currentXY.getY());

                        farmer.plowLand(tempPlot);
                        success = true;

                        if (!success) tempMsgFinal = "~~~ Not Enough Money! ~~~";
                        else {
                            tempMsgs.add ("~~~ Plot has been plowed! ~~~");
                            tempMsgs.add ("Gained xp: 0.5");

                            tempMsgFinal = concatMsg (tempMsgs);
                        }
                        menu.updateFeedback(cropInfo, tempMsgFinal);
                    }
            }
            else if ( tempPlot.getIsPlowed()) { // is plowed
                    if ( !tempPlot.getIsOccupied()){ // When plowed plot is NOT occupied (i.e., no crop on it)
                        if (kh.getSeedPressed()) { // plant seed
                            System.out.println("CROP TYPE: " + tempCrop.getCropType());
                            if (tempCrop.getCropType() == 3) // if tree, check for corner cases
                                success = treeCheck (menu.getMyFarm().getFarmField(), tempCrop);
                            
                            if (success) {// if allowed to plant do this
                                success = seedChoiceLogic (tempPlot, farmer, menu.getMyFarm().getCurrentDay(), tempCrop, tempMsgs);

                                if (!success) tempMsgFinal = "~~~ Not Enough Money! ~~~";
                                else tempMsgFinal = concatMsg (tempMsgs);
                            }
                            else tempMsgFinal = "~~~ Not allowed to plant tree ~~~";

                            menu.updateFeedback(cropInfo, tempMsgFinal);
                        }
                        else if (kh.getShovelPressed()) { //shoveling
                            success = farmer.digOut(tempPlot);

                            if (!success) tempMsgFinal = "~~~ Not Enough Money! ~~~";
                            else {
                                tempMsgs.add ("~~~ Shovel used! ~~~");
                                tempMsgs.add ("Gained xp: 2");
                                tempMsgs.add ("You paid 7 coins.");

                                tempMsgFinal = concatMsg (tempMsgs);
                            }

                            menu.updateFeedback(cropInfo, tempMsgFinal);
                        }
                    }
                    else { //When plowed plot IS occupied, actions are: water, fertilize, shovel
                        if (kh.getWaterPressed() && !kh.getIsHeld()) { //watering
                           kh.setIsHeld(true);
                           farmer.waterPlant (tempPlot);
                           success = true;
                           
                           if (!success) tempMsgFinal = "~~~ Not Enough Money! ~~~";
                           else {
                                tempMsgs.add ("~~~ Watering can used! ~~~");
                                tempMsgs.add ("Gained xp: 0.5");
                                tempMsgs.add ("Water Needs: " + tempPlot.getCrop().getTimesWatered() + "/" + tempPlot.getCrop().getWaterNeed() + "(" +
                                tempPlot.getCrop().getWaterBonus() + ")");

                                tempMsgFinal = concatMsg (tempMsgs);
                           }

                           menu.updateFeedback(cropInfo, tempMsgFinal);
                        }
                        else if (kh.getFertilizerPressed() && !kh.getIsHeld()) { //fertilizing
                            kh.setIsHeld(true);
                            success = farmer.fertilizePlant (tempPlot);
                            
                            if (!success) tempMsgFinal = "~~~ Not Enough Money! ~~~";
                            else {
                                tempMsgs.add( "~~~ Fertilizer used! ~~~");
                                tempMsgs.add ("Gained xp: 4");
                                tempMsgs.add ("Fert Needs: " + tempPlot.getCrop().getTimesFertilized() + "/" + tempPlot.getCrop().getFertilizerNeed() + "(" +
                                    tempPlot.getCrop().getFertilizerBonus() + ")");

                                tempMsgFinal = concatMsg(tempMsgs);
                            }
                            
                            
                            menu.updateFeedback(cropInfo, tempMsgFinal);
                        }
                        else if (kh.getShovelPressed()) { //shoveling
                            success = farmer.digOut(tempPlot);

                            if (!success) tempMsgFinal = "~~~ Not Enough Money! ~~~";
                            else {
                                tempMsgs.add ("~~~ Shovel used! ~~~");
                                tempMsgs.add ("Gained xp: 2");
                                tempMsgs.add ("You paid 7 coins.");

                                tempMsgFinal = concatMsg (tempMsgs);
                            }

                            menu.updateFeedback(cropInfo, tempMsgFinal);
                        }

                        if (tempPlot.getCrop() !=null && tempPlot.getCrop().getIsHarvestable()) { // harvesting the plant
                            if (kh.getHarvestPressed()) {
                                farmer.harvestCrop(tempPlot, tempMsgs);

                                tempMsgFinal = concatMsg (tempMsgs);

                                menu.updateFeedback(cropInfo , tempMsgFinal);
                            }
                        }
                        else {
                            if (kh.getHarvestPressed()) {
                                if (tempPlot.getCrop().getIsWithered()) {
                                    alertMessage ("Crop is dead. Please use shovel...");
                                    tempMsgFinal = "Crop is dead. Please use shovel...";
                                }
                                else{
                                    int readyInDays;
                                    readyInDays = tempPlot.getCrop().getHarvestTime() - (menu.getMyFarm().getCurrentDay()  - tempPlot.getCrop().getDayPlanted());
                                    alertMessage ("Crop not ready yet... Ready in " + readyInDays + " days");
                                    tempMsgFinal = "Crop not ready yet... Ready in " + readyInDays + " days";    

                                    menu.updateFeedback(cropInfo, tempMsgFinal);
                                }
                            }
                        }
                    }
            }
            
        }

        return success;
    }

    public void updateMyFarm (MyFarm myfarm) {
        this.farm = myfarm;
    }

    public String concatMsg (ArrayList<String> tempMsgs) {
        String rString = "";

        rString = "<html>";

        for (int i = 0; i < tempMsgs.size(); i++) 
            rString += tempMsgs.get(i) + "<br/>";

        rString += "</html>";

        return rString;
    }

    public boolean treeCheck (PlotGrid tempGrid, Crop tempCrop) {
        boolean rCheck = true;
        PlotLand tempPlot;
        int x = this.currentXY.getX(), y = this.currentXY.getY();
        int ind;

        // when x is 1 or 10
        // when y is 1 or 5
        if (x == 1 || 
            x == 10 ||
            y == 1 ||
            y == 5) {
                
            System.out.println ("CORNER CASE!");
            rCheck = false;
        }
        else {
            System.out.println ("NOT CORNER CASE!");

            
            // check top
            for (int i = -1; i < 2; i++) {
                ind = new Coordinates(x + i, y - 1).linearize();
                tempPlot = tempGrid.getPlot(ind);

                if (tempPlot.getIsOccupied() || tempPlot.isWithered() || tempPlot.getHasRock()) rCheck = false;
            }

            // check sides
            for (int i = -1; i < 2; i++) {
                ind = new Coordinates(x + i, y).linearize();
                tempPlot = tempGrid.getPlot(ind);

                if (i != 0)
                    if (tempPlot.getIsOccupied() || tempPlot.isWithered() || tempPlot.getHasRock()) rCheck = false;
            }
            
            // check bottom
            for (int i = -1; i < 2; i++) {
                ind = new Coordinates(x + i, y + 1).linearize();
                tempPlot = tempGrid.getPlot(ind);

                if (tempPlot.getIsOccupied() || tempPlot.isWithered() || tempPlot.getHasRock()) rCheck = false;
            }
        }

        return rCheck;
    }
}