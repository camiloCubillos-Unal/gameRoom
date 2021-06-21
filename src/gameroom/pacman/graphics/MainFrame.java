/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.pacman.graphics;

import gameroom.pacman.audioController.AudioController;
import gameroom.pacman.graphics.GraphicController;
import gameroom.pacman.playerController.PlayerController;
import gameroom.pacman.testPackage.GraphicControllerTest;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * @author ccubi
 */
public class MainFrame extends JFrame {
    
    GraphicController graphicController;
    //GraphicControllerTest graphicControllerTest;
    PlayerController playerController;
    AudioController pacmanSFX;
    
    public MainFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.playerController = new PlayerController();
        this.graphicController = new GraphicController(playerController);
        //this.graphicControllerTest = new GraphicControllerTest(playerController);
        this.pacmanSFX = new AudioController("src\\media\\Audio\\SFX\\waka-waka.wav", 0.30);
        this.addKeyListener(playerController);
        this.setBounds(400,100,497,520);
        this.setTitle("Pacman");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(graphicController);
        //this.add(graphicControllerTest);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
                stopSecondaryComponents();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                
            }

            @Override
            public void windowIconified(WindowEvent e) {
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                
            }
        });
        
        this.setVisible(true);
        startSecondaryComponents();
    }
    
    private void startSecondaryComponents(){
        this.pacmanSFX.playLoop();
    }
    
    private void stopSecondaryComponents(){
        this.pacmanSFX.stop();
    }
    
}
