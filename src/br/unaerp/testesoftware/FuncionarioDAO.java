package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionarioDAO {
    /*private Connection connection;

    public FuncionarioDAO() {
        new ConnectionFactory();
		this.connection = ConnectionFactory.getConnection();
    }*/

    public void insert(Funcionario f) {
        String sqlFunc = "INSERT INTO t_funcionario (primeiroNome, ultimoNome, salario, departamento) VALUES (?,?,?,?)";

        try {
            try (
            	Connection connection = ConnectionFactory.getConnection();
            	PreparedStatement stmtFunc = connection.prepareStatement(sqlFunc, Statement.RETURN_GENERATED_KEYS)) {
                stmtFunc.setString(1, f.getPrimeiroNome());
                stmtFunc.setString(2, f.getUltimoNome());
                stmtFunc.setFloat(3, f.getSalario());
                stmtFunc.setInt(4, f.getDpto());

                stmtFunc.execute();

                try (ResultSet generatedKeys = stmtFunc.getGeneratedKeys()) {
                    int generatedId = -1;
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }

                    if (generatedId != -1) {
                        String sqlRF = "INSERT INTO t_regiaofuncionario (idFuncionario, idRegiao, salario) VALUES (?,?,?)";
                        try (Connection connection2 = ConnectionFactory.getConnection();
                        	PreparedStatement stmtRF = connection2.prepareStatement(sqlRF)) {
                            stmtRF.setInt(1, generatedId);
                            stmtRF.setInt(2, f.getRegiao());
                            stmtRF.setFloat(3, f.getSalario());

                            stmtRF.execute();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAll() { // SELECT
        String sql = "SELECT * FROM t_funcionario";

        try (
        	Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) 
        {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ResultSet getSpecify(Funcionario funcionario) { // SELECT ESPECÍFICO
        String sql = "SELECT "
        		+ "f.idFuncionario, "
        		+ "f.primeiroNome, "
        		+ "f.ultimoNome, "
        		+ "f.salario, "
        		+ "d.nomeDpto AS departamento, "
        		+ "l.enderecoRua AS endereco, "
        		+ "l.codigoPostal, "
        		+ "l.cidade, "
        		+ "l.estadoProvincia, "
        		+ "p.nomePais AS pais, "
        		+ "r.nomeRegiao AS regiao "
        		+ "FROM "
        		+ "t_funcionario AS f "
        		+ "LEFT JOIN "
        		+ "t_departamento AS d ON f.departamento = d.idDpto "
        		+ "LEFT JOIN "
        		+ "t_local AS l ON d.local = l.idLocal "
        		+ "LEFT JOIN "
        		+ "t_pais AS p ON l.pais = p.idPais "
        		+ "LEFT JOIN "
        		+ "t_regiao AS r ON p.regiao = r.idRegiao "
        		+ "WHERE "
        		+ "f.idFuncionario = ?";

        try (
        	Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement(sql))
        {
        	stmt.setInt(1, funcionario.getIdFuncionario());
        	ResultSet rs = stmt.executeQuery(); 

            System.out.printf("%-10s %-20s %-20s %-15s %-25s %-35s %-10s %-15s %-3s %-10s %-10s\n", 
            		"ID", "1º Nome", "2º Nome", "Salário R$", "DEPARTAMENTO", "ENDEREÇO", "CÓDIGO POSTAL", 
            		"CIDADE", "UF", "PAÍS", "REGIÃO");

            while (rs.next()) {
                int id = rs.getInt("idFuncionario");
                String Pnome = rs.getString("primeiroNome");
                String Unome = rs.getString("ultimoNome");
                if (Unome == null) {
                    Unome = "";
                }
                String salario = rs.getString("salario");
                String departamento = rs.getString("departamento");
                String endereco = rs.getString("endereco");
                String codigoPostal = rs.getString("codigoPostal");
                String cidade = rs.getString("cidade");
                String estadoProvincia = rs.getString("estadoProvincia");
                String pais = rs.getString("pais");
                String regiao = rs.getString("regiao");

                String linha = String.format("%-10s %-20s %-20s %-15s %-25s %-35s %-10s %-15s %-3s %-10s %-10s", 
                		id, Pnome, Unome, salario, departamento, endereco, codigoPostal, cidade, estadoProvincia,
                		pais, regiao);
                System.out.println(linha);
            }
            System.out.printf("%n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*public void getAll() { // SELECT
        String sql = "SELECT idFuncionario AS \"ID\", primeiroNome AS \"Primeiro Nome\", ultimoNome AS \"Último Nome\", salario AS \"Salário R$\" FROM t_funcionario";

        try (
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) 
        {
            ResultSetPrinter printer = new ResultSetPrinter(); // Instancie a classe ResultSetPrinter

            //System.out.printf("%-10s %-20s %-20s %-20s\n", "ID", "1º Nome", "2º Nome", "Salário R$");
            
            printer.printResultSet(rs); // Use o método printResultSet para formatar a saída

            System.out.printf("%n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void update(Funcionario funcionario) {
        String sql = "update t_funcionario set primeiroNome=?, ultimoNome=?, salario=?, departamento=? where idFuncionario=?";
        String sqlRegiao = "update t_regiaofuncionario set idRegiao=?, salario=? where idFuncionario=?";

        try {
            try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                PreparedStatement stmtRegiao = connection.prepareStatement(sqlRegiao)
            ) {
                stmt.setString(1, funcionario.getPrimeiroNome());
                stmt.setString(2, funcionario.getUltimoNome());
                stmt.setFloat(3, funcionario.getSalario());
                stmt.setInt(4, funcionario.getDpto());
                stmt.setLong(5, funcionario.getIdFuncionario());
                stmt.execute();

                stmtRegiao.setInt(1, funcionario.getRegiao());
                stmtRegiao.setFloat(2, funcionario.getSalario());
                stmtRegiao.setLong(3, funcionario.getIdFuncionario());
                stmtRegiao.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Funcionario funcionario) {
        String sqlDptoGerente = "SELECT COUNT(*) FROM t_departamento WHERE gerente = ?";
        String sql = "DELETE FROM t_funcionario WHERE idFuncionario=?";
        String sql2 = "DELETE FROM t_regiaofuncionario WHERE idFuncionario=?";
        String sqlDptos = "SELECT * FROM t_departamento WHERE gerente=?";
        
        try (
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmtDptoGerente = connection.prepareStatement(sqlDptoGerente);
            PreparedStatement stmt1 = connection.prepareStatement(sql);
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            PreparedStatement stmtDptos = connection.prepareStatement(sqlDptos)
        ) {
            stmtDptoGerente.setLong(1, funcionario.getIdFuncionario());
            ResultSet rsDptoGerente = stmtDptoGerente.executeQuery();

            if (rsDptoGerente.next()) {
                int quantidade = rsDptoGerente.getInt(1);

                if (quantidade == 0) {
                    // Não há departamentos com o funcionário como gerente, então podemos excluir
                    stmt1.setLong(1, funcionario.getIdFuncionario());
                    stmt1.execute();

                    stmt2.setLong(1, funcionario.getIdFuncionario());
                    stmt2.execute();
                } else {
                    // Há departamentos com o funcionário como gerente, informe ao usuário
                    System.out.println("Este funcionário é gerente de um ou mais departamentos. "
                    		+ "Altere o gerente desses departamentos antes de excluí-lo.");
                    
                    stmtDptos.setInt(1, funcionario.getIdFuncionario());
                    ResultSet rsDptosG = stmtDptos.executeQuery();
                    
                    System.out.printf("%-10s %-20s\n", "ID", "NOME");
                    
                    while (rsDptosG.next()) {
                        int id = rsDptosG.getInt("idDpto");
                        String nomeDpto = rsDptosG.getString("nomeDpto");

                        String linha = String.format("%-10d %-20s", id, nomeDpto);
                        System.out.println(linha);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void recalcSalario(int calcSalario, int perct, int idFunc) {
        String sqlFuncionario;
        String sqlRegiao;

        if (calcSalario == 1) {
            sqlFuncionario = "UPDATE t_funcionario SET salario = salario + salario * ? WHERE idFuncionario = ?";
            sqlRegiao = "UPDATE t_regiaofuncionario SET salario = salario + salario * ? WHERE idFuncionario = ?";
        } else if (calcSalario == 0) {
            sqlFuncionario = "UPDATE t_funcionario SET salario = salario - salario * ? WHERE idFuncionario = ?";
            sqlRegiao = "UPDATE t_regiaofuncionario SET salario = salario - salario * ? WHERE idFuncionario = ?";
        } else {
            throw new IllegalArgumentException("Valor inválido para calcSalario");
        }

        try (
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmtFuncionario = connection.prepareStatement(sqlFuncionario);
            PreparedStatement stmtRegiao = connection.prepareStatement(sqlRegiao)
        ) {
            double percentage = perct / 100.0;

            stmtFuncionario.setDouble(1, percentage);
            stmtFuncionario.setInt(2, idFunc);
            stmtFuncionario.execute();

            stmtRegiao.setDouble(1, percentage);
            stmtRegiao.setInt(2, idFunc);
            stmtRegiao.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
