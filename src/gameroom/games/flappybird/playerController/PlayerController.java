/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.games.flappybird.playerController;

import gameroom.games.flappybird.graphics.GraphicController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import gameroom.games.flappybird.audioController.AudioController;
import gameroom.games.flappybird.graphics.MainFrame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * @author ccubi
 */
public abstract class PlayerController implements KeyListener{
    
    private GraphicController graphicController;
    private AudioController birdJump;
    private JFrame mainFrame;
    private String username;

    public PlayerController(GraphicController _graphicController, JFrame _mainFrame) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.graphicController = _graphicController;
        this.username = this.graphicController.username;
        this.birdJump = new AudioController("src\\media\\audio\\sfx\\bird_jump.wav",0.6);
        this.mainFrame = _mainFrame;
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case 32:
                
                if(graphicController.gameOn == false){
                    graphicController.gameOn = true;
                }
                
                if(graphicController.getBird().isAlive()){
                    birdJump.play();
                    graphicController.getBird().setSpeed(-15);
                }
                break;
            case 82:
                if(!graphicController.getBird().isAlive()){
                    graphicController.backgroundMusic.stop();
                    this.mainFrame.dispose();
                    try {
                        MainFrame newGame = new MainFrame(this.username);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
