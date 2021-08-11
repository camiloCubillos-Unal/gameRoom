
package gameroom.games.Pong.pongfor1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Eventos extends KeyAdapter{
    static boolean der,izq;
    
    @Override
    public void keyPressed(KeyEvent m){
        
        int tecla = m.getKeyCode();
        
        if(tecla == KeyEvent.VK_RIGHT){
            der = true;
            izq = false;
        }
        if(tecla == KeyEvent.VK_LEFT){
            izq = true;
            der = false;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent m){
          
    }
}
