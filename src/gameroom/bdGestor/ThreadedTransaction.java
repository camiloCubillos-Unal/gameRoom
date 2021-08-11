/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.bdGestor;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ccubi
 */
public class ThreadedTransaction extends Thread {
    
    String game;
    String username;
    int score;
    int transactionID;
    Bd_gestor bdGestor;
    
    public ThreadedTransaction(int _transactionID, String _game, String _username, int _score){
        transactionID = _transactionID;
        bdGestor = new Bd_gestor("ueizgfjgoc2gxumn","u22yMCtXEBlTA4pMsMjI");
        game = _game;
        username = _username;
        score = _score;
    }

    @Override
    public void run(){
        switch(transactionID){
            case 0:
            {
                try {
                    bdGestor.updateScore(game, username, score);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ThreadedTransaction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ThreadedTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            default:
                break;
        }
    }
    
}
