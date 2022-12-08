package main;

import logic.ActionHandler;
import player.Sprite;
import logic.Coordinates;

public class Collision {
    Board board;
    private int x, y;
    
    public Collision(Board board){
        this.board = board;
    }

    
    /** 
     * This method constantly checks for collisions on the map.
     * It works by initializing the boxes around the tree
     * @param s The sprites that collide with each other
     */
    public void checkTile(Sprite s){
        int leftX = s.getX() + s.getSpriteArea().x;
        int rightX = s.getX() + s.getSpriteArea().x + s.getSpriteArea().width;
        int topY = s.getY() + s.getSpriteArea().y;
        int botY = s.getY() + s.getSpriteArea().x + s.getSpriteArea().height;

        int leftCol = leftX / board.tileSize;
        int rightCol = rightX / board.tileSize;
        int topRow = topY / board.tileSize;
        int botRow = botY / board.tileSize;

        int tile1, tile2;

        switch (s.getDirection()){
            case "up":
                topRow = ((s.getY() - s.getSpeed()) / board.tileSize);
                tile1 = board.tileMan.getTileID()[leftCol][topRow];
                tile2 = board.tileMan.getTileID()[rightCol][topRow];
                if (board.tileMan.getTile()[tile1].getHasCollision() == true || 
                board.tileMan.getTile()[tile2].getHasCollision() == true){
                    s.setCollisionOn(true);// = true;
                }
                System.out.println("Tile " + leftCol + ", " + topRow);
                this.x = leftCol;
                this.y = topRow;
                break;
            case "down":
                botRow = ((s.getY() + s.getSpeed()) / board.tileSize) + 1;
                tile1 = board.tileMan.getTileID()[leftCol][botRow];
                tile2 = board.tileMan.getTileID()[rightCol][botRow];
                if (board.tileMan.getTile()[tile1].getHasCollision() == true || 
                board.tileMan.getTile()[tile2].getHasCollision() == true){
                    s.setCollisionOn(true);// = true;
                }
                System.out.println("Tile " + leftCol + ", " + botRow);
                this.x = leftCol;
                this.y = botRow;
                break;
            case "left":
                leftCol = (s.getX() - s.getSpeed()) / board.tileSize;
                tile1 = board.tileMan.getTileID()[leftCol][topRow];
                tile2 = board.tileMan.getTileID()[leftCol][botRow];
                if (board.tileMan.getTile()[tile1].getHasCollision() == true || 
                board.tileMan.getTile()[tile2].getHasCollision() == true){
                    s.setCollisionOn(true);// = true;
                }
                System.out.println("Tile " + leftCol + ", " + botRow);
                this.x = leftCol;
                this.y = botRow;
                break;
            case "right":
                rightCol = ((s.getX() + s.getSpeed()) / board.tileSize) + 1;
                tile1 = board.tileMan.getTileID()[rightCol][topRow];
                tile2 = board.tileMan.getTileID()[rightCol][botRow];
                if (board.tileMan.getTile()[tile1].getHasCollision() == true || 
                board.tileMan.getTile()[tile2].getHasCollision() == true){
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
     * @return Coordinates
     */
    public Coordinates getCoords () {
        return new Coordinates (this.x, this.y);
    }

    
    /** 
     * This method returns the current x variable
     * @return int
     */
    public int getX(){
        return this.x;
    }

    
    /** 
     * This method, when called, updates the x variable
     * @param x
     */
    public void setX(int x){
        this.x = x;
    }

    
    /** 
     * This method returns the current y variable
     * @return int
     */
    public int getY(){
        return this.y;
    }

    
    /** 
     * This method, when called, updates the y variable
     * @param y
     */
    public void setY(int y){
        this.y = y;
    }
}
