/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.pacman.graphics;

import gameroom.pacman.playerController.PlayerController;
import gameroom.pacman.entities.Pacman;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author ccubi
 */
public class GraphicController extends JPanel {
    
    private Graphics2D graphicPainter;
    private PlayerController playerController;
    public Pacman pacman;
    private Image brick;
    private Image galleta;
    
    int map[][] = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,1,1,1,1,0,1,0,1,1,1,1,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,1,1,0,1,1,0,1,0,1,1,0,1,1,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                   {1,0,1,0,1,0,0,0,0,0,1,0,1,0,1},
                   {1,0,0,0,1,0,1,0,1,0,1,0,0,0,1},
                   {1,0,1,0,0,0,1,0,1,0,0,0,1,0,1},
                   {1,0,1,1,1,0,1,0,1,0,1,1,1,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,1,1,1,1,1,0,1,1,1,1,1,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    
    public GraphicController(PlayerController _playerController){
        this.playerController = _playerController;
        pacman = new Pacman("src\\media\\img\\Pacman_right.png", 224, 416,this.playerController,this.map);
        brick = new ImageIcon("src\\media\\img\\brick.png").getImage();
        galleta = new ImageIcon("src\\media\\img\\galleta2.png").getImage();
    }
    
    public void paintComponent(Graphics _graphics){
        
        super.paintComponent(_graphics);
        graphicPainter = (Graphics2D) _graphics;
        
        try {
            drawBackground();
            drawWalls();
            drawPacman();
            //drawGrid();
            drawScore();
            Thread.sleep(16);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphicController.class.getName()).log(Level.SEVERE, null, ex);
        }                
        
    }
    
    private void drawBackground(){
        this.setBackground(Color.BLACK);
    }
    
    private void drawPacman(){
        
        graphicPainter.drawImage(pacman.getSprite(),pacman.getXPosition(),pacman.getYPosition(),null);
        pacman.move();
        
        repaint();
        
    }
    
    private void drawWalls(){       
        
        for (int row = 0; row < map.length; row++) {
            for(int col = 0; col < map[row].length; col++){
                if(map[row][col] == 1){
                    graphicPainter.drawImage(brick,32 * (col),32 * (row),this);
                }else if(map[row][col] == 0){
                    graphicPainter.drawImage(galleta,32 * (col),32 * (row),this);
                }
            }
        }
        
        repaint();
        
    }
   
    private void drawGrid(){
        
        int sizeFactor = 32;
        
        for (int i = 0; i < 600; i += sizeFactor) {
            for (int j = 0; j < 600; j += sizeFactor) {
                graphicPainter.setColor(Color.MAGENTA);
                graphicPainter.drawRect(i, j, sizeFactor, sizeFactor);
            }
        }
    }
    
    private void drawScore(){
        graphicPainter.drawString("Score: ", 32, 448);
    }
}
