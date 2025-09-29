package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicoDAO {

    public void inserirMedico(Medico medico) throws SQLException
    {
        String querySQL = "INSERT INTO Medico (idMedico, salario, cargaHorariaSemanal, plantao, especialidade, subEspecialidade, formacao) values (?, ?, ?, ?, ?, ?, ?)";

        try(
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(querySQL))
        {
            stmt.setLong(1, medico.getId());
            stmt.setDouble(2, medico.getSalario());
            stmt.setInt(3,medico.getCargaHorariaSemanal());
            stmt.setString(4,medico.getPlantao());
            stmt.setString(5, medico.getEspecialidade());
            stmt.setString(6, medico.getSubEspecialidade());
            stmt.setString(7, medico.getFormacao());

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Médico : "+e.getMessage());
        }
    }
}
