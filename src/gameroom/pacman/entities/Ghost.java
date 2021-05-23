/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.pacman.entities;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Ghost {
    
    public Collider collider = new Collider();
    Image sprite;
    int xPosition;
    int yPosition;
    
    public Ghost(String _spritePath, int _xSpawnPosition, int _ySpawnPosition){
        this.sprite = new ImageIcon(_spritePath).getImage();
        this.xPosition = _xSpawnPosition;
        this.yPosition = _ySpawnPosition;
        this.collider.newCollider(_xSpawnPosition, _ySpawnPosition, 32, 32);
                
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
