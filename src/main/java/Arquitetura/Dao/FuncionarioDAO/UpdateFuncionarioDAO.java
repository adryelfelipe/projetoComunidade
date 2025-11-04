package Arquitetura.Dao.FuncionarioDAO;

import Arquitetura.Config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFuncionarioDAO
{
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
}
