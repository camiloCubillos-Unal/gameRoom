
package gameroom;

import gameroom.flappybird.Main;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameMenu extends JFrame{
    
    public GameMenu(){
        
        JTextArea ta = new JTextArea();
        
        JPanel mainPanel = new CustomPanel("src\\media\\img\\testBackground.png");
        
        GameCard g1 = new GameCard("not_flappy_bird","src\\media\\img\\testIcon.png",0,0);
        GameCard g2 = new GameCard("snake","src\\media\\img\\testIcon2.png",200,0);
        GameCard g3 = new GameCard("pacman","src\\media\\img\\testIcon3.png",400,0);
        GameCard g4 = new GameCard("","src\\media\\img\\comingSoon.png",0,200);
        GameCard g5 = new GameCard("","src\\media\\img\\comingSoon.png",200,200);
        GameCard g6 = new GameCard("","src\\media\\img\\comingSoon.png",400,200);
        GameCard g7 = new GameCard("","src\\media\\img\\comingSoon.png",0,400);
        GameCard g8 = new GameCard("","src\\media\\img\\comingSoon.png",200,400);
        GameCard g9 = new GameCard("","src\\media\\img\\comingSoon.png",400,400);
        
        
        mainPanel.add(g1);
        mainPanel.add(g2);
        mainPanel.add(g3);
        mainPanel.add(g4);
        mainPanel.add(g5);
        mainPanel.add(g6);
        mainPanel.add(g7);
        mainPanel.add(g8);
        mainPanel.add(g9);

        
       
        this.setContentPane(mainPanel);
        this.setTitle("GameRoom");
        this.setBounds(400,50,615,630);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
    }
    
}
