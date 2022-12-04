package main;

import logic.ActionHandler;
import player.Sprite;
import logic.Coordinates;

public class Collision {
    Board board;
    public int x, y;
    
    public Collision(Board board){
        this.board = board;
    }

    public void checkTile(Sprite s){
        int leftX = s.x + s.spriteArea.x;
        int rightX = s.x + s.spriteArea.x + s.spriteArea.width;
        int topY = s.y + s.spriteArea.y;
        int botY = s.y + s.spriteArea.x + s.spriteArea.height;

        int leftCol = leftX / board.tileSize;
        int rightCol = rightX / board.tileSize;
        int topRow = topY / board.tileSize;
        int botRow = botY / board.tileSize;

        int tile1, tile2;

        switch (s.direction){
            case "up":
                topRow = ((s.y - s.speed) / board.tileSize);
                tile1 = board.tileMan.tileMapID[leftCol][topRow];
                tile2 = board.tileMan.tileMapID[rightCol][topRow];
                if (board.tileMan.tile[tile1].hasCollision == true || 
                board.tileMan.tile[tile2].hasCollision == true){
                    s.collisionOn = true;
                }
                System.out.println("Tile " + leftCol + ", " + topRow);
                this.x = leftCol;
                this.y = topRow;
                break;
            case "down":
                botRow = ((s.y + s.speed) / board.tileSize) + 1;
                tile1 = board.tileMan.tileMapID[leftCol][botRow];
                tile2 = board.tileMan.tileMapID[rightCol][botRow];
                if (board.tileMan.tile[tile1].hasCollision == true || 
                board.tileMan.tile[tile2].hasCollision == true){
                    s.collisionOn = true;
                }
                System.out.println("Tile " + leftCol + ", " + botRow);
                this.x = leftCol;
                this.y = botRow;
                break;
            case "left":
                leftCol = (s.x - s.speed) / board.tileSize;
                tile1 = board.tileMan.tileMapID[leftCol][topRow];
                tile2 = board.tileMan.tileMapID[leftCol][botRow];
                if (board.tileMan.tile[tile1].hasCollision == true || 
                board.tileMan.tile[tile2].hasCollision == true){
                    s.collisionOn = true;
                }
                System.out.println("Tile " + leftCol + ", " + botRow);
                this.x = leftCol;
                this.y = botRow;
                break;
            case "right":
                rightCol = ((s.x + s.speed) / board.tileSize) + 1;
                tile1 = board.tileMan.tileMapID[rightCol][topRow];
                tile2 = board.tileMan.tileMapID[rightCol][botRow];
                if (board.tileMan.tile[tile1].hasCollision == true || 
                board.tileMan.tile[tile2].hasCollision == true){
                    s.collisionOn = true;
                }
                System.out.println("Tile " + rightCol + ", " + botRow);
                this.x = rightCol;
                this.y = botRow;
                break;
        }
    }

    public Coordinates getCoords () {
        return new Coordinates (this.x, this.y);
    }
}
