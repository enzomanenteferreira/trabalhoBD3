import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conexaoSQL {
       private static final String url = "jdbc:mysql://localhost:3306/trabalho3bd";
       private static final String usuario = "root";
       private static final String senha = "1234";
       private static Connection conexao;
      public static Connection conectarbd() {
          try {
              //conectar no banco de dados
              conexao = DriverManager.getConnection(url, usuario, senha);
              System.out.println("conectado");

          } catch(SQLException e){
              System.out.println("erro ao se conectar ao banco" + e.getMessage());
          }
          return conexao;
      }

      public static void fecharConexao(){
          try{
              //fechar conexao com o banco de dados
              conexao.close();
              System.out.println("conexao fechada");
          } catch (SQLException e) {
              System.out.println("erro ao fechar a conexao com o banco de dados" + e.getMessage());
          }
      }
}
