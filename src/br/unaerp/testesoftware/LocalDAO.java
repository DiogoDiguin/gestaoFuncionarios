package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LocalDAO {
    private Connection connection;

    PaisDAO daoP = ApplicationContext.getPaisDAO();

    Scanner scannerPais = new Scanner(System.in);
    Scanner entradaDpto = new Scanner(System.in);

    public LocalDAO() {
        new ConnectionFactory();
		this.connection = ConnectionFactory.getConnection();
    }

    public void insert(Local l) {
        int idPais = l.getPais().getIdPais();

        String sql = "insert into t_local (enderecoRua, codigoPostal, cidade, estadoProvincia, pais)"
                + " values (?, ?, ?, ?, ?)";

        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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

    public void update(Local l) {
        String sql = "update t_local set enderecoRua=?, codigoPostal=?, cidade=?, estadoProvincia=?, pais=?"
                + " where idLocal=?";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
            try (PreparedStatement stmt = connection.prepareStatement("delete from t_local where idLocal=?")) {
                stmt.setLong(1, l.getIdLocal());
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet listarFuncionarios(Local l) {
        String sql = "select * from t_departamento where local=?";

        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

                        // Restante do código...
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
