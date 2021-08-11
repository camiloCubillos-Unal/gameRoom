
package gameroom.games.Pong.pongfor1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mesa extends JPanel{
    

    Pelota pelota;
    Raqueta raqueta = new Raqueta(225,525);
    
    public Mesa(String _username, JFrame _jframe) {
        pelota = new Pelota(15, 0, _username, _jframe);
        setBackground(Color.BLACK);
    }
    
    @Override
    public void paintComponent(Graphics p){
        super.paintComponent(p);
        Graphics2D p2 = (Graphics2D) p;
        p2.setColor(Color.WHITE);
        dibujarPuntaje(p2);
        pintor(p2);
        try {
            momentum();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pintor(Graphics2D p){
        p.fill(pelota.getPelota());
        p.fill(raqueta.getRaqueta());
    }
    
    public void momentum() throws ClassNotFoundException, SQLException{
        pelota.rebote(getBounds(),choque(raqueta.getRaqueta()));
        raqueta.accion(getBounds());
    }
    
    private boolean choque(Rectangle2D f){
        return pelota.getPelota().intersects(f);
    }
    
    private void dibujarPuntaje(Graphics2D g) {
        Graphics2D g1 = g, g2 = g;
        Font score = new Font("Arial", Font.BOLD, 30);
        g.setFont(score);

        g1.drawString(Integer.toString(pelota.getScore()), (float) getBounds().getCenterX(), 30);
    }
    
}
