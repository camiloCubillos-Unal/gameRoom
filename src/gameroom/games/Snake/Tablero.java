
package gameroom.games.Snake;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Tablero extends JPanel{
    
    Color colorTablero = Color.BLACK;
    
    int tamT,tamU,numU,marco;

    public Tablero(int tamT, int numU) {
        this.tamT = tamT;
        this.numU = numU;
        this.tamU = tamT/numU;
        this.marco = tamT%numU;
        this.setBackground(Color.BLACK);
    }
    
    @Override
    public void paint(Graphics pintura){
    
        super.paint(pintura);
        pintura.setColor(colorTablero);
        
        for(int i=0; i<numU;i++){
            for(int j=0; j<numU;j++){
                pintura.fillRect(marco/2+i*tamU, marco/2+j*tamU, tamU-1, tamU-1);
            }
        }
    }
}
