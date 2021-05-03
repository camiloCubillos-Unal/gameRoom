package gameroom.pacman.entities;

import gameroom.pacman.playerController.PlayerController;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Pacman {

    PlayerController playerController;
    
    Image sprite;
    int xPosition;
    int yPosition;
    int leftCollider;
    int rightCollider;
    int upperCollider;
    int downCollider;
    
    int map[][];
    public int refXPosition;
    public int refYPosition;
    
    int speedX = 0;
    int speedY = 0;
    
    public Pacman(String _spritePath, int _xSpawn, int _ySpawn, PlayerController _playerController, int _map[][]){
        this.sprite = new ImageIcon(_spritePath).getImage();
        this.xPosition = _xSpawn;
        this.yPosition = _ySpawn;
        this.leftCollider = _xSpawn;
        this.rightCollider = _xSpawn + 32;
        this.upperCollider = _ySpawn;
        this.downCollider = _ySpawn + 32;
        this.playerController = _playerController;
        this.map = _map;
        this.refXPosition = _xSpawn + 14;
        this.refYPosition = _ySpawn + 14;
        
    }
    
    // Methods
    
    public void move(){
        
        
        int col = this.getXPosition();
        int row = this.getYPosition();
        
        if((col % 32 == 0) & (row % 32 == 0)){
            
            col = col / 32;
            row = row / 32;
            
            map[row][col] = 2;
            
            if(map[row][col-1] == 1){speedX = 0;}
            if(map[row][col+1] == 1){speedX = 0;}
            if(map[row-1][col] == 1){speedY = 0;}
            if(map[row+1][col] == 1){speedY = 0;}
            
            switch(this.playerController.getKey()){
            case 37:
                if(map[row][col-1] != 1){
                    speedX = -2;
                    speedY = 0;
                    setSprite("src\\media\\img\\Pacman_left.png");
                }else{
                    speedX = 0;
                }
                break;
            case 38:
                if(map[row-1][col] != 1){
                    speedY = -2;
                    speedX = 0;
                    setSprite("src\\media\\img\\Pacman_up.png");
                }else{
                    speedY = 0;
                }
                break;
            case 39:
                if(map[row][col+1] != 1){
                    speedX = 2;
                    speedY = 0;
                    setSprite("src\\media\\img\\Pacman_right.png");
                }else{    
                    speedX = 0;
                    }
                break;
            case 40:
                if(map[row+1][col] != 1){
                    speedY = 2;
                    speedX = 0;                   
                    setSprite("src\\media\\img\\Pacman_down.png");
                }else{
                    speedY = 0;
                }
                break;
            }
            
        }
        System.out.println("Velocidad X: "+speedX);
        System.out.println("Velocidad Y: "+speedY);
        this.setXPosition(this.getXPosition() + speedX);
        this.setYPosition(this.getYPosition() + speedY);
        /*System.out.println("X: "+this.getXPosition());
                System.out.println("col: "+col);
                System.out.println("Y: "+this.getYPosition());
                System.out.println("row: "+row);
                System.out.println(map[row-1][col]);*/
        
        
    }
    
    // Getters
    
    public Image getSprite(){
        return this.sprite;
    }
    
    public int getSpeedX(){
        return this.speedX;
    }
    
    public int getSpeedY(){
        return this.speedY;
    }
    
    public int getXPosition(){
        return this.xPosition;
    }
    
    public int getYPosition(){
        return this.yPosition;
    }
    
    // Setters
    
    public void setXPosition(int _newXPosition){
        this.xPosition = _newXPosition;
    }
    
    public void setYPosition(int _newYPosition){
        this.yPosition = _newYPosition;
    }
    
    public void setSprite(String _spritePath){
        this.sprite = new ImageIcon(_spritePath).getImage();
    }
    
}
