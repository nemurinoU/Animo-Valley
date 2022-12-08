package tile;

import javax.imageio.ImageIO;

import logic.PlotLand;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.Board;
import main.KeyHandler;
import main.Collision;
import mco1.Farmer;
public class TileManager {
    Board board;
    KeyHandler kh;
    Collision colz;
    Farmer farmer;
    public Tile[] tile;
    public int tileMapID[][];
    logic.PlotGrid tileCopy;

    public TileManager(Board board, KeyHandler kh, Collision colz){
        this.board = board;
        this.kh = kh;
        this.colz = colz;
        tile = new Tile[10]; //number of kinds of tiles
        tileMapID = new int [board.maxScreenCol][board.maxScreenRow];

        getTileImage();
        loadMap("farm_map.txt");
    }

    // This inputs the textfile input into the PlotGrid class
    public logic.PlotGrid updateTileGrid (main.InfoBar menu) {
        logic.PlotGrid tempGrid = menu.getMyFarm().getFarmField();
        logic.PlotLand tempPlot = new PlotLand(false, false, false, 0, 0);

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 10; j++) {
                if (this.tileMapID[j][i] == 3) 
                    tempPlot = new logic.PlotLand(false, true, false, j, i);
                else if (this.tileMapID[j][i] == 0) 
                    tempPlot = new logic.PlotLand(false, false, false, j, i);
                
                tempGrid.addPlot (tempPlot);
            }
        }

        return tempGrid;
    }

    // this updates the local copy of the PlotGrid
    public void updateTileCopy (logic.PlotGrid updatedCopy) {
        this.tileCopy = updatedCopy;
    } 

    public logic.PlotGrid getTileCopy () {
        return this.tileCopy;
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile(); //unplowed
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("tile_images/grass.png"));
            tile[0].isUnplowed = true;

            tile[1] = new Tile(); //plowed
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("tile_images/plowed.png"));
            
            tile[1].isPlowed = true;

            tile[2] = new Tile(); //tree
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("tile_images/tree.png"));
            tile[2].hasCollision = true;

            tile[3] = new Tile(); //rock
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("tile_images/rock.png"));
            tile[3].hasRock = true;

            tile[4] = new Tile(); //watered & plowed
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("tile_images/water_unplowed.png"));
            tile[4].isDry = false;

            tile[5] = new Tile(); //seedling
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("tile_images/seeded.png"));
            tile[5].isSeeded = true;

            tile[6] = new Tile(); //watered, plowed & seeded
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("tile_images/water_seeded.png"));
            tile[6].isDry = false;

            tile[7] = new Tile(); //turnip
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("tile_images/turnip.png"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadMap(String fileName){
        try {
            InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;

            while (col < board.maxScreenCol && row < board.maxScreenRow){
                String line = br.readLine();
                while (col < board.maxScreenCol){
                    String id[] = line.split(" ");
                    int i = Integer.parseInt(id[col]);
                    tileMapID[col][row] = i;
                    col++;
                }
                if (col == board.maxScreenCol){
                    col = 0;
                    row++;
                }

                //rock generator
                for (int i = 10; i < 15; i++){
                    int randX = (int)Math.floor(Math.random()*(10-1+1)+1);
                    int randY = (int)Math.floor(Math.random()*(5-1+1)+1);
                    tileMapID[randX][randY] = 3;
                }
                
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void draw(Graphics2D g2){
        //g2.drawImage(tile[0].image, 0, 0, board.tileSize, board.tileSize, null);

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < board.maxScreenCol && row < board.maxScreenRow){
            int tileID = tileMapID[col][row];
            g2.drawImage(tile[tileID].image, x, y, board.tileSize, board.tileSize, null);
            col++;
            x += board.tileSize;

            if (col == board.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += board.tileSize;
            }
        }

        //Plow tile
        // AHHH GETS
        // I actually don't need to put in the LOGIC here, I can just create a kh object in the logic
        // portion of the MVC :))
        if (tile[tileMapID[colz.x][colz.y]].isUnplowed){
            if (kh.spacePressed == true){
                tileMapID[colz.x][colz.y] = 1;
            }
        }

        // Plant on tile
        if (tile[tileMapID[colz.x][colz.y]].isPlowed){
            if (kh.uPressed == true){
                tileMapID[colz.x][colz.y] = 5;
            }
        }
        

        //Mine rock
        if (tile[tileMapID[colz.x][colz.y]].hasRock){
            if(kh.pPressed == true){
                //mco1.Farmer tempFarmer = this.myfarm.getFarmer();
                tileMapID[colz.x][colz.y] = 0;
                //farmer.setCoins(farmer.getCoins() - 50);
            }
        }

        //If tile is dry and plowed, water it
        if (tile[tileMapID[colz.x][colz.y]].isDry && tile[tileMapID[colz.x][colz.y]].isPlowed){
            if (kh.iPressed == true){
                tileMapID[colz.x][colz.y] = 4;
            }
        }
        
        //If tile is dry and has seed, water it
        if (tile[tileMapID[colz.x][colz.y]].isSeeded){
            if (kh.iPressed == true){
                tileMapID[colz.x][colz.y] = 6;
            }
        }

        if (tile[tileMapID[colz.x][colz.y]].isDry == false){
            if (kh.uPressed == true){
                tileMapID[colz.x][colz.y] = 6;
            }
        }
    }
}