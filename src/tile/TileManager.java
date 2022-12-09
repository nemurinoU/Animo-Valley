package tile;

import javax.imageio.ImageIO;

import logic.Coordinates;
import logic.PlotLand;
import logic.PlotGrid;
import logic.Farmer;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.Board;
import main.KeyHandler;
import main.Collision;
import logic.Farmer;

public class TileManager {
    Board board;
    KeyHandler kh;
    Collision colz;
    Farmer farmer;
    private Tile[] tile;
    private int[][] tileID;
    PlotGrid tileCopy;

    /**
     * This constructor is invoked when a TileManager object is created.
     * @param board The board or view class
     * @param kh The key handler class where key inputs are modified
     * @param colz The collision class that checks for collision on the tiles
     */
    public TileManager(Board board, KeyHandler kh, Collision colz){
        this.board = board;
        this.kh = kh;
        this.colz = colz;
        tile = new Tile[20]; //number of kinds of tiles
        tileID = new int [board.getMaxScreenCol()][board.getMaxScreenRow()];

        getTileImage();
        loadMap("farm_map.txt");
    }

    // This inputs the textfile input into the PlotGrid class
    public logic.PlotGrid updateTileGrid (main.InfoBar menu) {
        logic.PlotGrid tempGrid = menu.getMyFarm().getFarmField();
        logic.PlotLand tempPlot = new PlotLand(false, false, false, 0, 0);

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 10; j++) {
                if (this.tileID[j][i] == 3) 
                    tempPlot = new logic.PlotLand(false, true, false, j, i);
                else if (this.tileID[j][i] == 0) 
                    tempPlot = new logic.PlotLand(false, false, false, j, i);
                
                tempGrid.addPlot (tempPlot);
            }
        }
        

        return tempGrid;
    }

    
    /** 
     * This method updates the tiles for every instance
     * @param updatedCopy The updated copy of the tile
     */
    public void updateTileCopy (logic.PlotGrid updatedCopy) {
        this.tileCopy = updatedCopy;
    } 

    
    /** 
     * @return PlotGrid
     */
    public logic.PlotGrid getTileCopy () {
        return this.tileCopy;
    }

    /**
     * This method is used to get the tile images such as the trees, rocks, crops, and plot lands.
     */
    public void getTileImage(){
        try {
            tile[0] = new Tile(); //unplowed
            tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/grass.png")));
            tile[0].setIsUnplowed(true);

            tile[1] = new Tile(); //plowed
            tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/plowed.png"))); 
            //tile[1].isPlowed = true;
            tile[1].setIsPlowed(true);

            tile[2] = new Tile(); //tree
            tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/tree.png")));
            tile[2].setHasCollision(true);

            tile[3] = new Tile(); //rock
            tile[3].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/rock.png")));
            tile[3].setHasRock(true);

            tile[4] = new Tile(); //watered & plowed
            tile[4].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/water_unplowed.png")));
            tile[4].setIsDry(false);

            tile[5] = new Tile(); //seedling
            tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/seeded.png")));
            tile[5].setIsSeeded(true);

            tile[6] = new Tile(); //watered, plowed & seeded
            tile[6].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/water_seeded.png")));
            tile[6].setIsDry(false);

            tile[7] = new Tile(); //turnip
            tile[7].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/crops/turnip.png")));

            tile[8] = new Tile(); //carrot
            tile[8].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/crops/carrot.png")));

            tile[9] = new Tile(); //potato
            tile[9].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/crops/potato.png")));

            tile[10] = new Tile(); //rose
            tile[10].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/crops/rose.png")));

            tile[11] = new Tile(); //tulip
            tile[11].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/crops/tulip.png")));

            tile[12] = new Tile(); //sunflower
            tile[12].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/crops/sunflower.png")));

            tile[13] = new Tile(); //mango-tree
            tile[13].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/crops/mango-tree.png")));

            tile[14] = new Tile(); //apple-tree
            tile[14].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/crops/apple-tree.png")));

            tile[15] = new Tile(); //dead
            tile[15].setImage(ImageIO.read(getClass().getResourceAsStream("tile_images/crops/dead.png")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    /** 
     * This method is used to load the map automatically by painting the Board with the sprites breadth-first.
     * @param fileName The filename of the map in .txt format
     */
    public void loadMap(String fileName){
        try {
            InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;

            while (col < board.getMaxScreenCol() && row < board.getMaxScreenRow()){
                String line = br.readLine();
                while (col < board.getMaxScreenCol()){
                    String id[] = line.split(" ");
                    int i = Integer.parseInt(id[col]);
                    tileID[col][row] = i;
                    col++;
                }
                if (col == board.getMaxScreenCol()){
                    col = 0;
                    row++;
                }

                //rock generator (randomized)
                for (int i = 10; i < 15; i++){
                    int randX = (int)Math.floor(Math.random()*(10-1+1)+1);
                    int randY = (int)Math.floor(Math.random()*(5-1+1)+1);
                    tileID[randX][randY] = 3;
                }
                
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    /** 
     * This is the draw() method for the tiles.
     * @param g2
     */
    public void draw(Graphics2D g2, boolean success){
        //g2.drawImage(tile[0].image, 0, 0, board.tileSize, board.tileSize, null);

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < board.getMaxScreenCol() && row < board.getMaxScreenRow()){
            int tileIndex = tileID[col][row];
            g2.drawImage(tile[tileIndex].getImage(), x, y, board.getTileSize(), board.getTileSize(), null);
            col++;
            x += board.getTileSize();

            if (col == board.getMaxScreenCol()){
                col = 0;
                x = 0;
                row++;
                y += board.getTileSize();
            }
        }

        Coordinates coords;

        // update the grid to have harvested or withered plants
        for (int i = 0; tileCopy != null && i < tileCopy.getPlotGrid().size(); i++) {
            coords = tileCopy.getPlot(i).getCoords();

            if (tileCopy.getPlot(i) != null && !tileCopy.getPlot(i).getHasRock()) {
                if (tileCopy.getPlot(i).isHarvestable()) { // update plants to proper texture

                    tileID[coords.getX()][coords.getY()] = tileCopy.getPlot(i).getPublicID();
                }
                // gonna have to change this code above to handle all types of plants
                if (tileCopy.getPlot(i).isWithered()) tileID[coords.getX()][coords.getY()] = 15; // update to withered plant
                if (!tileCopy.getPlot(i).getIsOccupied() && !tileCopy.getPlot(i).getIsPlowed()) tileID[coords.getX()][coords.getY()] = 0; // when shoveled
            }

        }

        //Plow tile
        // AHHH GETS
        // I actually don't need to put in the LOGIC here, I can just create a kh object in the logic
        // portion of the MVC :))
        // plow tile
        /*
         * 1 plow
         * 2 water
         * 3 fertilize
         * 4 pickaxe
         * 5 shovel
         * 0 seed
         * space harvest
         */
        coords = new Coordinates (colz.getX(), colz.getY());
        int i = coords.linearize();

        if (i != -1 && success) {
			if (tile[tileID[colz.getX()][colz.getY()]].getIsUnplowed()){
				if (kh.getPlowPressed() == true){
					tileID[colz.getX()][colz.getY()] = 1;
				}
			}
			// Plant on tile
			else if (tile[tileID[colz.getX()][colz.getY()]].getIsPlowed()){
				if (kh.getSeedPressed() == true){
					tileID[colz.getX()][colz.getY()] = 5;
				}
                else if(kh.getShovelPressed()) {
                    tileID[colz.getX()][colz.getY()] = 0;
                }
			}
			//Mine rock
			else if (tile[tileID[colz.getX()][colz.getY()]].getHasRock()){
				if(kh.getPickaxePressed() == true){
					tileID[colz.getX()][colz.getY()] = 0;
				}
			}
            //If tile is dry and has seed, water it
            else if (tile[tileID[colz.getX()][colz.getY()]].getIsSeeded()){
                if (kh.getWaterPressed() == true){
                    tileID[colz.getX()][colz.getY()] = 6;
                }
                else if(kh.getShovelPressed()) {
                    tileID[colz.getX()][colz.getY()] = 0;
                }
            }
            else if (tileCopy.getPlot(i).getCrop() == null ) {
                if (kh.getHarvestPressed()) {
                    tileID[colz.getX()][colz.getY()] = 0;
                }  
            }
		}
		/***        
        //If tile is dry and has seed, water it
        if (tile[tileID[colz.getX()][colz.getY()]].getIsSeeded()){
            if (kh.getWaterPressed() == true){
                tileID[colz.getX()][colz.getY()] = 6;
            }
        }

        //If tile is wet, plant seed
        if (tile[tileID[colz.getX()][colz.getY()]].getIsDry() == false){
            if (kh.getSeedPressed() == true){
                tileID[colz.getX()][colz.getY()] = 6;
            }
        }
		***/
    }

    
    /** 
     * This method gets the tile.
     * @return Tile[]
     */
    public Tile[] getTile() {
        return this.tile;
    }

    
    /** 
     * This method, when called, updates the tile.
     * @param tile
     */
    public void setTile(Tile[] tile) {
        this.tile = tile;
    }

    
    /** 
     * This method gets the tile ID
     * @return int[][] The tile ID in 2D array form [row][col]
     */
    public int[][] getTileID() {
        return this.tileID;
    }

    
    /** 
     * This method, when called, updates the tile ID
     * @param tileMapID The tile ID in 2D array form [row][col]
     */
    public void setTileID(int[][] tileMapID) {
        this.tileID = tileMapID;
    }

}