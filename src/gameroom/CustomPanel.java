/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ccubi
 */
public class CustomPanel extends JPanel {
    
    Image bgImage;
    
    public CustomPanel(String _imagePath){
        this.bgImage = new ImageIcon(_imagePath).getImage();
        this.setLayout(null);
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
       
    super.paintComponent(g);
        g.drawImage(this.bgImage, 0, 0, null);
    }
    
}
