import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;



public class conexaoSQL {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/trabalho3bd";
        String usuario = "root";
        String senha = "1234";
        try {
            //conectar no banco de dados
            Connection conexao = DriverManager.getConnection(url,usuario,senha);
            System.out.println("conectado");
            conexao.close();


            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
