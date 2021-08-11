/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.games.pacman.entities;

import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;

public class Ghost {
    
    public Collider collider = new Collider();
    Image sprite;
    int xPosition;
    int xSpawn;
    int yPosition;
    int ySpawn;
    int speedX;
    int speedY;
    List<String> movements = new ArrayList<String>();
    int previousDirection;
    
    boolean moving = false;
    
    public Ghost(String _spritePath, int _xSpawnPosition, int _ySpawnPosition){
        this.sprite = new ImageIcon(_spritePath).getImage();
        this.xPosition = _xSpawnPosition;
        this.xSpawn = _xSpawnPosition;
        this.yPosition = _ySpawnPosition;
        this.ySpawn = _ySpawnPosition;
        this.collider.newCollider(_xSpawnPosition, _ySpawnPosition, 32, 32);
        previousDirection = 1; //Valor inicial. No correspoonde a ninguna dirección.
    }    
    
    private boolean checkMovesavailability(){   
        System.out.println("[!] Revisando disponibilidad de movimientos");
        for(int i = 0; i < this.movements.size(); i++){
            if(this.movements.get(i) != "-"){
                return true;
            }
        }
        return false;
    }
    
    private void printVector(List<String> _vector){
        for(int i = 0; i < _vector.size();i++){
           System.out.println(_vector.get(i));
        }
    }
    
    private List<String> countSteps(int[][] _map, int _initX, int _initY){

        /*
            GENERAR DIRECCIÓN RANDOM
            1 - ARRIBA
            2 - DERECHA
            -1 - ABAJO
            -2 - IZQUIERDA
        */
        
        int direction = ThreadLocalRandom.current().nextInt(-2,3);
        System.out.println("Direccion: "+direction);
        int xCell = _initX / 32;
        int yCell = _initY / 32;
        List<String> newMovements = new ArrayList<String>();
        
        if(direction != (this.previousDirection * -1)){
        
            switch(direction){
                case 1:
                    while(_map[yCell - 1][xCell] != 1){
                        newMovements.add("u");
                        yCell--;
                    }
                    break;
                
                case 2:
                    while(_map[yCell][xCell + 1] != 1){
                        System.out.println("Block type: "+_map[yCell][xCell + 1]);
                        newMovements.add("r");
                        xCell++;
                    }
                    break;
                
                case -1:
                    while(_map[yCell + 1][xCell] != 1){
                        newMovements.add("d");
                        yCell++;
                    }
                    break;
                
                case -2:
                    while(_map[yCell][xCell - 1] != 1){
                        newMovements.add("l");
                        xCell--;
                    }
                    break;
            
                case 0:
                    break;
            }
            System.out.println("MOVEMENTS SIZE: " + newMovements.size());
            return newMovements;
        }
        
        this.previousDirection = direction;
        return newMovements;
        
    }
    
    private List<String> findPath(int[][] map,int _xInit,int _yInit, int _xObjective, int _yObjective){
        return null;
    }
    
    public void move(int[][] map){
        

        
        if(this.xPosition % 32 == 0 & this.yPosition % 32 == 0) {
        //System.out.println("X: " + this.xPosition + " " + this.xPosition / 32);
        //System.out.println("Y: " +this.yPosition + " " + this.yPosition / 32);
        printVector(movements);
        
        if(!moving){
            this.movements = countSteps(map, this.xPosition, this.yPosition);
            if(this.movements.size() > 0){
                moving = true;
            }
        }
        
        if(!checkMovesavailability()){
            moving = false;
        }           
            
            for (int i = 0; i < movements.size(); i++) { 
                if(movements.get(i) != "-"){
                    switch(movements.get(i)){
                        case "r":
                            this.speedX = 1;
                            this.speedY = 0;
                            break;
                        case "d":
                            this.speedX = 0;
                            this.speedY = 1;
                            break;
                        case "l":
                            this.speedX = -1;
                            this.speedY = 0;
                            break;
                        case "u":
                            this.speedX = 0;
                            this.speedY = -1;
                            break;
                    }
                    movements.set(i, "-");
                    
                    break;
                }else{
                    this.speedX = 0;
                    this.speedY = 0;
                }
            }
        }
        
        this.xPosition += speedX;
        this.yPosition += speedY;
        this.collider.updateCollider(this.xPosition, this.yPosition, 32, 32);        
        
    }
    
    // Getters
    
    public Image getSprite(){
        return this.sprite;
    }
    
    public int getXPosition(){
        return this.xPosition;
    }
    
    public int getYPosition(){
        return this.yPosition;
    }
}
