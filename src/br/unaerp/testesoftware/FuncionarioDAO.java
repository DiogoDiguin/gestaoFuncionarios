package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO {
	private Connection connection;
	
	public FuncionarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Funcionario f) {
		String sql = "insert into t_funcionario (primeiroNome,ultimoNome,salario" + "values (?,?,?)";
	
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

	        stmt.setString(1,f.getPrimeiroNome());
	        stmt.setString(2,f.getUltimoNome());
	        stmt.setFloat(3,f.getSalario());
	        
	        stmt.execute();
	        stmt.close();
	    } 
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ResultSet getAll() { //SELECT
		String sql = "SELECT * FROM t_funcionario";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
		    ResultSet rs = stmt.executeQuery(sql);

		    System.out.printf("%-10s %-20s %-20s %-20s\n", "ID", "1º Nome", "2º Nome", "Salário R$");
		    
		    while (rs.next()) {
		        int id = rs.getInt("idFuncionario");
		        String Pnome = rs.getString("primeiroNome");
		        String Unome = rs.getString("ultimoNome");
		        if (Unome == null) {
		        	Unome = "";
		        }
		        String salario = rs.getString("salario");

		        String linha = String.format("%-10d %-20s %-20s %-20s", id, Pnome, Unome, salario);
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
	
	public void update(Funcionario funcionario) {
	    String sql = "update t_funcionario set primerioNome=?, ultimoNome=?, salario=?," + "where id=?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, funcionario.getPrimeiroNome());
	        stmt.setString(2, funcionario.getUltimoNome());
	        stmt.setFloat(3, funcionario.getSalario());
	        stmt.setFloat(4, funcionario.getIdFuncionario());
	        stmt.execute();
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public void delete(Funcionario funcionario) {
	    try {
	        PreparedStatement stmt = connection.prepareStatement("delete from t_funcionario where id=?");
	        stmt.setLong(1, funcionario.getIdFuncionario());
	        stmt.execute();
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
}
