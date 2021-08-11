package gameroom.games.flappybird.graphics;

import com.sun.media.jfxmedia.AudioClip;
import gameroom.games.flappybird.audioController.AudioController;
import gameroom.games.flappybird.playerController.PlayerController;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MainFrame extends JFrame {
    
    
    GraphicController graphicController;
    PlayerController playerController;
    String username;
    
    public MainFrame(String _username) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.username = _username;
        this.graphicController = new GraphicController(_username);
        this.playerController = new PlayerController(graphicController,this) {};
        this.setResizable(false);
        this.setTitle("Not Flappy Bird");
        this.setBounds(400,50,615,630); // Hay que dejar un margen de 15 px a lo ancho y 30 px a lo alto
        this.setVisible(true);
        this.addKeyListener(playerController);
        this.add(graphicController);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                stopAll(); 
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
    }
    
    public void stopAll(){
        this.graphicController.backgroundMusic.stop();
    }
    
    
}
