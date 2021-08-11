/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.games.pacman.graphics;

import gameroom.games.pacman.audioController.AudioController;
import gameroom.games.pacman.entities.Ghost;
import gameroom.games.pacman.playerController.PlayerController;
import gameroom.games.pacman.entities.Pacman;
import java.awt.Color;
import gameroom.bdGestor.Bd_gestor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
    private AudioController deathSound;
    private FontController fontController;
    private PlayerController playerController;
    public Pacman pacman;
    public Ghost redGhost;
    public Ghost blueGhost;
    public Ghost orangeGhost;
    private Image brick;
    private Image galleta;
    public Image lifesSprite;
    private Font _8bitFont;
    private Font _gameOverFont;
    private String username;
    private boolean firstGeneration;
    private Bd_gestor bdGestor;
    private AudioController pacmanSFX;

    int map[][] = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    public GraphicController(PlayerController _playerController, String _username, AudioController _pacmanSFX) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.playerController = _playerController;
        this.fontController = new FontController();
        pacman = new Pacman("src\\media\\img\\Pacman_right.png", 224, 384, this.playerController, this.map);
        redGhost = new Ghost("src\\media\\img\\redGhost.png", 64, 224);
        blueGhost = new Ghost("src\\media\\img\\blueGhost.png", 224, 64);
        orangeGhost = new Ghost("src\\media\\img\\orangeGhost.png", 384, 224);
        brick = new ImageIcon("src\\media\\img\\brick.png").getImage();
        galleta = new ImageIcon("src\\media\\img\\galleta2.png").getImage();
        lifesSprite = new ImageIcon("src\\media\\img\\lifes.png").getImage();
        _8bitFont = fontController.createFont("src\\media\\fonts\\8-bit-pusab.ttf", 10);
        _gameOverFont = fontController.createFont("src\\media\\fonts\\8-bit-pusab.ttf", 25);
        matrixController = new MatrixController();
        deathSound = new AudioController("src\\media\\Audio\\SFX\\pacman_death.wav", 0.30);
        username = _username;
        bdGestor = new Bd_gestor("ueizgfjgoc2gxumn", "u22yMCtXEBlTA4pMsMjI");
        pacmanSFX = _pacmanSFX;

        JButton nextMapBtn = new JButton("Siguiente Mapa");
        nextMapBtn.setBounds(10, 10, 100, 100);
        nextMapBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGeneration();
            }
        });
        //this.add(nextMapBtn);        
    }

    private void restartGeneration() {
        this.firstGeneration = false;
        if (pacman.getLifes() < 3) {
            pacman.setLifes(pacman.getLifes() + 1);
        }
    }

    public void paintComponent(Graphics _graphics) {

        super.paintComponent(_graphics);
        graphicPainter = (Graphics2D) _graphics;

        graphicPainter.setFont(_8bitFont);
        graphicPainter.setColor(Color.WHITE);

        try {
            if (pacman.getLifes() > 0) {
                drawBackground();
                drawWalls();
                if (!firstGeneration) {
                    generateMap();
                }
                drawPacman();
                drawGhosts();
                //drawGrid();
                checkGhostCollisions();
                drawLifes();
                drawScore();
                checkCookiesAmount();
                Thread.sleep(14);
            }else{
                graphicPainter.setFont(_gameOverFont);
                graphicPainter.drawString("GAME OVER", 120, 150);
                graphicPainter.drawString("SCORE: " + playerController.getScore(), 130, 200);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(GraphicController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GraphicController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GraphicController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void checkCookiesAmount() {
        int cookiesAmount = matrixController.getZeroes(map);
        if (cookiesAmount == 0) {
            restartGeneration();
        }
    }

    private void generateMap() {
        int randomPattern;
        int randomRotation;
        int[][] pattern;

        pacman.setXPosition(224);
        pacman.setYPosition(384);

        // leftSide map
        randomPattern = ThreadLocalRandom.current().nextInt(0, matrixController.patterns.length);
        randomRotation = ThreadLocalRandom.current().nextInt(1, 5);
        pattern = matrixController.rotateMatrix(matrixController.patterns[randomPattern], randomRotation);
        matrixController.composeMatrix(map, pattern, 1);
        pattern = matrixController.reflexMatrix(pattern);
        matrixController.composeMatrix(map, pattern, 2);

        // rightSide map
        randomPattern = ThreadLocalRandom.current().nextInt(0, matrixController.patterns.length);
        randomRotation = ThreadLocalRandom.current().nextInt(1, 5);
        pattern = matrixController.rotateMatrix(matrixController.patterns[randomPattern], randomRotation);
        matrixController.composeMatrix(map, pattern, 3);
        pattern = matrixController.reflexMatrix(pattern);
        matrixController.composeMatrix(map, pattern, 4);

        // Draw pathCrosses
        // leftCross
        map[6][2] = 0;
        map[6][5] = 0;
        map[7][2] = 0;
        map[7][5] = 0;
        map[8][2] = 0;
        map[8][5] = 0;

        // upCross
        map[2][6] = 0;
        map[5][6] = 0;
        map[2][7] = 0;
        map[5][7] = 0;
        map[2][8] = 0;
        map[5][8] = 0;

        // rightCross
        map[6][9] = 0;
        map[6][12] = 0;
        map[7][9] = 0;
        map[7][12] = 0;
        map[8][9] = 0;
        map[8][12] = 0;

        // downCross
        map[9][6] = 0;
        map[12][6] = 0;
        map[9][7] = 0;
        map[12][7] = 0;
        map[9][8] = 0;
        map[12][8] = 0;

        firstGeneration = true;
    }

    private void drawBackground() {
        this.setBackground(Color.BLACK);
    }

    private void drawPacman() throws InterruptedException {

        graphicPainter.drawImage(pacman.getSprite(), pacman.getXPosition(), pacman.getYPosition(), null);
        pacman.move();

        repaint();

    }

    private void checkGhostCollisions() throws InterruptedException, ClassNotFoundException, SQLException {

        if (this.pacman.collider.onCollision(this.redGhost.collider)) {
            deathSound.play();
            this.pacman.setLifes(this.pacman.getLifes() - 1);
            this.pacman.setXPosition(this.pacman.getXSpawn());
            this.pacman.setYPosition(this.pacman.getYSpawn());
        }

        if (this.pacman.collider.onCollision(this.blueGhost.collider)) {
            deathSound.play();
            this.pacman.setLifes(this.pacman.getLifes() - 1);
            this.pacman.setXPosition(this.pacman.getXSpawn());
            this.pacman.setYPosition(this.pacman.getYSpawn());
        }

        if (this.pacman.collider.onCollision(this.orangeGhost.collider)) {
            deathSound.play();
            this.pacman.setLifes(this.pacman.getLifes() - 1);
            this.pacman.setXPosition(this.pacman.getXSpawn());
            this.pacman.setYPosition(this.pacman.getYSpawn());
        }

        if (this.pacman.getLifes() <= 0) {
            pacmanSFX.stop();
            this.pacman.setSprite("");
            bdGestor.updateScore("pacman", username, playerController.getScore());
        }
    }

    private void drawLifes() {

        graphicPainter.setFont(_8bitFont);
        graphicPainter.setColor(Color.WHITE);
        graphicPainter.drawString("Lifes: ", 320, 25);
        if (this.pacman.getLifes() == 3) {
            graphicPainter.drawImage(lifesSprite, 432, 12, this);
        }
        if (this.pacman.getLifes() >= 2) {
            graphicPainter.drawImage(lifesSprite, 412, 12, this);
        }
        if (this.pacman.getLifes() >= 1) {
            graphicPainter.drawImage(lifesSprite, 392, 12, this);
        }
    }

    private void drawGhosts() {

        graphicPainter.drawImage(redGhost.getSprite(), redGhost.getXPosition(), redGhost.getYPosition(), null);
        graphicPainter.drawImage(blueGhost.getSprite(), blueGhost.getXPosition(), blueGhost.getYPosition(), null);
        graphicPainter.drawImage(orangeGhost.getSprite(), orangeGhost.getXPosition(), orangeGhost.getYPosition(), null);

        redGhost.move(map);
        blueGhost.move(map);
        orangeGhost.move(map);

        repaint();
    }

    private void drawWalls() {

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == 1) {
                    graphicPainter.drawImage(brick, 32 * (col), 32 * (row), this);
                } else if (map[row][col] == 0) {
                    graphicPainter.drawImage(galleta, 32 * (col), 32 * (row), this);
                }
            }
        }

        repaint();

    }

    private void drawGrid() {

        int sizeFactor = 32;

        for (int i = 0; i < 600; i += sizeFactor) {
            for (int j = 0; j < 600; j += sizeFactor) {
                graphicPainter.setColor(Color.MAGENTA);
                graphicPainter.drawRect(i, j, sizeFactor, sizeFactor);
            }
        }
    }

    private void drawScore() {
        graphicPainter.setColor(Color.white);
        graphicPainter.setFont(_8bitFont);
        graphicPainter.drawString(String.format("Score: %s", playerController.getScore()), 32, 25);
    }
}
