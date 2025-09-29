package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PacienteDAO {

    public void inserirPaciente (Paciente paciente)
    {
        String querySql = "insert into Paciente (idPaciente, numeroCarteirinha, contatoEmergencia, statusPaciente) values (?, ?, ?, ?)";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(querySql))
        {
            stmt.setLong(1, paciente.getId());
            stmt.setString(2, paciente.getNumeroCarterinha() );
            stmt.setString(3, paciente.getContatoEmergencia());
            stmt.setString(4, paciente.getContatoEmergencia());

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Paciente : "+e.getMessage());
        }
    }
}
