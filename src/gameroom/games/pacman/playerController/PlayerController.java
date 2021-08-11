
package gameroom.games.pacman.playerController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener{    

    int keyPressed = 0;
    int auxKeyPressed = 0;
    int score = 0;
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyPressed = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    public int getKey(){
        return this.keyPressed;    
    }
    
    public void setScore(int _newScore){
        this.score = _newScore;
    }
    
    public int getScore(){
        return this.score;
    }
    
}

