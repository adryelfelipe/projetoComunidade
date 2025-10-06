package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PacienteDAO {

    // -- CRUD -- //

    // Inserção
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

    // Remoção
    public boolean deletarPaciente(long id) {
        String querySql = "DELETE FROM Paciente WHERE idPaciente = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(querySql)) {
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
            System.err.println("Erro ao deletar Paciente com ID " + id + ": " + e.getMessage());
            return false;
        }
    }
}
