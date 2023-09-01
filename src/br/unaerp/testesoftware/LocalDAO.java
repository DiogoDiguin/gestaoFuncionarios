package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class LocalDAO {
    //private Connection connection;

    PaisDAO daoP = ApplicationContext.getPaisDAO();

    Scanner scannerPais = new Scanner(System.in);
    Scanner entradaDpto = new Scanner(System.in);

    /*public LocalDAO() {
        new ConnectionFactory();
		this.connection = ConnectionFactory.getConnection();
    }*/

    public void insert(Local l) {
        int idPais = l.getPais().getIdPais();

        String sql = "insert into t_local (enderecoRua, codigoPostal, cidade, estadoProvincia, pais)"
                + " values (?, ?, ?, ?, ?)";

        try {
            try (
            	Connection connection = ConnectionFactory.getConnection();
            	PreparedStatement stmt = connection.prepareStatement(sql)) 
            {
                stmt.setString(1, l.getEnderecoRua());
                stmt.setString(2, l.getCodigoPostal());
                stmt.setString(3, l.getCidade());
                stmt.setString(4, l.getEstado());
                stmt.setInt(5, idPais);
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAll() {
        String sql = "SELECT l.idLocal, l.enderecoRua, l.codigoPostal, l.cidade, l.estadoProvincia, p.nomePais " +
                "FROM t_local l " +
                "JOIN t_pais p ON l.pais = p.idPais " +
                "ORDER BY l.idLocal ASC";

        try (
        	Connection connection = ConnectionFactory.getConnection();
        	PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) 
        {

            System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s\n", "ID", "ENDEREÇO", "CÓDIGO",
                    "CIDADE", "ESTADO", "PAÍS");

            while (rs.next()) {
                int id = rs.getInt("idLocal");
                String enderecoRua = rs.getString("enderecoRua");
                String codigoPostal = rs.getString("codigoPostal");
                String cidade = rs.getString("cidade");
                String estadoProvincia = rs.getString("estadoProvincia");
                String nomePais = rs.getString("nomePais");

                String linha = String.format("%-10s %-20s %-20s %-20s %-20s %-20s", id, enderecoRua, codigoPostal,
                        cidade, estadoProvincia, nomePais);
                System.out.println(linha);
            }
            System.out.printf("%n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*public void getAll() {
        String sql = "SELECT " +
                     "l.idLocal AS \"ID\", " +
                     "l.enderecoRua AS \"Endereço\", " +
                     "l.codigoPostal AS \"Código\", " +
                     "l.cidade AS \"Cidade\", " +
                     "l.estadoProvincia AS \"Estado\", " +
                     "p.nomePais AS \"País\" " +
                     "FROM t_local l " +
                     "JOIN t_pais p ON l.pais = p.idPais " +
                     "ORDER BY l.idLocal ASC";

        try (
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
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

    public void update(Local l) {
        String sql = "update t_local set enderecoRua=?, codigoPostal=?, cidade=?, estadoProvincia=?, pais=?"
                + " where idLocal=?";
        try {
            try (
                Connection connection = ConnectionFactory.getConnection();
            	PreparedStatement stmt = connection.prepareStatement(sql)) 
            {
                stmt.setString(1, l.getEnderecoRua());
                stmt.setString(2, l.getCodigoPostal());
                stmt.setString(3, l.getCidade());
                stmt.setString(4, l.getEstado());
                stmt.setInt(5, l.getPais().getIdPais());
                stmt.setInt(6, l.getIdLocal());
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Local l) {
        try {
            try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement("delete from t_local where idLocal=?")) 
            {
                stmt.setLong(1, l.getIdLocal());
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet listarFuncionarios(Local l) {
        Scanner entradaInt = new Scanner(System.in);
        
        // Primeira consulta para listar os departamentos do local
        String sql = "select * from t_departamento where local=?";
        
        try {
            try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) 
            {
                stmt.setInt(1, l.getIdLocal()); // Define o valor do parâmetro
                try (ResultSet rs = stmt.executeQuery()) {
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

                        // Solicitar o ID do departamento ao usuário
                        System.out.print("ID DEPARTAMENTO: ");
                        int idDpto = entradaInt.nextInt();
                        
                        // Segunda consulta para listar os funcionários do departamento
                        String sqlFuncionario = "select * from t_funcionario where departamento=?";
                        String sqlGerente = "SELECT f.idFuncionario, f.primeiroNome, f.ultimoNome, f.salario FROM t_departamento d JOIN t_funcionario f ON d.gerente "
                        		+ "= f.idFuncionario WHERE d.idDpto=?";
                        try (
                        	PreparedStatement stmtGerente = connection.prepareStatement(sqlGerente);
                        	PreparedStatement stmtFunc = connection.prepareStatement(sqlFuncionario)
                        ) {
                        	stmtGerente.setInt(1, idDpto);
                            stmtFunc.setInt(1, idDpto); // Define o ID do departamento
                            try (
                            	ResultSet rsGerente = stmtGerente.executeQuery();
                            	ResultSet rsFuncionario = stmtFunc.executeQuery()) 
                            {
                            	if (!rsGerente.next()) {
                                    System.out.println("Nenhum gerente encontrado neste departamento.");
                                } else {
                                    System.out.printf("\nGERENTE: ");

                                    do {
                                        String primeiroNome = rsGerente.getString("primeiroNome");
                                        String ultimoNome = rsGerente.getString("ultimoNome");

                                        String linhaGerente = String.format("%-15s %-15s", primeiroNome, ultimoNome);
                                        System.out.println(linhaGerente);
                                    } while (rsGerente.next());
                                }
                            	
                                if (!rsFuncionario.next()) {
                                    System.out.println("Nenhum funcionário encontrado neste departamento.");
                                } else {
                                    System.out.printf("\n%-10s %-20s %-20s %-10s\n", "ID", "1º NOME", "2º NOME", "SALÁRIO");

                                    do {
                                        int idFuncionario = rsFuncionario.getInt("idFuncionario");
                                        String primeiroNome = rsFuncionario.getString("primeiroNome");
                                        String ultimoNome = rsFuncionario.getString("ultimoNome");
                                        double salario = rsFuncionario.getDouble("salario");

                                        String linhaFuncionario = String.format("%-10s %-20s %-20s %-10s",
                                                idFuncionario, primeiroNome, ultimoNome, salario);
                                        System.out.println(linhaFuncionario);
                                    } while (rsFuncionario.next());
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        entradaInt.close();
        return null;
    }

}
