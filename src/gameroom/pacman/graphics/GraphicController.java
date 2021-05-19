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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author ccubi
 */
public class GraphicController extends JPanel {
    
    private Graphics2D graphicPainter;
    private MatrixController matrixController;
    private FontController fontController;
    private PlayerController playerController;
    public Pacman pacman;
    private Image brick;
    private Image galleta;
    private Font scoreFont;
    private boolean firstGeneration;
    
    int map[][] = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    
    public GraphicController(PlayerController _playerController){
        this.playerController = _playerController;
        this.fontController = new FontController();
        pacman = new Pacman("src\\media\\img\\Pacman_right.png", 224, 384,this.playerController,this.map);
        brick = new ImageIcon("src\\media\\img\\brick.png").getImage();
        galleta = new ImageIcon("src\\media\\img\\galleta2.png").getImage();
        scoreFont = fontController.createFont("src\\media\\fonts\\8-bit-pusab.ttf",10);
        matrixController = new MatrixController();
        
        JButton nextMapBtn = new JButton("Siguiente Mapa");
        nextMapBtn.setBounds(10,10,100,100);
        nextMapBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGeneration();
            }
        });
        //this.add(nextMapBtn);        
    }
    
    private void restartGeneration(){
        this.firstGeneration = false;
    }
    
    public void paintComponent(Graphics _graphics){
        
        super.paintComponent(_graphics);
        graphicPainter = (Graphics2D) _graphics;
        
        try {
            drawBackground();
            drawWalls();
            if(!firstGeneration){
                generateMap();
            }
            drawPacman();
            //drawGrid();
            drawScore();
            checkCookiesAmount();
            Thread.sleep(14);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphicController.class.getName()).log(Level.SEVERE, null, ex);
        }                
        
    }
    
    private void checkCookiesAmount(){
        int cookiesAmount = matrixController.getZeroes(map);
        if(cookiesAmount == 0){
            this.firstGeneration = false;
        }
    }
    
    private void generateMap(){
        int randomPattern;
        int randomRotation;
        int[][] pattern;
        
        pacman.setXPosition(224);
        pacman.setYPosition(384);

        // leftSide map
        
        randomPattern = ThreadLocalRandom.current().nextInt(0, matrixController.patterns.length);
        randomRotation = ThreadLocalRandom.current().nextInt(1,5);
        pattern = matrixController.rotateMatrix(matrixController.patterns[randomPattern], randomRotation);
        matrixController.composeMatrix(map, pattern, 1);
        pattern = matrixController.reflexMatrix(pattern);
        matrixController.composeMatrix(map, pattern, 2);
        
        // rightSide map
        
        randomPattern = ThreadLocalRandom.current().nextInt(0, matrixController.patterns.length);
        randomRotation = ThreadLocalRandom.current().nextInt(1,5);
        pattern = matrixController.rotateMatrix(matrixController.patterns[randomPattern], randomRotation);
        matrixController.composeMatrix(map, pattern, 3);
        pattern = matrixController.reflexMatrix(pattern);
        matrixController.composeMatrix(map, pattern, 4);
        
        // Draw pathCrosses
            
        // leftCross
            
        map[6][2] = 0; map[6][5] = 0;
        map[7][2] = 0; map[7][5] = 0;
        map[8][2] = 0; map[8][5] = 0;
            
        // upCross
            
        map[2][6] = 0; map[5][6] = 0;
        map[2][7] = 0; map[5][7] = 0;
        map[2][8] = 0; map[5][8] = 0;
            
        // rightCross
            
        map[6][9] = 0; map[6][12] = 0;
        map[7][9] = 0; map[7][12] = 0;
        map[8][9] = 0; map[8][12] = 0;
           
        // downCross
            
        map[9][6] = 0; map[12][6] = 0;
        map[9][7] = 0; map[12][7] = 0;
        map[9][8] = 0; map[12][8] = 0;
        
        firstGeneration = true;
    }
    
    private void drawBackground(){
        this.setBackground(Color.BLACK);
    }
    
    private void drawPacman() throws InterruptedException{
        
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
        graphicPainter.setColor(Color.white);
        graphicPainter.setFont(scoreFont);
        graphicPainter.drawString(String.format("Score: %s",playerController.getScore()), 32, 25);
    }
}
