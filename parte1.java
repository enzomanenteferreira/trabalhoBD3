import java.sql.*;

public class parte1{
    public static void main(String[] args){
        Connection conexao = conexaoSQL.conectarbd();
        try{
          System.out.println("teste");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conexaoSQL.fecharConexao();
        }
    }
}
