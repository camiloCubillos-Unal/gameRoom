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

/**
 *
 * @author ccubi
 */
public class Bd_gestor {
    
    String bdHost = "jdbc:mysql://localhost:3306/gameroom";
    String bdUser;
    String bdPassword;
    
    Connection connection;
        
    
    public Bd_gestor(String _username, String _password){

        this.bdUser = _username;
        this.bdPassword = _password;
        
        this.bdHost = "jdbc:mysql://btjjrb5hl40a5n82ljro-mysql.services.clever-cloud.com:3306/btjjrb5hl40a5n82ljro";
      
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Bd_gestor bd_gestor = new Bd_gestor("ueizgfjgoc2gxumn", "u22yMCtXEBlTA4pMsMjI");    
        bd_gestor.updateScore("flappy", 10, 101010);
    }
    
    public void registerUser(String _username, String _password) throws ClassNotFoundException, ClassNotFoundException, SQLException, SQLException{
        
        
        if(!checkUserExistence(_username, _password, 0)){
            
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
            
                statement.execute(String.format("INSERT INTO scoresdata VALUES (null,'%s',0,0,0,0)",userID));               
                
                
                System.out.println("[+] Usuario registrado exitosamente!");
                
            }catch (SQLException e) {
                System.out.println("[ERROR] No se pudo registrar el usuario");
                System.out.println(e);
            }
            

            
        }else{
            
            System.out.println("[ERROR] Este usuario ya existe");
            
        }
    }
    
    public boolean checkUserExistence(String _username, String _password, int mode) throws ClassNotFoundException, SQLException, SQLException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(this.bdHost, this.bdUser, this.bdPassword);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(String.format("SELECT * FROM users WHERE username = '%s'",_username));
        resultset.next();
        try{
            if(resultset.getString("username") != ""){
                System.out.println("Usuario Existente");
                if(mode == 1){
                    loginUser(_username, _password, resultset);
                }
                return true;
            }
        }catch(SQLException e){
            System.out.println("Usuario No Existente");
            return false;
        }
        return false;
    }
    
    public void loginUser(String _username, String _password, ResultSet resultset) throws SQLException{
        
        
        if(resultset.getString("password").equals(_password)){
                System.out.println("[202] Sesión iniciada");
            }else{
                System.out.println("[ERROR] Contraseña o usuario incorrecto");
            }
    }
    
    public void updateScore(String _game, int _id, int _newScore) throws ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(this.bdHost, this.bdUser, this.bdPassword);
        Statement statement = connection.createStatement();
        
        ResultSet resultset = statement.executeQuery(String.format("SELECT * FROM scoresdata WHERE id_usuario = %s",_id));
        resultset.next();
        
        if(_newScore > Integer.parseInt(resultset.getString(_game))){
            statement.executeUpdate(String.format("UPDATE scoresdata SET %s = %s WHERE id_usuario = %s",_game,_newScore,_id));
        }
    }
    
    public int getScore(String _game , int _id) throws ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(this.bdHost, this.bdUser, this.bdPassword);
        Statement statement = connection.createStatement();
        
        int score;
        
        ResultSet resultset = statement.executeQuery(String.format("SELECT * FROM scoresdata WHERE id_usuario = %s",_id));
        resultset.next();
        
        return Integer.parseInt(resultset.getString(_game));
        
    }
    
}
