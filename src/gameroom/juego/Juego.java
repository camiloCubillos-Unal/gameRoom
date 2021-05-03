package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Pablo Reinel
 */
public class Juego {
    
    //atributos
    static JFrame ventana;
    
    //presentacion
    JPanel panelPresentacion;
    JButton iniciar;
    JLabel fondoPresentacion;
    ImageIcon imagenFondoPres;
    
    //menu
    JPanel panelMenu;
    JButton botones[];
    JLabel fondoMenu;
    ImageIcon imagenFondoMenu;
      
    //juego
    
    static JPanel panelJuego;
    JLabel fondoJuego;
    ImageIcon imagenFondoJuego;
    static int mat[][];
    static JLabel matriz [][];
    int px;
    int py;
    String jugador;
    JLabel nombre;
    int puntos;
    JLabel records;
    int abajo;
    int arriba;
    int izq;
    int der;
    Timer timer;
    
    //fantasmas
    Fantasmas fantasma1;
    Fantasmas fantasma2;
    Fantasmas fantasma3;
    static int matAux[][];
    
    
    public Juego(){
    
        ventana = new JFrame("PACMAN");
        ventana.setSize(700, 700);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panelPresentacion = new JPanel();
        panelPresentacion.setLayout(null);
        panelPresentacion.setBounds(0,0,ventana.getWidth(),ventana.getHeight());
        panelPresentacion.setVisible(true);
        panelPresentacion.setBackground(Color.red);
        
        iniciar = new JButton("Iniciar");
        iniciar.setBounds(ventana.getWidth()-120, 20, 100, 30);
        iniciar.setVisible(true);
        iniciar.setBackground(Color.white);
        panelPresentacion.add(iniciar,0);
        
        fondoPresentacion = new JLabel();
        fondoPresentacion.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        imagenFondoPres = new ImageIcon("src/media/img/fondoPresentacion.png");
        imagenFondoPres = new ImageIcon(imagenFondoPres.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoPresentacion.setIcon(imagenFondoPres);
        fondoPresentacion.setVisible(true);
        panelPresentacion.add(fondoPresentacion,0);
        
        //menu
        botones = new JButton[2];
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton();
        }
        
       
     
       
        
        iniciar.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e){
                 System.out.println("iniciar");
                 menu();
                 eventoMenu();
            }
        
        });
        
        //juego
        
         mat = new int[15][15];
         mat = tablero(1);
         //matAux = tablero(1);
         matriz = new JLabel[15][15];
         matAux = new int[15][15];
         for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                matriz[i][j] = new JLabel();
                matAux[i][j] = mat[i][j];
            }
            
        }
        
        
        px = 1;
        py = 1;
        mat[px][py] = 3;
        
        
       
        
        abajo = 0;
        arriba = 0;
        izq = 0;
        der = 0;
        
        
        
 
        ventana.add(panelPresentacion);
        
        
        
        
        
        
        
        
        ventana.setVisible(true);
        
    
    }//fin constructor
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    public void jugar(){
        
        panelMenu.setVisible(false);
        panelJuego = new JPanel();
        panelJuego.setLayout(null);
        panelJuego.setBounds(0,0,ventana.getWidth(),ventana.getHeight());
        panelJuego.setVisible(true);
        
        fondoJuego = new JLabel();
        fondoJuego.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        imagenFondoJuego = new ImageIcon("src/media/img/fondoJugar.png");
        imagenFondoJuego = new ImageIcon(imagenFondoJuego.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoJuego.setIcon(imagenFondoJuego);
        fondoJuego.setVisible(true);
        panelJuego.add(fondoJuego,0);
        
        for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    matriz[i][j].setIcon(new ImageIcon("src/media/img/"+mat[i][j]+".png"));
                    matriz[i][j].setBounds(120+(i*30), 120+(j*30), 30, 30);
                    matriz[i][j].setVisible(true);
                    panelJuego.add(matriz[i][j],0);
            }
        }
        
        nombre = new JLabel("JUGADOR: "+ jugador);
        nombre.setBounds(20, 20, 150, 30);
        nombre.setForeground(Color.white);
        nombre.setVisible(true);
        panelJuego.add(nombre,0);
        
        puntos = 0;
        records = new JLabel("Puntos: "+puntos);
        records.setBounds(ventana.getWidth()-(150+20), 20, 150, 30);
        records.setVisible(true);
        records.setForeground(Color.white);
        panelJuego.add(records,0);
        mover();
        fantasma1 = new Fantasmas(12 ,13 ); 
        fantasma2 = new Fantasmas(13 ,13 );
        fantasma3 = new Fantasmas(13 ,12 );
        ventana.add(panelJuego);
               
    }
    
    public static void pintarMatriz(){
        for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    matriz[i][j].setIcon(new ImageIcon("src/media/img/"+mat[i][j]+".png"));
                    matriz[i][j].setBounds(120+(i*30), 120+(j*30), 30, 30);
                    matriz[i][j].setVisible(true);
                    panelJuego.add(matriz[i][j],0);
            }
        }
    }
    
    public void mover(){
        
        
        timer = new Timer (200, new ActionListener () 
        { 
            public void actionPerformed(ActionEvent e) 
            { 
              if( arriba == 1 && (mat[px][py-1]==1 || mat[px][py-1]==0)){
                    if(mat[px][py-1]==1){
                        puntos = puntos + 5;
                        records.setText("Puntos: "+puntos);
                    } 
                    mat[px][py] = 0;
                    matAux[px][py] = mat[px][py]; //esto es nuevo
                    py = py-1;
                    mat[px][py] = 3;
                    pintarMatriz();
                               
              }
              if( abajo == 1 && (mat[px][py+1]==1 || mat[px][py+1]==0)){
                    if(mat[px][py+1]==1){
                        puntos = puntos + 5;
                        records.setText("Puntos: "+puntos);
                    } 
                    mat[px][py] = 0;
                    matAux[px][py] = mat[px][py]; //esto es nuevo
                    py = py+1;
                    mat[px][py] = 3;
                    pintarMatriz();
                     
              }
              if( izq == 1 && (mat[px-1][py]==1 || mat[px-1][py]==0)){
                    if(mat[px-1][py]==1){
                        puntos = puntos + 5;
                        records.setText("Puntos: "+puntos);
                    } 
                    mat[px][py] = 0;
                    matAux[px][py] = mat[px][py]; //esto es nuevo
                    px = px-1;
                    mat[px][py] = 3;
                    pintarMatriz();
                      
              }
              if( der == 1 && (mat[px+1][py]==1 || mat[px+1][py]==0)){
                    if(mat[px+1][py]==1){
                        puntos = puntos + 5;
                        records.setText("Puntos: "+puntos);
                    } 
                    mat[px][py] = 0;
                    matAux[px][py] = mat[px][py]; //esto es nuevo
                    px = px+1;
                    mat[px][py] = 3;
                    pintarMatriz();
                   
              }
                int enc = 0;
                for (int i = 0; i < mat.length && enc == 0; i++) {
                    for (int j = 0; j < mat.length && enc == 0; j++) {
                          if(mat[i][j]==1)
                             enc =1;
                    }
                }
                if(enc == 0){
                    JOptionPane.showMessageDialog(ventana, "FELICITACIONES GANO");
                    panelJuego.setVisible(false);
                    panelMenu.setVisible(true);
                    timer.stop();
                }
                      
                //matar pacman
                if(  mat[px][py+1] == 7 || mat[px][py-1] == 7 || mat[px-1][py] == 7 || mat[px+1][py] == 7 ){
                    fantasma1.timer.stop();
                    fantasma2.timer.stop();
                    fantasma3.timer.stop();
                    JOptionPane.showMessageDialog(ventana, "ESTAS MUERTO");
                    panelJuego.setVisible(false);
                    panelMenu.setVisible(true);
                    timer.stop();
                  
                }
                
        }});
        timer.start();
        ventana.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    System.out.println("tecla hacia arriba");
                    if(mat[px][py-1]==1 || mat[px][py-1]==0 ){
                        arriba = 1;
                        abajo = 0;
                        izq = 0;
                        der = 0;
                    }    
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    System.out.println("tecla hacia abajo");
                    if(mat[px][py+1]==1 || mat[px][py+1]==0 ){ 
                        arriba = 0;
                        abajo = 1;
                        izq = 0;
                        der = 0;
                    }    
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    System.out.println("tecla hacia izquierda");
                    if(mat[px-1][py]==1 || mat[px-1][py]==0 ){
                        arriba = 0;
                        abajo = 0;
                        izq = 1;
                        der = 0;
                    }    
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    System.out.println("tecla hacia derecha");
                    if(mat[px+1][py]==1 || mat[px+1][py]==0 ){
                        arriba = 0;
                        abajo = 0;
                        izq = 0;
                        der = 1;
                    }
                }

                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
    
    
    });
    
    
    } 
    
    public int[][] tablero(int opcion){
    
        int[][]aux1 = new int[15][15];
        if( opcion == 1){
            
            int aux[][] = {
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                    {2,1,1,1,1,1,1,2,1,1,1,1,1,1,2},
                    {2,1,2,2,1,2,1,2,1,2,2,1,2,1,2},
                    {2,1,2,1,1,2,1,1,1,1,2,1,2,1,2},
                    {2,1,1,1,2,2,2,1,2,1,1,1,1,1,2},
                    {2,1,2,1,1,1,1,1,2,2,2,1,2,2,2},
                    {2,1,2,2,1,2,2,1,1,2,2,1,1,1,2},
                    {2,1,1,1,1,1,2,2,1,1,1,1,2,1,2},
                    {2,2,2,1,2,1,2,2,2,1,2,1,2,1,2},
                    {2,1,1,1,2,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,1,1,1,2,2,2,1,2,1,2,1,2},
                    {2,1,1,1,2,1,2,1,1,1,1,1,2,1,2},
                    {2,1,2,1,2,1,2,1,2,1,2,1,2,1,2},
                    {2,1,1,1,2,1,1,1,1,1,1,1,1,1,2},
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                  };
            
            return aux;
        }
        if( opcion == 2){
            int aux[][] = {
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                    {2,2,2,2,2,1,1,2,1,1,1,1,1,1,2},
                    {2,2,2,2,2,2,1,2,1,2,2,1,2,1,2},
                    {2,2,2,2,2,2,1,1,1,1,2,1,2,1,2},
                    {2,2,2,2,2,2,2,1,2,1,1,1,1,1,2},
                    {2,2,2,2,2,1,1,1,2,2,2,1,2,2,2},
                    {2,1,2,2,1,2,2,1,1,2,2,1,1,1,2},
                    {2,1,1,1,1,1,2,2,1,1,1,1,2,1,2},
                    {2,2,2,1,2,1,2,2,2,1,2,1,2,1,2},
                    {2,1,1,1,2,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,1,1,1,2,2,2,1,2,1,2,1,2},
                    {2,1,1,1,2,1,2,1,1,1,1,1,2,1,2},
                    {2,1,2,1,2,1,2,1,2,1,2,1,2,1,2},
                    {2,1,1,1,2,1,1,1,1,1,1,1,1,1,2},
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                  };
            return aux;
        }
        if( opcion == 3){
            int aux[][] = {
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                    {2,1,1,1,1,1,1,2,1,1,1,1,1,1,2},
                    {2,1,2,2,1,2,1,2,1,2,2,1,2,1,2},
                    {2,1,2,1,1,2,1,1,1,1,2,1,2,1,2},
                    {2,1,1,1,2,2,2,1,2,1,1,1,1,1,2},
                    {2,1,2,1,1,1,1,1,2,2,2,1,2,2,2},
                    {2,1,2,2,1,2,2,1,1,2,2,1,1,1,2},
                    {2,1,1,1,1,1,2,2,1,1,1,1,2,1,2},
                    {2,2,2,1,2,1,2,2,2,1,2,1,2,1,2},
                    {2,1,1,1,2,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,1,1,1,2,2,2,1,2,1,2,1,2},
                    {2,1,1,1,2,1,2,1,1,1,1,1,2,1,2},
                    {2,1,2,1,2,1,2,1,2,1,2,1,2,1,2},
                    {2,1,1,1,2,1,1,1,1,1,1,1,1,1,2},
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                  };
             return aux;
        }
        return aux1;
    }
    
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void menu(){
        
        panelPresentacion.setVisible(false);
        panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0,0,ventana.getWidth(),ventana.getHeight());
        panelMenu.setVisible(true);
        
        fondoMenu = new JLabel();
        fondoMenu.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        imagenFondoMenu = new ImageIcon("src/media/img/fondoMenu.png");
        imagenFondoMenu = new ImageIcon(imagenFondoMenu.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoMenu.setIcon(imagenFondoMenu);
        fondoMenu.setVisible(true);
        panelMenu.add(fondoMenu,0);
        
        botones[0].setText("JUGAR");
        //botones[1].setText("crear tablero");
        //[2].setText("Records");
       // botones[3].setText("cargar tablero");
        botones[1].setText("SALIR");
        
        for (int i = 0; i < botones.length; i++) {
            botones[i].setBounds(ventana.getWidth()-(200+50), (i+1)*50, 200, 40);
            botones[i].setVisible(true);
            botones[i].setBackground(Color.WHITE);
            panelMenu.add(botones[i],0);
        }
        
        ventana.add(panelMenu);
        
        
    
    }//fin del menu
    
    public void eventoMenu(){
        
        //boton jugar
        botones[0].addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e){
                 System.out.println("jugar");
                 //pedir nombre
                jugador = JOptionPane.showInputDialog(ventana, "Nombre del jugador", "Escribe aqui" );     
                while(jugador == null || jugador.compareTo("Escribe aqui")==0 || jugador.compareTo("")==0){
                    jugador = JOptionPane.showInputDialog(ventana, "Debes ingresar usuario","Escribe aqui");
                }
                 jugar();
                
            }
        
        });
        
        //boton crear tablero
       // botones[1].addMouseListener(new MouseAdapter() {

           // public void mousePressed(MouseEvent e){
              //   System.out.println("crear tablero");
                 
          //  }
        
       // });
        
        //boton records
        //botones[2].addMouseListener(new MouseAdapter() {

           // public void mousePressed(MouseEvent e){
             //    System.out.println("records");
                 
            //}
        
       // });
        
        //cargar tablero
       // botones[3].addMouseListener(new MouseAdapter() {

        //    public void mousePressed(MouseEvent e){
            //     System.out.println("cargar tablero");
                 
            //}
        
        //});
        
        //salir
        botones[1].addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e){
                 System.out.println("SALIR");
                 System.exit(0);
            }
        
        });
        
    }
    
}
