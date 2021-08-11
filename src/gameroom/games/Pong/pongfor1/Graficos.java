
package gameroom.games.Pong.pongfor1;

import javax.swing.JFrame;

public class Graficos extends JFrame{
    
    private final int ancho=500,largo=600;
    private Mesa diseño;
    private Movimiento movimiento;
    
    public Graficos(String _username){
        
        setTitle("Pong For 1");
        setSize(ancho,largo);
        setLocationRelativeTo(null);
        setResizable(false);
        
        diseño = new Mesa(_username, this);
        add(diseño);
        addKeyListener(new Eventos());        
        
        
        movimiento=new Movimiento(diseño);
        movimiento.start();
    }
}
