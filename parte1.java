import java.sql.*;

public class parte1{
    public static void main(String[] args) throws SQLException {
        Connection conexao = conexaoSQL.conectarbd();
        try {
            //consulta transacoes cliente 1
            String[] clientes = {"jeferson", "william"}; // Lista de clientes a serem consultados

            for (String cliente : clientes) {
                String sqlCliente = "SELECT " +
                        "c.nomeCliente AS Cliente, " +
                        "cc.nroContaCliente AS Conta, " +
                        "tc.tipoConta AS tipoConta, " +
                        "t.nomeTransacao AS Transacao, " +
                        "t.valorTransacao AS Valor, " +
                        "t.dataTransacao AS Data, " +
                        "uf.nomeUF AS UF " +
                        "FROM Cliente c " +
                        "JOIN contaCliente cc ON c.idCliente = cc.idCliente " +
                        "JOIN tipoConta tc ON cc.idTipoConta = tc.idtipoConta " +
                        "JOIN transacao t ON cc.idcontaCliente = t.idContaCliente " +
                        "JOIN Endereco e ON c.idEndereco = e.idEndereco " +
                        "JOIN cidade cid ON e.idCidade = cid.idcidade " +
                        "JOIN unidadeFederativa uf ON cid.siglaUF = uf.siglaUF " +
                        "WHERE " +
                        "uf.siglaUF = 'PR' AND c.nomeCliente = ?"; // Usando ? como marcador de parâmetro

                PreparedStatement statementCliente = conexao.prepareStatement(sqlCliente);
                statementCliente.setString(1, cliente); // Substituindo o parâmetro na consulta

                ResultSet resultSetCliente = statementCliente.executeQuery();

                System.out.println("-------Extrato Bancario para o/a cliente " + cliente + "-------");
                while (resultSetCliente.next()) {
                    String clienteNome = resultSetCliente.getString("Cliente");
                    String conta = resultSetCliente.getString("Conta");
                    String tipoConta = resultSetCliente.getString("tipoConta");
                    String transacao = resultSetCliente.getString("Transacao");
                    double valor = resultSetCliente.getDouble("Valor");
                    Date data = resultSetCliente.getDate("Data");
                    System.out.println("Cliente: " + clienteNome + ", Conta: " + conta + ", Tipo de Conta: " + tipoConta +
                            ", Transação: " + transacao + ", Valor: " + valor + ", Data: " + data);

                }
                System.out.println("\n");
                resultSetCliente.close();
                statementCliente.close();
            }

            // Consulta de investimentos cliente 1
            for (String cliente : clientes) {
                String sqlClienteInvestimento = "SELECT " +
                        "c.nomeCliente AS Cliente, " +
                        "cc.nroContaCliente AS Conta, " +
                        "tc.tipoConta AS tipoConta, " +
                        "i.nomeInvestimento AS Investimento, " +
                        "i.valorInvestimento AS Valor, " +
                        "i.dataInvestimento AS Data, " +
                        "uf.nomeUF AS UF " +
                        "FROM Cliente c " +
                        "JOIN contaCliente cc ON c.idCliente = cc.idCliente " +
                        "JOIN tipoConta tc ON cc.idTipoConta = tc.idtipoConta " +
                        "JOIN investimento i ON cc.idcontaCliente = i.idContaCli " +
                        "JOIN Endereco e ON c.idEndereco = e.idEndereco " +
                        "JOIN cidade cid ON e.idCidade = cid.idcidade " +
                        "JOIN unidadeFederativa uf ON cid.siglaUF = uf.siglaUF " +
                        "WHERE " +
                        "uf.siglaUF = 'PR' AND c.nomeCliente = ?";

                PreparedStatement statementClienteInvestimento = conexao.prepareStatement(sqlClienteInvestimento);
                statementClienteInvestimento.setString(1, cliente); // Substituindo o parâmetro na consulta

                ResultSet resultSetClienteInvestimento = statementClienteInvestimento.executeQuery();

                System.out.println("-------Lista de Investimentos para o/a cliente: " + cliente + "-------");
                while (resultSetClienteInvestimento.next()) {
                    String clienteI = resultSetClienteInvestimento.getString("Cliente");
                    String contaI = resultSetClienteInvestimento.getString("Conta");
                    String tipoContaI = resultSetClienteInvestimento.getString("tipoConta");
                    String investimento = resultSetClienteInvestimento.getString("Investimento");
                    double valorI = resultSetClienteInvestimento.getDouble("Valor");
                    Date dataI = resultSetClienteInvestimento.getDate("Data");
                    System.out.println("Cliente: " + clienteI + ", Conta: " + contaI + ", Tipo de Conta: " + tipoContaI +
                            ", Investimento: " + investimento + ", Valor: " + valorI + ", Data: " + dataI);
                }
                System.out.println("\n");
                resultSetClienteInvestimento.close();
                statementClienteInvestimento.close();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try{
                //fechar conexao com o banco de dados
                conexao.close();
            } catch (SQLException e) {
                System.out.println("erro ao fechar a conexao com o banco de dados" + e.getMessage());
            }
        }
    }
}
