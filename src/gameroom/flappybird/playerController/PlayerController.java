/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.flappybird.playerController;

import gameroom.flappybird.graphics.GraphicController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import gameroom.flappybird.audioController.AudioController;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author ccubi
 */
public class PlayerController implements KeyListener{
    
    private GraphicController graphicController;
    private AudioController birdJump;

    public PlayerController(GraphicController _graphicController) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.graphicController = _graphicController;
        this.birdJump = new AudioController("src\\media\\audio\\sfx\\bird_jump.wav",0.6);
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case 32:
                if(graphicController.getBird().isAlive()){
                    birdJump.play();
                    graphicController.getBird().setSpeed(-15);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
