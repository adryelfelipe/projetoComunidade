package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionarioDAO {

    // -- CRDU -- //

    // Inserção
    public void inserirFuncionario(Funcionario funcionario)
    {
        String querySql = "INSERT INTO Funcionario (idFuncionario, salario, cargaHorariaSemanal) VALUES (?, ?, ?)";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, funcionario.getId());
            stmt.setDouble(2, funcionario.getSalario());
            stmt.setInt(3, funcionario.getCargaHorariaSemanal());

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao inserir Funcionário : " +e.getMessage());
        }

    }

    // Remoção
    public void deletarFuncionario(String cpf) {
        String querySql = "DELETE f " +
                "FROM Funcionario f " +
                "JOIN Usuario u ON u.idUsuario = f.idFuncionario " +
                "WHERE u.cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql))
        {
            stmt.setString(1, cpf);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar Funcionário com o CPF " + cpf + ": " + e.getMessage());
        }
    }

    public void updateSalario(String cpf, double salario)
    {
        String querySql = "UPDATE Funcionario f"+
                "INNER JOIN Usuario u ON f.idFuncionario = u.idUsuario "+
                "SET salario = ? "+
                "WHERE cpf = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setDouble(1, salario);
            stmt.setString(2, cpf);
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar salário do funcionario com CPF: "+cpf+ e);
        }
    }

}
