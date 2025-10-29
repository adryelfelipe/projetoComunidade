package Arquitetura.Dao.AdministradorDAO;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Enums.Departamento;

import java.sql.*;

public class UpdateAdministradorDAO {

    public void updateDepartamento(long id, Departamento departamento)
    {
        String querySql = "UPDATE Administrador "+
                "SET idDepartamento = ? "+
                "WHERE idAdministrador = ? ";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, departamento.getIdDepartamento());
            stmt.setLong(2, id);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar  departamento do administrador com ID: "+ id + e);
        }
    }
}
