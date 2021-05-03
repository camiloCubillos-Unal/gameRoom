
package gameroom.pacman.playerController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener{    

    int keyPressed = 0;
    int auxKeyPressed = 0;
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyPressed = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Aa");
    }
    
    public int getKey(){
        return this.keyPressed;    
    }
    
}

