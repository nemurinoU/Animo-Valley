package logic;

import main.Board;
import player.Sprite;
/***
 * <p>
 * This class is used to check for collision between two different sprites.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-12-10
 */
public class Collision {
    Board board;
    private int x, y;
    
    /**
     * This constructor accepts a board and uses its coordinates to test for collisions
     * @param board The board that contains the needed objects and methods
     */
    public Collision(Board board){
        this.board = board;
    }

    
    /** 
     * This method constantly checks for collisions on the map.
     * <p>
     * It works by initializing the boxes around the tree
     * @param s The sprites that collide with each other
     */
    public void checkTile(Sprite s){
        int leftX = s.getX() + s.getSpriteArea().x;
        int rightX = s.getX() + s.getSpriteArea().x + s.getSpriteArea().width;
        int topY = s.getY() + s.getSpriteArea().y;
        int botY = s.getY() + s.getSpriteArea().x + s.getSpriteArea().height;

        int leftCol = leftX / board.getTileSize();
        int rightCol = rightX / board.getTileSize();
        int topRow = topY / board.getTileSize();
        int botRow = botY / board.getTileSize();

        //Creating the two tiles that will collide (player and tree)
        int tile1, tile2;

        //If the player's direction is equal to the string,
        switch (s.getDirection()){
            case "up":
                topRow = ((s.getY() - s.getSpeed()) / board.getTileSize());
                tile1 = board.getTileMan().getTileID()[leftCol][topRow];
                tile2 = board.getTileMan().getTileID()[rightCol][topRow];
                if (board.getTileMan().getTile()[tile1].getHasCollision() == true || 
                board.getTileMan().getTile()[tile2].getHasCollision() == true){
                    s.setCollisionOn(true);
                }
                System.out.println("Tile " + leftCol + ", " + topRow);
                this.x = leftCol;
                this.y = topRow;
                break;
            case "down":
                botRow = ((s.getY() + s.getSpeed()) / board.getTileSize()) + 1;
                tile1 = board.getTileMan().getTileID()[leftCol][botRow];
                tile2 = board.getTileMan().getTileID()[rightCol][botRow];
                if (board.getTileMan().getTile()[tile1].getHasCollision() == true || 
                board.getTileMan().getTile()[tile2].getHasCollision() == true){
                    s.setCollisionOn(true);// = true;
                }
                System.out.println("Tile " + leftCol + ", " + botRow);
                this.x = leftCol;
                this.y = botRow;
                break;
            case "left":
                leftCol = (s.getX() - s.getSpeed()) / board.getTileSize();
                tile1 = board.getTileMan().getTileID()[leftCol][topRow];
                tile2 = board.getTileMan().getTileID()[leftCol][botRow];
                if (board.getTileMan().getTile()[tile1].getHasCollision() == true || 
                board.getTileMan().getTile()[tile2].getHasCollision() == true){
                    s.setCollisionOn(true);// = true;
                }
                System.out.println("Tile " + leftCol + ", " + botRow);
                this.x = leftCol;
                this.y = botRow;
                break;
            case "right":
                rightCol = ((s.getX() + s.getSpeed()) / board.getTileSize()) + 1;
                tile1 = board.getTileMan().getTileID()[rightCol][topRow];
                tile2 = board.getTileMan().getTileID()[rightCol][botRow];
                if (board.getTileMan().getTile()[tile1].getHasCollision() == true || 
                board.getTileMan().getTile()[tile2].getHasCollision() == true){
                    s.setCollisionOn(true); //= true;
                }
                System.out.println("Tile " + rightCol + ", " + botRow);
                this.x = rightCol;
                this.y = botRow;
                break;
        }
    }

    
    /** 
     * This method returns the current coordinates
     * @return Coordinates the coordinates of the sprite
     */
    public Coordinates getCoords () {
        return new Coordinates (this.x, this.y);
    }

    
    /** 
     * This method returns the current x variable
     * @return int The X value of the collision
     */
    public int getX(){
        return this.x;
    }

    
    /** 
     * This method, when called, updates the x variable
     * @param x The new X value
     */
    public void setX(int x){
        this.x = x;
    }

    
    /** 
     * This method returns the current y variable
     * @return int The Y value of the collision
     */
    public int getY(){
        return this.y;
    }

    
    /** 
     * This method, when called, updates the y variable
     * @param y The new Y value
     */
    public void setY(int y){
        this.y = y;
    }
}
