import java.sql.*;

public class parte2 {
    // Configurações do banco de dados
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "postgres";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Registrando o driver JDBC
            Class.forName(JDBC_DRIVER);

            // Abrindo uma conexão com o banco de dados
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Executando operações
            stmt = conn.createStatement();

            // Inserindo um novo registro
            String insertQuery = "INSERT INTO contaCliente (cliente,agencia, tipoConta, banco, nroContaCliente, saldoConta) " +
                    "VALUES ((1, 'mario', 'Silva', '24422'),(1, 'Agência 020'), (1, 'Conta Corrente'), (1, 'Banco do brasil'), '123456789', 1000.00)";
            stmt.executeUpdate(insertQuery);

            // Imprimindo os dados inseridos
            System.out.println("Dados inseridos:");
            rs = stmt.executeQuery("SELECT * FROM contaCliente");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idcontaCliente"));
                System.out.println("Cliente: " + rs.getString("cliente"));
                System.out.println("Agência: " + rs.getString("agencia"));
                System.out.println("Tipo de Conta: " + rs.getString("tipoConta"));
                System.out.println("Banco: " + rs.getString("banco"));
                System.out.println("Número da Conta: " + rs.getString("nroContaCliente"));
                System.out.println("Saldo: " + rs.getBigDecimal("saldoConta"));
                System.out.println("--------------------------");
            }
            rs.close();

            // Atualizando um registro
            String updateQuery = "UPDATE contaCliente SET saldoConta = 1500.00 WHERE idcontaCliente = 1";
            stmt.executeUpdate(updateQuery);

            // Imprimindo os dados atualizados
            System.out.println("Dados após atualização:");
            rs = stmt.executeQuery("SELECT * FROM contaCliente WHERE idcontaCliente = 1");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idcontaCliente"));
                System.out.println("Saldo Atualizado: " + rs.getBigDecimal("saldoConta"));
                System.out.println("--------------------------");
            }
            rs.close();

            // Deletando um registro
            String deleteQuery = "DELETE FROM contaCliente WHERE idcontaCliente = 1";
            stmt.executeUpdate(deleteQuery);

            // Imprimindo os dados restantes após exclusão
            System.out.println("Dados restantes após exclusão:");
            rs = stmt.executeQuery("SELECT * FROM contaCliente");
            rs.close();

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Lidando com erros de SQL
            se.printStackTrace();
        } catch (Exception e) {
            // Lidando com erros genéricos
            e.printStackTrace();
        } finally {
            // Fechando recursos de forma segura
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}