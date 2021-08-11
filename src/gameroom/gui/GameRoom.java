/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.gui;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author ccubi
 */
public class GameRoom {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //Juego mainFrame = new Juego(); 
        //GameMenu gameMenu = new GameMenu();
        LoginScreen ls = new LoginScreen();
        ls.setLocationRelativeTo(null);
        ls.setVisible(true);
        
    }
    
}
