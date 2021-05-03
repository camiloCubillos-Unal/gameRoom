
package juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

public class Fantasmas {
    
    //atributos
    int fanx;
    int fany;
    Timer timer;
    Random aleatorio;
    int direccion;
    int mx;
    int my;
    
    public Fantasmas( int x, int y ){
        aleatorio = new Random();
        fanx = x;
        fany = y;
        Juego.mat[fanx][fany] = 7;
        direccion = aleatorio.nextInt(4);
        this.movimiento();
    }//contrutor
    
    
    
    public void movimiento(){
    
        timer = new Timer (0, new ActionListener () 
        { 
            public void actionPerformed(ActionEvent e) 
            { 
                System.out.println("esta funcionando el timer");
                //izquierda
                if( fanx > 0 && direccion == 0 ){
                    System.out.println("aqui" + direccion);
                    //camina
                    if(Juego.mat[fanx-1][fany] != 2 && (Juego.mat[fanx-1][fany] == 0 || Juego.mat[fanx-1][fany] == 1)){
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                        fanx -=1;
                        Juego.mat[fanx][fany]=7;
                        Juego.pintarMatriz();
                    }else
                    //choca con la pared
                    if(fanx > 0 && Juego.mat[fanx-1][fany] == 2 ){
                        direccion = aleatorio.nextInt(4); 
                    }else
                    //choca con otro fantasm
                    if(Juego.mat[fanx-1][fany] == 7 ){
                        direccion = aleatorio.nextInt(4); 
                    }
                }
                //derecha
                if( direccion == 1 ){
                    System.out.println("aqui" + direccion);
                    if(Juego.mat[fanx+1][fany] != 2 && (Juego.mat[fanx+1][fany] == 0 || Juego.mat[fanx+1][fany] == 1)){
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                        fanx +=1;
                        Juego.mat[fanx][fany]=7;
                        Juego.pintarMatriz();
                    }else
                    
                    if(fanx < 14 && Juego.mat[fanx+1][fany] == 2 ){
                        direccion = aleatorio.nextInt(4); 
                    }else
                    if(Juego.mat[fanx+1][fany] == 7 ){
                        direccion = aleatorio.nextInt(4); 
                    }
 
                }
                //arriba
                if( fany > 0 && direccion == 2 ){
                    System.out.println("aqui" + direccion);
                    if(Juego.mat[fanx][fany-1] != 2 && (Juego.mat[fanx][fany-1] == 0 || Juego.mat[fanx][fany-1] == 1)){
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                        fany -=1;
                        Juego.mat[fanx][fany]=7;
                        Juego.pintarMatriz();
                    }else
                    if(fany > 0 && Juego.mat[fanx][fany-1] == 2 ){
                        direccion = aleatorio.nextInt(4); 
                    }else
                    if(Juego.mat[fanx][fany-1] == 7 ){
                        direccion = aleatorio.nextInt(4); 
                    }
                }
                //abajo
                if(  direccion == 3 ){
                    System.out.println("aqui" + direccion);
                    if(Juego.mat[fanx][fany+1] != 2 &&(Juego.mat[fanx][fany+1] == 0 || Juego.mat[fanx][fany+1] == 1)){
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                        fany +=1;
                        Juego.mat[fanx][fany]=7;
                        Juego.pintarMatriz();
                    }else
                    if(fany < 14 && Juego.mat[fanx][fany+1] == 2 ){
                        direccion = aleatorio.nextInt(4); 
                    }else
                    if(Juego.mat[fanx][fany+1] == 7 ){
                        direccion = aleatorio.nextInt(4); 
                    }
                }
        }});
        timer.start();
    
    }//fin metodo;
    
    
    
    
    
}
