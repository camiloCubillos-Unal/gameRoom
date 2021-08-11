
package gameroom.games.Pong.pongfor1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Movimiento extends Thread{
    double frecuencia=5;

    Mesa pelota;
    Mesa diseño;

    public Movimiento(Mesa diseño) {
        this.diseño=diseño;
    }
    
    public void run(){
        while(true){
        diseño.repaint();
            try {
                Thread.sleep((long) frecuencia);
            } catch (InterruptedException ex) {
                Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            frecuencia-=0.00000000000001;
        }
        
    }
}
