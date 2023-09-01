package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PaisDAO {
    /*private Connection connection;*/

    RegiaoDAO daoR = ApplicationContext.getRegiaoDAO();

    Scanner scannerRegiao = new Scanner(System.in);
    int opcaoOperacao = 0;

    /*public PaisDAO() {
        new ConnectionFactory();
		this.connection = ConnectionFactory.getConnection();
    }*/

    public void insert(Pais p) {
        //daoR.getAll();

        // Obter o id da região a partir da instância de Regiao
        long idRegiao = p.getRegiao().getId();

        String sql = "insert into t_pais (nomePais, regiao) values (?, ?)";

        try (
            Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement(sql)) 
        {
            stmt.setString(1, p.getNomePais());
            stmt.setLong(2, idRegiao);  // Usar o id da região

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAll() { // SELECT
        String sql = "SELECT p.idPais, p.nomePais, r.nomeRegiao " +
                "FROM t_pais p " +
                "JOIN t_regiao r ON p.regiao = r.idRegiao " +
                "ORDER BY p.idPais ASC";

        try (
            Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) 
        {

            System.out.printf("%-10s %-20s %-20s \n", "ID", "NOME", "REGIÃO");

            while (rs.next()) {
                int id = rs.getInt("idPais");
                String nomePais = rs.getString("nomePais");
                String nomeRegiao = rs.getString("nomeRegiao");

                String linha = String.format("%-10d %-20s %-20s", id, nomePais, nomeRegiao);
                System.out.println(linha);
            }
            System.out.printf("%n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Pais p) {
        String sql = "update t_pais set nomePais=?, regiao=? where idPais=?";
        try (
            Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement(sql)) 
        {
            stmt.setString(1, p.getNomePais());
            stmt.setLong(2, p.getRegiao().getId());  // Usar o ID da região
            stmt.setInt(3, p.getIdPais());  // Supondo que getIdPais() retorna o ID do país
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Pais p) {
        try (
        	Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmtUpdateLocal = connection.prepareStatement("update t_local set pais=1 where pais=?");        		
        	PreparedStatement stmtDeletePais = connection.prepareStatement("delete from t_pais where idPais=?"))
        {	
        	stmtUpdateLocal.setLong(1, p.getIdPais());
        	stmtUpdateLocal.execute();
            
            stmtDeletePais.setLong(1, p.getIdPais());
            stmtDeletePais.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectDepartamentos() { // SELECT
        /*String sql = "SELECT p.nomePais, GROUP_CONCAT(d.nomeDpto SEPARATOR ', ') AS Departamentos "
                + "FROM t_departamento d "
                + "JOIN t_local l ON d.local = l.idLocal "
                + "JOIN t_pais p ON l.pais = p.idPais "
                + "GROUP BY p.nomePais;";*/

        String sql = "SELECT p.nomePais, d.nomeDpto "
                + "FROM t_pais p "
                + "JOIN t_local l ON p.idPais = l.pais "
                + "JOIN t_departamento d ON l.idLocal = d.local "
                + "ORDER BY p.nomePais, d.nomeDpto;";

        try (
            Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) 
        {

            System.out.printf("%-25s %-25s\n", "PAÍS", "DEPARTAMENTO");

            while (rs.next()) {
                String nomePais = rs.getString("nomePais");
                String nomeDpto = rs.getString("nomeDpto");

                String linha = String.format("%-25s %-25s", nomePais, nomeDpto);
                System.out.println(linha);
            }
            System.out.printf("%n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
