package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PaisDAO {
private Connection connection;

	RegiaoDAO daoR = new RegiaoDAO();

	Scanner scannerRegiao = new Scanner(System.in);
	int opcaoOperacao = 0;
	
	public PaisDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Pais p) {
		//daoR.getAll();
	    	    
	    // Obter o id da região a partir da instância de Regiao
	    long idRegiao = p.getRegiao().getId();
	    
	    String sql = "insert into t_pais (nomePais, regiao) values (?, ?)";
	    
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        
	        stmt.setString(1, p.getNomePais());
	        stmt.setLong(2, idRegiao);  // Usar o id da região
	        
	        stmt.execute();
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public ResultSet getAll() { // SELECT
	    String sql = "SELECT p.idPais, p.nomePais, r.nomeRegiao " +
	    		"FROM t_pais p " +
	    		"JOIN t_regiao r ON p.regiao = r.idRegiao " +
	    		"ORDER BY p.idPais ASC";

	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery(sql);

	        System.out.printf("%-10s %-20s %-20s \n", "ID", "NOME", "REGIÃO");

	        while (rs.next()) {
	            int id = rs.getInt("idPais");
	            String nomePais = rs.getString("nomePais");
	            String nomeRegiao = rs.getString("nomeRegiao");

	            String linha = String.format("%-10d %-20s %-20s", id, nomePais, nomeRegiao);
	            System.out.println(linha);
	        }
	        System.out.printf("%n");

	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
	public void update(Pais p) {
	    String sql = "update t_pais set nomePais=?, regiao=? where idPais=?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, p.getNomePais());
	        stmt.setLong(2, p.getRegiao().getId());  // Usar o ID da região
	        stmt.setInt(3, p.getIdPais());  // Supondo que getIdPais() retorna o ID do país
	        stmt.execute();
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	
	public void delete(Pais p) {
	    try {
	        PreparedStatement stmt = connection.prepareStatement("delete from t_pais where idPais=?");
	        stmt.setLong(1, p.getIdPais());
	        stmt.execute();
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
}
