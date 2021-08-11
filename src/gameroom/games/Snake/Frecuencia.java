
package gameroom.games.Snake;

import java.sql.SQLException;
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
          try {
              snake.moverVibora();
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(Frecuencia.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(Frecuencia.class.getName()).log(Level.SEVERE, null, ex);
          }
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
