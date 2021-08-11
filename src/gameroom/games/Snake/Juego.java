
package gameroom.games.Snake;

import gameroom.games.Snake.Tablero;
import java.awt.event.KeyEvent;

public class Juego extends javax.swing.JFrame {

    Personaje snake;
    
    public Juego(String _username) {
        initComponents();
        this.setLocationRelativeTo(null);
        
        snake = new Personaje(600,30,this,_username);
        this.add(snake);
        snake.setBounds(10,10,600,600);
        snake.setOpaque(false);
        
        Tablero tablero = new Tablero(600,30);
        this.add(tablero);
        tablero.setBounds(10,10,600,600);
        
        this.requestFocus(true);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Snake");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            snake.Direccion("Ar");
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            snake.Direccion("Ab");
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            snake.Direccion("D");
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            snake.Direccion("I");
        }
    }//GEN-LAST:event_formKeyPressed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
