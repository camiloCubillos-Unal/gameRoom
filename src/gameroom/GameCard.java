package gameroom;

import Snake.Juego;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Cursor;
import javafx.scene.input.KeyCode;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameCard extends JButton {
    
    String game;
    Icon gameIcon;
    JButton gameButton = new JButton();
    
    public GameCard(String _game, String _gameIconPath, int _xMargin, int _yMargin){
        
        this.game = _game;
        this.gameIcon = new ImageIcon(_gameIconPath);
        this.gameButton.setIcon(this.gameIcon);
        this.gameButton.setBorder(null);
        this.gameButton.setOpaque(false);
        this.gameButton.setBackground(new Color(0,0,0,0));
        this.gameButton.setAlignmentY(CENTER_ALIGNMENT);
        this.gameButton.setAlignmentX(CENTER_ALIGNMENT);
        this.setOpaque(false);
        this.setBackground(new Color(0,0,0,0));
        this.setBounds(_xMargin,_yMargin,200,200);
        this.setBorder(null);
        this.setEnabled(false);
        
        
        this.gameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    try {
                        openGame();
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(GameCard.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(GameCard.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(GameCard.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameCard.class.getName()).log(Level.SEVERE, null, ex);
                    }                   
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                changeCursor("Selection");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changeCursor("Default");
            }
        });
        this.add(this.gameButton);
        
    }
    
    private void openGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
        switch(this.game){
            case("not_flappy_bird"):
                gameroom.flappybird.graphics.MainFrame maf = new gameroom.flappybird.graphics.MainFrame();
                break;
            case("snake"):
                Juego j = new Juego();
                break;
            case("pacman"):
                gameroom.pacman.graphics.MainFrame map = new gameroom.pacman.graphics.MainFrame();
                break;
        }
    }
    
    private void changeCursor(String _arg){
        switch(_arg){
            case "Selection":
                this.getRootPane().setCursor(new Cursor(Cursor.HAND_CURSOR));
                break;
            case "Default":
                this.getRootPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                break;
        }
    }
    
}
