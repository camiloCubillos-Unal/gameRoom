package gameroom.flappybird.graphics;

import gameroom.flappybird.audioController.AudioController;
import gameroom.flappybird.playerController.PlayerController;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MainFrame extends JFrame {
    
    
    GraphicController graphicController = new GraphicController();
    PlayerController playerController;
    
    public MainFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.playerController = new PlayerController(graphicController,this) {};
        this.setResizable(false);
        this.setTitle("Not Flappy Bird");
        this.setSize(615,630); // Hay que dejar un margen de 15 px a lo ancho y 30 px a lo alto
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(playerController);
        this.add(graphicController);
    }
}
