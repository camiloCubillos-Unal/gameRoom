
package Snake;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Frecuencia implements Runnable{
    
    Personaje snake;
    boolean frecuencia=true;
    
    public Frecuencia(Personaje snake) {
        this.snake=snake;
    }
    
    @Override
    public void run() {
        
      while(frecuencia){
        snake.moverVibora();
        snake.repaint();
        try {
            Thread.sleep(125);
        } catch (InterruptedException ex) {
            Logger.getLogger(Frecuencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   }
    
    public void Pausa(){
        this.frecuencia=false;
    }
}
