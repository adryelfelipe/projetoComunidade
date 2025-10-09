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
        String querySQL = "INSERT INTO Medico (idMedico, idPlantao, especialidade, subEspecialidade, formacao) values (?, ?, ?, ?, ?)";

        try(
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(querySQL))
        {
            stmt.setLong(1, medico.getId());
            stmt.setLong(2,medico.getPlantao().getIdPlantao());
            stmt.setLong(3, medico.getEspecialidade().getIdEspecialidade());
            stmt.setString(4, medico.getSubEspecialidade());
            stmt.setString(5, medico.getFormacao());

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Médico : "+e.getMessage());
        }
    }

    // Remoção
    public boolean deletarMedico(long id) {
        String querySql = "DELETE FROM Medico WHERE idMedico = ?";

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
            System.err.println("Erro ao deletar Médico com ID " + id + ": " + e.getMessage());
            return false;
        }
    }
}
