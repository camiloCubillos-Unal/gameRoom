    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom.bdGestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ccubi
 */
public class Bd_gestor {
    
    String bdHost;
    String bdUser;
    String bdPassword;
    JFrame container = null;
    
    Connection connection;
        
    
    public Bd_gestor(String _username, String _password){

        this.bdUser = _username;
        this.bdPassword = _password;
        this.bdHost = "jdbc:mysql://btjjrb5hl40a5n82ljro-mysql.services.clever-cloud.com:3306/btjjrb5hl40a5n82ljro";
      
    }
    
    public Bd_gestor(String _username, String _password, JFrame _container){
        
        this.container = _container;
        this.bdUser = _username;
        this.bdPassword = _password;
        this.bdHost = "jdbc:mysql://btjjrb5hl40a5n82ljro-mysql.services.clever-cloud.com:3306/btjjrb5hl40a5n82ljro";
      
    }

    public void registerUser(String _username, String _password) throws ClassNotFoundException, ClassNotFoundException, SQLException, SQLException{
        
        
        if(!checkUserExistence(_username, _password)){
            
            try{
                // Creación de usuario
            
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(this.bdHost, this.bdUser, this.bdPassword);
                Statement statement = connection.createStatement();
                statement.execute(String.format("INSERT INTO users VALUES (null,'%s','%s')",_username,_password));
            
                // Obtención del ID del usuario recien registrado
            
                ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM users WHERE username = '%s'",_username));
                resultSet.next();
                String userID = resultSet.getString("id");
            
                // Iniciación de registro en tabla scoresData
            
                statement.execute(String.format("INSERT INTO scoresdata VALUES (null,'%s',0,0,0,0,0,'%s')",userID,_username));               
                
                JOptionPane.showMessageDialog(null,"Usuario registrado exitosamente! Ya puede iniciar sesión");
                
                if(this.container != null){
                    this.container.dispose();
                }
                
                connection.close();
                
                
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"[ERROR] No se pudo registrar el usuario. Intentelo de nuevo más tarde.");
                System.out.println(e);
            }
            
            connection.close();
            
        }else{
            JOptionPane.showMessageDialog(null,"[ERROR] Este usuario ya existe");
        }
    }
    
    public boolean checkUserExistence(String _username, String _password) throws ClassNotFoundException, SQLException, SQLException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(this.bdHost, this.bdUser, this.bdPassword);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(String.format("SELECT * FROM users WHERE username = '%s'",_username));
        resultset.next();
        try{
            if(resultset.getString("username") != ""){
                System.out.println("Usuario Existente");
                connection.close();
                return true;
            }
        }catch(SQLException e){
            System.out.println("Usuario No Existente");
            connection.close();
            return false;
        }
        connection.close();
        return false;
    }
    
     public ResultSet loginUser(String _username) throws SQLException, ClassNotFoundException{
        
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(this.bdHost, this.bdUser, this.bdPassword);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(String.format("SELECT * FROM `users` WHERE username = '%s'",_username));
        resultset.next();
        return resultset;
    }
    
    public void updateScore(String _game, String _username, int _newScore) throws ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(this.bdHost, this.bdUser, this.bdPassword);
        Statement statement = connection.createStatement();
        
        ResultSet resultset = statement.executeQuery(String.format("SELECT * FROM `scoresdata` WHERE username = '%s'",_username));
        resultset.next();
        
        if(_newScore > Integer.parseInt(resultset.getString(_game))){
            statement.executeUpdate(String.format("UPDATE `scoresdata` SET %s = %s WHERE username = '%s'",_game,_newScore,_username));
        }
        
        connection.close();
    }
    
    public void retrieveScore(String _game, DefaultTableModel _tableModel) throws ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(this.bdHost, this.bdUser, this.bdPassword);
        
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(String.format("SELECT * FROM `scoresdata` ORDER BY %s DESC LIMIT 0,10",_game,_game));
        
        String[] data = new String[3];
        int i = 1;
        
        while(resultset.next()){
            data[0] = Integer.toString(i);
            data[1] = resultset.getString("username");
            data[2] = resultset.getString(_game);
            _tableModel.addRow(data);
            i++;
        }
        
        connection.close();
        
    }
    
}
