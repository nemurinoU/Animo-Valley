package tile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.Board;

public class TileManager {
    Board board;
    public Tile[] tile;
    public int tileMapID[][];

    public TileManager(Board board){
        this.board = board;

        tile = new Tile[6]; //number of kinds of tiles
        tileMapID = new int [board.maxScreenCol][board.maxScreenRow];


        getTileImage();
        loadMap("farm_map.txt");

    }

    public void getTileImage(){
        try {
            tile[0] = new Tile(); //unplowed
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("tile_images/002.png"));
            

            tile[1] = new Tile(); //plowed
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("tile_images/003.png"));

            tile[2] = new Tile(); //tree
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("tile_images/016.png"));
            tile[2].hasCollision = true;

            tile[3] = new Tile(); //rock
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("tile_images/032.png"));
            
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
            }
            br.close();
        } catch (Exception e) {
            System.out.println("krazy");
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
        
    }
}
