package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class DepartamentoDAO {
    /*private Connection connection;*/

    FuncionarioDAO daoF = ApplicationContext.getFuncionarioDAO();
    LocalDAO daoL = ApplicationContext.getLocalDAO();

    Scanner scannerPais = new Scanner(System.in);
    int opcaoOperacao = 0;

    /*public DepartamentoDAO() {
        new ConnectionFactory();
		this.connection = ConnectionFactory.getConnection();
    }*/

    public void insert(Departamento d) {
        //daoR.getAll();

        int idGerente = d.getGerente().getIdFuncionario();
        int idLocal = d.getLocal().getIdLocal();

        String sql = "insert into t_departamento (nomeDpto, gerente, local)"
                + " values (?, ?, ?)";

        try (
        	Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement(sql)) 
        {
            stmt.setString(1, d.getNomeDpto());
            stmt.setInt(2, idGerente);
            stmt.setInt(3, idLocal);

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAll() { // SELECT
        String sql = "SELECT d.idDpto, d.nomeDpto, f.primeiroNome, f.ultimoNome, l.enderecoRua, l.cidade, l.estadoProvincia " +
                "FROM t_departamento d " +
                "JOIN t_funcionario f ON d.gerente = f.idFuncionario " +
                "JOIN t_local l ON d.local = l.idLocal " +
                "ORDER BY d.idDpto ASC";

        try (
        	Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) 
        {

            System.out.printf("%-10s %-30s %-20s %-20s %-20s %-20s %-20s\n", "ID", "NOME", "1º NOME GERENTE", "2º NOME",
                    "ENDEREÇO", "CIDADE", "ESTADO");

            while (rs.next()) {
                int id = rs.getInt("idDpto");
                String nomeDpto = rs.getString("nomeDpto");
                String primeiroNome = rs.getString("primeiroNome");
                String ultimoNome = rs.getString("ultimoNome");
                String enderecoRua = rs.getString("enderecoRua");
                String cidade = rs.getString("cidade");
                String estadoProvincia = rs.getString("estadoProvincia");
                
                if (ultimoNome == null) {
                	ultimoNome = "";
                }

                String linha = String.format("%-10s %-30s %-20s %-20s %-20s %-20s %-20s", id, nomeDpto, primeiroNome,
                        ultimoNome, enderecoRua, cidade, estadoProvincia);
                System.out.println(linha);
            }
            System.out.printf("%n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*public void getAll() { // SELECT
        String sql = "SELECT d.idDpto AS \"ID\", d.nomeDpto AS \"NOME\", f.primeiroNome AS \"1º NOME GERENTE\", " +
                "f.ultimoNome AS \"2º NOME\", l.enderecoRua AS \"ENDEREÇO\", l.cidade AS \"CIDADE\", " +
                "l.estadoProvincia AS \"ESTADO\" " +
                "FROM t_departamento d " +
                "JOIN t_funcionario f ON d.gerente = f.idFuncionario " +
                "JOIN t_local l ON d.local = l.idLocal " +
                "ORDER BY d.idDpto ASC";

        try (
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) 
        {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Imprimir os cabeçalhos das colunas
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("|%-25s", metaData.getColumnLabel(i));
            }
            System.out.println("|");

            // Imprimir os dados
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    if (value == null) {
                        value = ""; // Converter "null" para uma string vazia
                    }
                    System.out.printf("|%-25s", value);
                }
                System.out.println("|");
            }

            System.out.printf("%n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void update(Departamento d) {
        String sql = "update t_departamento set nomeDpto=?, gerente=?, local=?"
                + " where idDpto=?";
        try (
        	Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement(sql)) 
        {
            stmt.setString(1, d.getNomeDpto());
            stmt.setInt(2, d.getGerente().getIdFuncionario());
            stmt.setInt(3, d.getLocal().getIdLocal());
            stmt.setInt(4, d.getIdDpto());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Departamento d) {
        try (Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement("delete from t_departamento where idDpto=?")) 
        {
            stmt.setLong(1, d.getIdDpto());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
