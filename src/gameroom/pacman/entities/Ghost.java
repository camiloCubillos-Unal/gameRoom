/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.pacman.entities;

import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;

public class Ghost {
    
    public Collider collider = new Collider();
    Image sprite;
    int xPosition;
    int yPosition;
    int speedX;
    int speedY;
    List<String> movements = new ArrayList<String>();
    
    public Ghost(String _spritePath, int _xSpawnPosition, int _ySpawnPosition){
        this.sprite = new ImageIcon(_spritePath).getImage();
        this.xPosition = _xSpawnPosition;
        this.yPosition = _ySpawnPosition;
        this.collider.newCollider(_xSpawnPosition, _ySpawnPosition, 32, 32);
    }    
    
    private boolean rayTraceBrick(int[][] map,int[] init, int[] objective,String axis){
        
        // Retorna true si encuentra algun ladrillo.
        
        boolean brickEncounter = false;
        
        switch(axis){
            
            case "horizontal":

                for (int i = init[0] / 32; i <= objective[0] / 32 + 1; i++) {
                    if(map[init[1] / 32][i] == 1){
                        System.out.println(String.format("Ladrillo en: [ %s , %s ]", i , init[1] / 32));
                        brickEncounter = true;
                    }else{
                        System.out.println(String.format("No hay ladrillo en: [ %s , %s ]", i, init[1] / 32));
                    }
                }
                break;
                
                
            // REVISAR LA PARTE VERTICAL DE LA DETECCIÓN DE LADRILLOS
                
            case "vertical":
                
                int distanceY = Math.abs(objective[1] / 32 - init[1] / 32);
                
                System.out.println(init[1]/32);
                System.out.println(distanceY);
                
                for (int i = init[1] / 32; i <= objective[1] / 32; i++) {
                    
                    if(map[init[0] / 32][i] == 1){
                        System.out.println(String.format("Ladrillo en: [ %s , %s ]", init[1] / 32 , i));
                        brickEncounter = true;
                    }else{
                        System.out.println(String.format("No hay ladrillo en: [ %s , %s ]",init[1] / 32 , i));
                    }
                }
                
                break;
        }
        
        return brickEncounter;
    }
    
    private List<String> findPath(int[][] map,int _xInit,int _yInit, int _xObjective, int _yObjective){
        return null;
    }
    
    public void move(int[][] map){
        
        if(this.xPosition % 32 == 0 & this.yPosition % 32 == 0) {
            
            int[] positionCoordenates = {this.xPosition, this.yPosition};
            int[] objectiveCoordenates = {32*1,32*9};
            
            rayTraceBrick(map, positionCoordenates, objectiveCoordenates, "vertical");
            
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
