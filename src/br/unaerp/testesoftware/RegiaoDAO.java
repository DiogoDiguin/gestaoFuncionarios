package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegiaoDAO {
    private Connection connection;
	
    public RegiaoDAO() {
        new ConnectionFactory();
		this.connection = ConnectionFactory.getConnection();
    }
	
    public void insert(Regiao r) {
        String sql = "insert into t_regiao (nomeRegiao)" + "values (?)";
	
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1,r.getNomeRegiao());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
    public ResultSet getAll() { //SELECT
        String sql = "SELECT * FROM t_regiao";
		
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.printf("%-10s %-20s \n", "ID", "Nome");

            while (rs.next()) {
                int id = rs.getInt("idRegiao");
                String nomeRegiao = rs.getString("nomeRegiao");

                String linha = String.format("%-10d %-20s", id, nomeRegiao);
                System.out.println(linha);
            }
            System.out.printf("%n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
    public void update(Regiao r) {
        String sql = "update t_regiao set nomeRegiao=?" + "where idRegiao=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.getNomeRegiao());
            stmt.setInt(2, r.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
    public void delete(Regiao r) {
        try (
        	PreparedStatement stmtUpdatePais = connection.prepareStatement("update t_pais set regiao=1 where regiao=?");
            PreparedStatement stmtDeleteRegiao = connection.prepareStatement("delete from t_regiao where idRegiao=?");
        ) {
        	stmtUpdatePais.setLong(1, r.getId());
            stmtUpdatePais.execute();
            
            stmtDeleteRegiao.setLong(1, r.getId());
            stmtDeleteRegiao.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
    public ResultSet mediaSalarial() {
        String sql = "SELECT r.nomeRegiao, AVG(rf.salario) AS mediaSalarial"
                + " FROM t_regiaofuncionario rf"
                + " JOIN t_regiao r ON rf.idRegiao = r.idRegiao"
                + " GROUP BY r.idRegiao;";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.printf("%-10s %-20s\n", "ID", "MÉDIA SALARIAL");

            while (rs.next()) {
                String nome = rs.getString("nomeRegiao");
                String mediaSalarial = rs.getString("mediaSalarial");

                String linha = String.format("%-10s %-20s", nome, mediaSalarial);
                System.out.println(linha);
            }
            System.out.printf("%n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
