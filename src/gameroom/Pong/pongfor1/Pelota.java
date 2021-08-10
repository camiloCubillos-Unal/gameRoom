
package gameroom.Pong.pongfor1;

import gameroom.bdGestor.Bd_gestor;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Pelota {
    
    int score=0;
    private int x, y, cx=1, cy=1;
    private final int ancho=15, alto=15;
    private String username;
    public static boolean finJuego = false;
    private Bd_gestor bdGestor;
    private JFrame jframe;

    public Pelota(int x, int y, String _username, JFrame _jframe) {
        this.x=x;
        this.y=y;
        this.username = _username;
        this.bdGestor = new Bd_gestor("ueizgfjgoc2gxumn","u22yMCtXEBlTA4pMsMjI");
        this.jframe = _jframe;
    }
    
    public Rectangle2D getPelota(){
        return new Rectangle2D.Double(x,y,ancho,alto);
    }
    
    public void rebote(Rectangle margen, boolean choque) throws ClassNotFoundException, SQLException{
    x+=cx;
    y+=cy;
        if(choque){
            cy=-cy;
            y=510;
            if(finJuego==false){
            score++;
            }
        }
        if(x>(margen.getMaxX()-15)){
            cx=-cx;
        }
        if(y>(margen.getMaxY()-15)){
            cy=-cy;
            finJuego=true;
            bdGestor.updateScore("pong", username, score);
            JOptionPane.showMessageDialog(null, "Puntaje: "+score, "GAME OVER!", -1);
            this.jframe.dispose();
        }
        if(x<0){
            cx=-cx;
        }
        if(y<0){
            cy=-cy;    
        }
    }
    
    public int getScore() {
        return score;
    }
}