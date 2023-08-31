package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LocalDAO {
private Connection connection;

	PaisDAO daoP = new PaisDAO();

	Scanner scannerPais = new Scanner(System.in);
	Scanner entradaDpto = new Scanner(System.in);
	int opcaoOperacao = 0;
	
	public LocalDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Local l) {
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
	    String sql = "SELECT l.idLocal, l.enderecoRua, l.codigoPostal, l.cidade, l.estadoProvincia, p.nomePais " +
	    		"FROM t_local l " +
	    		"JOIN t_pais p ON l.pais = p.idPais " +
	    		"ORDER BY l.idLocal ASC";

	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery(sql);

	        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s\n", "ID", "ENDEREÇO", "CÓDIGO", 
	        		"CIDADE", "ESTADO", "PAÍS");

	        while (rs.next()) {
	            int id = rs.getInt("idLocal");
	            String enderecoRua = rs.getString("enderecoRua");
	            String codigoPostal = rs.getString("codigoPostal");
	            String cidade = rs.getString("cidade");
	            String estadoProvincia = rs.getString("estadoProvincia"); // Correção aqui
	            String nomePais = rs.getString("nomePais"); // Nome da coluna corrigido

	            String linha = String.format("%-10s %-20s %-20s %-20s %-20s %-20s", id, enderecoRua, codigoPostal, 
	                    cidade, estadoProvincia, nomePais);
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
	
	public ResultSet listarFuncionarios(Local l) {
	    String sql = "select * from t_departamento where local=?";

	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setInt(1, l.getIdLocal()); // Define o valor do parâmetro
	        ResultSet rs = stmt.executeQuery();

	        if (!rs.next()) {
	            System.out.println("Nenhum departamento encontrado.");
	        } else {
	            System.out.printf("%-10s %-20s\n", "ID", "NOME DEPARTAMENTO");

	            do {
	                int idDpto = rs.getInt("idDpto");
	                String nomeDpto = rs.getString("nomeDpto");

	                String linha = String.format("%-10s %-20s", idDpto, nomeDpto);
	                System.out.println(linha);
	            } while (rs.next());

	            // Escolha do departamento
	            Scanner entradaDpto = new Scanner(System.in);
	            System.out.print("Escolha o ID do Departamento: ");
	            int idDepartamentoEscolhido = entradaDpto.nextInt();
	            
	            /* Exibe o GERENTE */
	            String sqlGerente = "SELECT d.idDpto, d.nomeDpto, f.primeiroNome, f.ultimoNome FROM t_departamento d " +
	                                "LEFT JOIN t_funcionario f ON d.gerente = f.idFuncionario where d.idDpto = ?";
	            
	            try {
	                PreparedStatement stmtGerente = connection.prepareStatement(sqlGerente);
	                stmtGerente.setInt(1, idDepartamentoEscolhido);
	                ResultSet rsGerente = stmtGerente.executeQuery();

	                System.out.printf("\nGERENTE: ");

	                while (rsGerente.next()) {
	                    String pNome = rsGerente.getString("primeiroNome");
	                    String uNome = rsGerente.getString("ultimoNome");
	                    if (uNome == null) {
	                    	uNome = "";
	                    }

	                    String linhaGerente = String.format("%-10s %-10s", pNome, uNome);
	                    System.out.println(linhaGerente);
	                }

	                rsGerente.close();
	                stmtGerente.close();
	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            /* FINAL Exibe o GERENTE */
	            
	            /* Listagem dos funcionários do departamento */
	            String sqlFuncionarios = "SELECT primeiroNome, ultimoNome, salario " +
	                                     "FROM t_funcionario " +
	                                     "WHERE departamento = ?";

	            try {
	                PreparedStatement stmtFunc = connection.prepareStatement(sqlFuncionarios);
	                stmtFunc.setInt(1, idDepartamentoEscolhido); // Define o valor do parâmetro
	                ResultSet rsFunc = stmtFunc.executeQuery();

	                System.out.println("FUNCIONÁRIOS: ");
	                System.out.printf("%-10s %-20s %-20s\n", "1º Nome", "2º Nome", "SALÁRIO");

	                while (rsFunc.next()) {
	                    String pNome = rsFunc.getString("primeiroNome");
	                    String uNome = rsFunc.getString("ultimoNome");
	                    Float salario = rsFunc.getFloat("salario");

	                    String linhaFunc = String.format("%-10s %-20s %-20s", pNome, uNome, salario);
	                    System.out.println(linhaFunc);
	                }

	                rsFunc.close();
	                stmtFunc.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            /* FINAL Listagem dos funcionários do departamento */
	        }

	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
