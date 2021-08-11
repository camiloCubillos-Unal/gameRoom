package gameroom.games.flappybird.entities;

import gameroom.games.flappybird.audioController.AudioController;
import java.awt.Image;
import javax.swing.ImageIcon;
import gameroom.games.flappybird.entities.Collider;
import gameroom.games.flappybird.graphics.MainFrame;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Bird {
    
    private Image sprite;
    public Collider collider = new Collider();
    private int xSpawn;
    private int ySpawn;
    private int yPosition;
    private int birdSpeed;
    private int spriteHeight = 60;
    private int spriteWidth = 60;
    private boolean alive = true;
    private AudioController deathSound;
    
    
    public Bird(String _spritePath, int _xSpawn, int _ySpawn) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        
        this.sprite = new ImageIcon(_spritePath).getImage();
        this.xSpawn = _xSpawn;
        this.ySpawn = _ySpawn;
        this.yPosition = _ySpawn;
        this.collider.newCollider(xSpawn, ySpawn, 40, 40);
        this.deathSound = new AudioController("src\\media\\Audio\\SFX\\deathSound.wav",1.00);
    }
    
    public void updateCollider(){
        this.collider.updateCollider(xSpawn, getYPosition(), this.spriteWidth, this.spriteHeight);
    }
    
    public int getSpeed(){
        return this.birdSpeed;
    }
    
    public void setSpeed(int _newBirdSpeed){
        this.birdSpeed = _newBirdSpeed;
    }
    
    public void setYPosition(int _newYPosition){
        this.yPosition = _newYPosition;
    }
    
    public int getYPosition(){
        return this.yPosition;
    }
    
    public Image getSprite(){
        return this.sprite;
    }
    
    public int getXSpawn(){
        return this.xSpawn;
    }
    
    public int getYSpawn(){
        return this.ySpawn;
    }
    
    public void moveBird() throws InterruptedException{
        setYPosition(getYPosition() + getSpeed());
        setSpeed(getSpeed() + 2);
        Thread.sleep(37);

    }
    
    public boolean isAlive(){
        if(alive){
            return true;
        }else{
            return false;
        }
    }
    
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    
    public void die(){
        if(isAlive()){
            deathSound.play();
            this.setSpeed(1);
        }
        this.setAlive(false);
    }
}
