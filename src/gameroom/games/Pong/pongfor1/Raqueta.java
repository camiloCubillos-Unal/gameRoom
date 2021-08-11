
package gameroom.games.Pong.pongfor1;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Raqueta {
    
    private int x,y;
    private static final int ancho=40, alto=15;

    public Raqueta(int x, int y) {
        this.x=x;
        this.y=y;
    }
    
    public Rectangle2D getRaqueta(){
        return new Rectangle2D.Double(x,y,ancho,alto);
    }
    
    public void accion(Rectangle margen){
        if(Eventos.der && x<(margen.getMaxX()-40)){
            x++;
        }
        if(Eventos.izq && x>0){
            x--;
        }
    }
}
