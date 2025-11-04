package Arquitetura.Dao.FuncionarioDAO;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionarioDAO {

    // -- CRDU -- //






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
