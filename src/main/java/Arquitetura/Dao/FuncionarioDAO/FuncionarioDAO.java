package Arquitetura.Dao.FuncionarioDAO;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionarioDAO {

    // -- CRDU -- //



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
            System.err.println("Erro ao deletar Funcionário com o CPF: " + cpf);
        }
    }

    public void updateSalario(long id, double salario)
    {
        String querySql = "UPDATE Funcionario f"+
                "INNER JOIN Usuario u ON f.idFuncionario = u.idUsuario "+
                "SET salario = ? "+
                "WHERE idUsuario = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setDouble(1, salario);
            stmt.setLong(2, id);

            stmt.executeQuery();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar salário do funcionario com ID: "+id + e);
        }
    }
    public void updateCargaHorariaSemanal(long id, int cargaHorariaSemanal)
    {
        String querySql = "UPDATE Funcionario f"+
                "INNER JOIN Usuario u ON f.idFuncionario = u.idUsuario "+
                "SET cargaHorariaSemanal = ? "+
                "WHERE idUsuario = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setInt(1, cargaHorariaSemanal);
            stmt.setLong(2, id);

            stmt.executeQuery();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar carga horária semanal do funcionario com ID: "+id + e);
        }
    }
}
