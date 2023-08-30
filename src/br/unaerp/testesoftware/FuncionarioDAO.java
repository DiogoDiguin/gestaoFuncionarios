package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionarioDAO {
	private Connection connection;
	
	public FuncionarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Funcionario f) {
		String sqlFunc = "INSERT INTO t_funcionario (primeiroNome, ultimoNome, salario) VALUES (?,?,?)";

		try {
		    PreparedStatement stmtFunc = connection.prepareStatement(sqlFunc, Statement.RETURN_GENERATED_KEYS);
		    stmtFunc.setString(1, f.getPrimeiroNome());
		    stmtFunc.setString(2, f.getUltimoNome());
		    stmtFunc.setFloat(3, f.getSalario());
		    
		    stmtFunc.execute();
		    
		    ResultSet generatedKeys = stmtFunc.getGeneratedKeys();
		    int generatedId = -1;
		    if (generatedKeys.next()) {
		        generatedId = generatedKeys.getInt(1);
		    }
		    
		    stmtFunc.close();
		    
		    // Utilize o ID gerado ao inserir na tabela t_regiaofuncionarios
		    if (generatedId != -1) {
		        String sqlRF = "INSERT INTO t_regiaofuncionarios (idFuncionario, idRegiao, salario) VALUES (?,?,?)";
		        PreparedStatement stmtRF = connection.prepareStatement(sqlRF);
		        stmtRF.setInt(1, generatedId); // Use o ID gerado
		        stmtRF.setInt(2, f.getRegiao());
		        stmtRF.setFloat(3, f.getSalario());
		        
		        stmtRF.execute();
		        stmtRF.close();
		    }
		} catch (SQLException e) {
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
	    String sql = "update t_funcionario set primeiroNome=?, ultimoNome=?, salario=? where idFuncionario=?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, funcionario.getPrimeiroNome());
	        stmt.setString(2, funcionario.getUltimoNome());
	        stmt.setFloat(3, funcionario.getSalario());
	        stmt.setLong(4, funcionario.getIdFuncionario());
	        stmt.execute();
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public void delete(Funcionario funcionario) {
	    try {
	        PreparedStatement stmt = connection.prepareStatement("delete from t_funcionario where idFuncionario=?");
	        stmt.setLong(1, funcionario.getIdFuncionario());
	        stmt.execute();
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public ResultSet mediaSalarial() {
		String sql = "SELECT r.nomeRegiao, AVG(rf.salario) AS mediaSalarial"
				+ " FROM t_regiaofuncionarios rf"
				+ " JOIN t_regiao r ON rf.idRegiao = r.idRegiao"
				+ " GROUP BY r.idRegiao;";

	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery(sql);

	        System.out.printf("%-10s %-20s\n", "ID", "MÉDIA SALARIAL");

	        while (rs.next()) {
	        	String nome = rs.getString("nomeRegiao");
	            String mediaSalarial = rs.getString("mediaSalarial");

	            String linha = String.format("%-10s %-20s", nome, mediaSalarial);
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
}
