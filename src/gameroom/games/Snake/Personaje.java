
package gameroom.games.Snake;

import java.awt.Color;
import gameroom.bdGestor.Bd_gestor;
import gameroom.bdGestor.ThreadedTransaction;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Personaje extends JPanel{
    
    Color colorVibora = Color.GREEN;
    Color colorFruta = Color.RED;  
    int tamT,tamU,numU,marco;
    int score=0;
    List<int[]> vibora = new ArrayList<>();
    int [] fruta=new int[2];
    
    String mov = "Ar";
    String mov2 = "Ar";
    
    Thread thr;
    Frecuencia vel;
    
    JFrame jframe;
    String username;
    ThreadedTransaction threadedTransaction;
    boolean threadedTransactionRunning = false;
    
    
    public Personaje(int tamT, int numU,JFrame _jframe, String _username) {
        this.tamT = tamT;
        this.numU = numU;
        this.tamU = tamT/numU;
        this.marco = tamT%numU;
        this.jframe = _jframe;
        this.username = _username;
        
        int[] a={numU/2-1, numU/2-1};
        int[] b={numU/2, numU/2-1};
        vibora.add(a);
        vibora.add(b);
        FrutaR();
        
        vel=new Frecuencia(this);
        thr=new Thread(vel);
        thr.start();
    }
    
    @Override
    public void paint(Graphics pintura){
    
        super.paint(pintura);
        pintura.setColor(colorVibora);
        
        for(int i=0; i < vibora.size();i++){
            pintura.fillRect(marco/2+vibora.get(i)[0]*tamU, marco/2+vibora.get(i)[1]*tamU, tamU-1, tamU-1);
        }
        pintura.setColor(colorFruta);
        pintura.fillRect(marco/2+fruta[0]*tamU, marco/2+fruta[1]*tamU, tamU-1, tamU-1);
    }
    
    public void moverVibora() throws ClassNotFoundException, SQLException{
        NueDireccion();
        int[] cuerpo = vibora.get(vibora.size()-1);
        int sx=0;
        int sy=0;
        
        switch (mov){
            case "Ar":sy=-1;break;
            case "Ab":sy=1;break;
            case "D":sx=1;break;
            case "I":sx=-1;break;
        }
        
        int[] pos={Math.floorMod(cuerpo[0]+sx,numU) , Math.floorMod(cuerpo[1]+sy,numU)};
        
        boolean viva=false;
        for(int i=0; i<vibora.size();i++){
            if(pos[0]==vibora.get(i)[0] && pos[1]==vibora.get(i)[1]){
            viva = true;
            break;
            }
        }
        
        if(viva){
            JOptionPane.showMessageDialog(null, "Puntaje: "+score, "GAME OVER", -1);
            if(!threadedTransactionRunning){
                threadedTransaction = new ThreadedTransaction(0,"snake",username, score);
                threadedTransaction.start();
                threadedTransactionRunning = true;
            }
            this.jframe.dispose();
        }
        if(pos[0]==fruta[0] && pos[1]==fruta[1]){
            vibora.add(pos);
            score++;
            FrutaR();
        }else{
        vibora.add(pos);
        vibora.remove(0);
        }  
    }
    
    public void FrutaR(){
        boolean nacer = false;
        int fx = (int) (Math.random()*numU);
        int fy = (int) (Math.random()*numU);
        
        for(int[] cuerpo:vibora){
            if(cuerpo[0]==fx && cuerpo[1]==fy){
            nacer = true;
            FrutaR();
            break;
            }
        }
        
        if(!nacer){
            this.fruta[0]=fx;
            this.fruta[1]=fy;
        }
    }
    
    public void Direccion(String m){
        if(mov=="Ar"){
            if(m!="Ab"){
                this.mov2=m;
            }
        }
        if(mov=="Ab"){
            if(m!="Ar"){
                this.mov2=m;
            }
        }
        if(mov=="D"){
            if(m!="I"){
                this.mov2=m;
            }
        }
        if(mov=="I"){
            if(m!="D"){
                this.mov2=m;
            }
        }
        
    }
    public void NueDireccion(){
        this.mov=this.mov2;
    }
}

