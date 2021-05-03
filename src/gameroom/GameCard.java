package gameroom;

import Snake.Juego;
import gameroom.flappybird.Main;
import gameroom.flappybird.graphics.MainFrame;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Cursor;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

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
                    System.out.println("xd");
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
                MainFrame ma = new MainFrame();
                break;
            case("snake"):
                Juego j = new Juego();
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
