package br.unaerp.testesoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionarioDAO {
    private Connection connection;

    public FuncionarioDAO() {
        new ConnectionFactory();
		this.connection = ConnectionFactory.getConnection();
    }

    public void insert(Funcionario f) {
        String sqlFunc = "INSERT INTO t_funcionario (primeiroNome, ultimoNome, salario, departamento) VALUES (?,?,?,?)";

        try {
            try (PreparedStatement stmtFunc = connection.prepareStatement(sqlFunc, Statement.RETURN_GENERATED_KEYS)) {
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
                        String sqlRF = "INSERT INTO t_regiaofuncionarios (idFuncionario, idRegiao, salario) VALUES (?,?,?)";
                        try (PreparedStatement stmtRF = connection.prepareStatement(sqlRF)) {
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

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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

    public void update(Funcionario funcionario) {
        String sql = "update t_funcionario set primeiroNome=?, ultimoNome=?, salario=?, departamento=? where idFuncionario=?";
        String sqlRegiao = "update t_regiaofuncionario set idRegiao=? where idFuncionario=?";

        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, funcionario.getPrimeiroNome());
                stmt.setString(2, funcionario.getUltimoNome());
                stmt.setFloat(3, funcionario.getSalario());
                stmt.setInt(4, funcionario.getDpto());
                stmt.setLong(5, funcionario.getIdFuncionario());
                stmt.execute();
            }

            try (PreparedStatement stmtRegiao = connection.prepareStatement(sqlRegiao)) {
                stmtRegiao.setInt(1, funcionario.getRegiao());
                stmtRegiao.setInt(2, funcionario.getIdFuncionario());
                stmtRegiao.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Funcionario funcionario) {
        try {
            try (PreparedStatement stmt = connection.prepareStatement("delete from t_funcionario where idFuncionario=?")) {
                stmt.setLong(1, funcionario.getIdFuncionario());
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void recalcSalario(int calcSalario, int perct, int idFunc) {
        String sqlSalario;

        if (calcSalario == 1) {
            sqlSalario = "UPDATE t_funcionario SET salario = salario + salario * ? WHERE idFuncionario = ?";
        } else if (calcSalario == 0) {
            sqlSalario = "UPDATE t_funcionario SET salario = salario - salario * ? WHERE idFuncionario = ?";
        } else {
            throw new IllegalArgumentException("Valor inválido para calcSalario");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sqlSalario)) {
            stmt.setDouble(1, perct / 100.0);
            stmt.setInt(2, idFunc);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
