package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Consulta;

import java.sql.*;
import java.time.LocalDate;

public class ConsultaDAO
{
    public void marcarConsulta(Consulta consulta)
    {
        String querySql = "INSERT INTO Consulta (dataConsulta, horarioConsulta, relatorio, idPaciente, idMedico, idExame) VALUES (?,?,?,?,?,?); ";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setDate(1, Date.valueOf(consulta.getDataConsulta()));
            stmt.setTime(2, Time.valueOf(consulta.getHorarioConsulta()));
            stmt.setString(3, consulta.getRelatorio());
            stmt.setLong(4, consulta.getPaciente().getId());
            stmt.setLong(5, consulta.getMedico().getId());
            stmt.setLong(6, consulta.getIdConsulta());

            stmt.executeUpdate(querySql);

            try(ResultSet resultSet = stmt.getGeneratedKeys())
            {
                if(resultSet.next())
                {
                    consulta.setIdConsulta(resultSet.getInt(1));
                }
            }

        }
        catch (SQLException e)
        {
            System.err.println("Não foi possível marcar uma Consulta: "+e.getMessage());
        }
    }

    // Remoção
    public boolean deletarConsulta(long id)
    {

        String querySql = "delete from Consulta where idConsulta = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(querySql))
        {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            // Retornar True se conseguiu deletar
            if (linhasAfetadas > 0) {
                return true;
            }
            // E retornara False se não conseguiu ou não existe
            else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar Consulta com ID " + id + ": " + e.getMessage());
            return false;
        }
    }
}
