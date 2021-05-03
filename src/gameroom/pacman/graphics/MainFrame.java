/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.pacman.graphics;

import gameroom.pacman.graphics.GraphicController;
import gameroom.pacman.playerController.PlayerController;
import javax.swing.JFrame;

/**
 *
 * @author ccubi
 */
public class MainFrame extends JFrame {
    
    GraphicController graphicController;
    PlayerController playerController = new PlayerController();
    
    public MainFrame(){
        this.graphicController = new GraphicController(playerController);
        this.addKeyListener(playerController);
        this.setBounds(400,100,497,520);
        this.setTitle("Pacman");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(graphicController);
        this.setVisible(true);
    }
}
