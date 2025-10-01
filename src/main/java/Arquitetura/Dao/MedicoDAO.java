package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicoDAO {

    // -- CRUD -- //

    // Inserção
    public void inserirMedico(Medico medico)
    {
        String querySQL = "INSERT INTO Medico (idMedico, plantao, especialidade, subEspecialidade, formacao) values (?, ?, ?, ?, ?)";

        try(
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(querySQL))
        {
            stmt.setLong(1, medico.getId());
            stmt.setString(2,medico.getPlantao());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setString(4, medico.getSubEspecialidade());
            stmt.setString(5, medico.getFormacao());

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Médico : "+e.getMessage());
        }
    }
}
