import java.sql.*;

public class parte1{
    public static void main(String[] args) throws SQLException {
        Connection conexao = conexaoSQL.conectarbd();
        try{
          System.out.println("teste");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try{
                //fechar conexao com o banco de dados
                conexao.close();
                System.out.println("conexao fechada");
            } catch (SQLException e) {
                System.out.println("erro ao fechar a conexao com o banco de dados" + e.getMessage());
            }
        }
    }
}
