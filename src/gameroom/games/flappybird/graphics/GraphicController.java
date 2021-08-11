package gameroom.games.flappybird.graphics;

import gameroom.games.flappybird.audioController.AudioController;
import gameroom.games.flappybird.entities.Bird;
import gameroom.games.flappybird.entities.PipePair;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import gameroom.bdGestor.Bd_gestor;
import gameroom.bdGestor.ThreadedTransaction;
import java.sql.SQLException;

public class GraphicController extends JPanel {

    private Graphics2D graphicPainter;
    private Bird bird = new Bird("src\\media\\img\\Bird.png", 250, 250);
    private PipePair highPipe = new PipePair("src\\media\\img\\110_pipe_upper.png", "src\\media\\img\\310_pipe_lower.png", 700);
    private PipePair middlePipe = new PipePair("src\\media\\img\\210_pipe_upper.png", "src\\media\\img\\210_pipe_lower.png", 700);
    private PipePair bottomPipe = new PipePair("src\\media\\img\\310_pipe_upper.png", "src\\media\\img\\110_pipe_lower.png", 700);
    private PipePair[] pipesTemplate = new PipePair[]{highPipe, middlePipe, bottomPipe};
    private PipePair pipe;
    private boolean pipeOnScreen = false;
    private int pipeID;
    private int score = 0;
    private AudioController scoreUp;
    public AudioController backgroundMusic;
    private FontController fontController = new FontController();
    private Font scoreFont = fontController.createFont("src\\media\\fonts\\8-bit-pusab.ttf", 30);
    private Font restartFont = fontController.createFont("src\\media\\fonts\\8-bit-pusab.ttf", 15);
    public boolean gameOn = false;
    private ThreadedTransaction threadedTransaction;
    private boolean threadedTransactionRunning = false;
    public String username;

    public GraphicController(String _username) throws UnsupportedAudioFileException, IOException, IOException, LineUnavailableException, LineUnavailableException {
        this.scoreUp = new AudioController("src\\media\\Audio\\SFX\\point.wav", 0.7);
        this.backgroundMusic = new AudioController("src\\media\\audio\\music\\background.wav", 0.25);
        this.username = _username;
        backgroundMusic.play();

    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        graphicPainter = (Graphics2D) graphics;
        graphicPainter.setFont(new Font(null, Font.PLAIN, 20));

        drawBushes();
        drawPipes();
        drawGround();
        drawBackground();
        //drawGrid();
        drawPoints();
        drawInitMessage();
        try {
            drawBird();
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphicController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GraphicController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GraphicController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //drawColliders();
    }

    private void drawGround() {
        for (int i = 0; i < 600; i += 10) {
            ImageIcon groundIcon = new ImageIcon("src\\media\\img\\ground.png");
            Image ground = groundIcon.getImage();
            graphicPainter.drawImage(ground, i, 555, null);
        }
    }

    private void drawBackground() {
        this.setBackground(new Color(163, 231, 255));
    }

    private void drawBushes() {
        for (int i = 0; i < 600; i += 60) {
            ImageIcon bushIcon = new ImageIcon("src\\media\\img\\bush.png");
            Image bush = bushIcon.getImage();
            graphicPainter.drawImage(bush, i, 500, null);
        }
    }

    private void drawGrid() {

        int sizeFactor = 15;

        for (int i = 0; i < 600; i += sizeFactor) {
            for (int j = 0; j < 600; j += sizeFactor) {
                graphicPainter.setColor(Color.MAGENTA);
                graphicPainter.drawRect(i, j, sizeFactor, sizeFactor);
            }
        }
    }

    private void drawBird() throws InterruptedException, ClassNotFoundException, SQLException {

        graphicPainter.drawImage(bird.getSprite(), bird.getXSpawn(), bird.getYPosition(), null);

        if (gameOn) {
            if (bird.getYPosition() >= 500) {
                bird.die();
                drawLoseMessage();
                submitScore();
            } else {
                bird.moveBird();
                bird.collider.updateCollider(bird.collider.x, bird.getYPosition(), bird.collider.width, bird.collider.height);
                if (bird.collider.onCollision(pipe.lowerPipeCollider) | bird.collider.onCollision(pipe.upperPipeCollider)) {
                    bird.die();
                }
            }
        }
        repaint();
    }

    private void submitScore() throws ClassNotFoundException, SQLException {
        if (!threadedTransactionRunning) {
            this.threadedTransaction = new ThreadedTransaction(0, "not_flappy_bird", username, score);
            this.threadedTransaction.start();
            threadedTransactionRunning = true;
        }
    }

    public void drawLoseMessage() {

        graphicPainter.setColor(new Color(50, 50, 50));
        graphicPainter.fillRect(120, 200, 400, 125);
        graphicPainter.setColor(Color.WHITE);
        graphicPainter.drawString("Game Over", 180, 250);
        graphicPainter.setFont(restartFont);
        graphicPainter.drawString("Presiona    para reiniciar.", 135, 300); // La fuente se distorsiona al exportar. Por eso hay que mover un poco el texto
        graphicPainter.setColor(Color.GREEN);
        graphicPainter.drawString("R", 268, 300);
    }

    public void drawInitMessage() {
        if (!gameOn) {
            graphicPainter.setColor(new Color(50, 50, 50));
            graphicPainter.fillRect(80, 400, 450, 125);
            graphicPainter.setColor(Color.WHITE);
            graphicPainter.setFont(restartFont);
            graphicPainter.drawString("Presiona                  para saltar.", 95, 475); // La fuente se distorsiona al exportar. Por eso hay que mover un poco el texto
            graphicPainter.setColor(Color.GREEN);
            graphicPainter.drawString("ESPACIO", 230, 475);
        }

    }

    public void drawPoints() {
        if (gameOn) {
            graphicPainter.setFont(this.scoreFont);
            graphicPainter.drawString(String.valueOf(this.score), 300, 100);
        }
    }

    public void drawPipes() {

        if (gameOn) {
            if (pipeOnScreen == false) {
                pipeID = (int) (Math.random() * 3);
                pipe = new PipePair(pipesTemplate[pipeID].getUpperPipePath(), pipesTemplate[pipeID].getLowerPipePath(), 600);
                pipeOnScreen = true;
            }

            if (pipe.getPipesXPosition() >= -100) {
                graphicPainter.drawImage(pipe.getUpperPipeSprite(), pipe.getPipesXPosition(), pipe.getUpperPipeYSpawn(), null);
                graphicPainter.drawImage(pipe.getLowerPipeSprite(), pipe.getPipesXPosition(), pipe.getLowerPipeYSpawn(), null);
                if (bird.isAlive()) {
                    pipe.movePipes();
                }
            } else {
                scoreUp.play();
                score++;
                pipeOnScreen = false;
            }
        }
    }

    public Bird getBird() {
        return this.bird;
    }

    public void drawColliders() {
        graphicPainter.drawRect(bird.collider.x, bird.collider.y, bird.collider.width, bird.collider.height);
        graphicPainter.drawRect(pipe.getPipesXPosition() + 15, 0, pipe.upperPipeCollider.width, pipe.upperPipeCollider.height);
        graphicPainter.drawRect(pipe.getPipesXPosition() + 15, 555 - pipe.getPipeHeight(pipe.getPipeSpritePath("lowerPipePath")), pipe.lowerPipeCollider.width, pipe.lowerPipeCollider.height);
    }
}
