package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DepartamentoDAO {
private Connection connection;

	FuncionarioDAO daoF = new FuncionarioDAO();
	LocalDAO daoL = new LocalDAO();

	Scanner scannerPais = new Scanner(System.in);
	int opcaoOperacao = 0;
	
	public DepartamentoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Departamento d) {
		//daoR.getAll();
		
	    int idPais = l.getPais().getIdPais();
	    
	    String sql = "insert into t_local (enderecoRua, codigoPostal, cidade, estadoProvincia, pais)"
	    		+ " values (?, ?, ?, ?, ?)";
	    
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        
	        stmt.setString(1, l.getEnderecoRua());
	        stmt.setString(2, l.getCodigoPostal());
	        stmt.setString(3, l.getCidade());
	        stmt.setString(4, l.getEstado());
	        stmt.setInt(5, idPais);
	        
	        stmt.execute();
	        stmt.close();
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

	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery(sql);

	        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s %-20s\n", "ID", "NOME", "NOME GERENTE", "NOME GERENTE", 
	        		"ENDEREÇO", "CIDADE", "ESTADO");

	        while (rs.next()) {
	            int id = rs.getInt("idDpto");
	            String nomeDpto = rs.getString("nomeDpto");
	            String primeiroNome = rs.getString("primeiroNome");
	            String ultimoNome = rs.getString("ultimoNome");
	            String enderecoRua = rs.getString("enderecoRua");
	            String cidade = rs.getString("cidade");
	            String estadoProvincia = rs.getString("estadoProvincia");

	            String linha = String.format("%-10s %-20s %-20s %-20s %-20s %-20s %-20s", id, nomeDpto, primeiroNome, 
	            		ultimoNome, enderecoRua, cidade, estadoProvincia);
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

	
	public void update(Local l) {
	    String sql = "update t_local set enderecoRua=?, codigoPostal=?, cidade=?, estadoProvincia=?, pais=?"
	    		+ " where idLocal=?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, l.getEnderecoRua());
	        stmt.setString(2, l.getCodigoPostal());
	        stmt.setString(3, l.getCidade());
	        stmt.setString(4, l.getEstado());
	        stmt.setInt(5, l.getPais().getIdPais());
	        stmt.setInt(6, l.getIdLocal());
	        stmt.execute();
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	
	public void delete(Local l) {
	    try {
	        PreparedStatement stmt = connection.prepareStatement("delete from t_local where idLocal=?");
	        stmt.setLong(1, l.getIdLocal());
	        stmt.execute();
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
}
