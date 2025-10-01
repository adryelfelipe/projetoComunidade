package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionarioDAO {

    // -- CRUD -- //

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
}
