import java.sql.*;

public class parte1{
    public static void main(String[] args) throws SQLException {
        Connection conexao = conexaoSQL.conectarbd();
        try {
            // consulta cliente
            String sqlCliente = "SELECT" +
                    "c.nomeCliente AS Cliente, " +
                    "cc.nroContaCliente AS Conta, " +
                    "tc.tipoConta AS Transacao, " +
                    "t.nomeTransacao AS Transacao, " +
                    "t.valorTransacao AS Valor, " +
                    "t.dataTransacao AS Data, " +
                    "t.dataTransacao AS Data, " +
                    "uf.nomeUF AS UF " +
                    "FROM Cliente c " +
                    "JOIN contaCliente cc ON c.idCliente = cc.idCliente" +
                    "JOIN transacao t ON cc.idcontaCliente = t.idContaCliente" +
                    "JOIN Endereco e ON c.idEndereco = e.idEndereco" +
                    "JOIN cidade cid ON e.idCidade = cid.idcidade " +
                    "JOIN unidadeFederativa uf ON cid.siglaUF = uf.siglaUF " +
                    "WHERE " +
                    "uf.siglaUF = 'PR' AND c.nomeCliente = 'jeferson' ";

            Statement statementCliente = conexao.createStatement();
            ResultSet resultSetCliente = statementCliente.executeQuery(sqlCliente);

            System.out.println("Extrato Bancario\n");
            while (resultSetCliente.next()) {
                String cliente = resultSetCliente.getString("Cliente");
                String conta = resultSetCliente.getString("Conta");
                String tipoConta = resultSetCliente.getString("tipoConta");
                String transacao = resultSetCliente.getString("Transacao");
                double valor = resultSetCliente.getDouble("Valor");
                Date data = resultSetCliente.getDate("Data");
                String uf = resultSetCliente.getString("UF");

                resultSetCliente.close();
                statementCliente.close();
                System.out.println("Cliente: " + cliente + ", Conta: " + conta + ", Tipo de Conta: " + tipoConta +
                        ", Transação: " + transacao + ", Valor: " + valor + ", Data: " + data + ", UF: " + uf);
            }
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
