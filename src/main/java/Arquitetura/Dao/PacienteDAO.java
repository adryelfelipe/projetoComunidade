package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            stmt.setLong(4, paciente.getStatusPaciente().getIdPaciente());

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Paciente : "+e.getMessage());
        }
    }

    // Remoção
    public void deletarPaciente(String cpf) {
        String querySql = "DELETE p " +
                          "FROM Paciente p " +
                          "JOIN Usuario u ON p.idPaciente = u.idUsuario " +
                          "WHERE u.cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql))
        {

            stmt.setString(1, cpf);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar Paciente com CPF " + cpf + ": " + e.getMessage());
        }
    }

    public void updateNumeroCarteirinha(long idConsulta, String numCarteirinha ) throws SQLException
    {
        String querySql = "UPDATE Consulta "+
                "SET numeroCarteirinha = ? "+
                "WHERE idConsulta = ? ";
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, numCarteirinha);
            stmt.setLong(2, idConsulta);

            stmt.executeUpdate();

        }
        catch (Exception e) {
            throw new SQLException("Erro ao atualizar número da carteirinha do paciente com ID: "+idConsulta, e);
        }
    }

    public boolean isCpfPaciente(String cpf) {
        String querySql = "SELECT tipoUsuario FROM Usuario WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long tipo = rs.getLong("tipoUsuario");
                return tipo == 1;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar o CPF do Paciente. ");
        }
        return false;
    }

    public void updateNumeroCarteirinha(String cpf, String numCarteirinha )
    {
        String querySql = "UPDATE Paciente p "+
                "INNER JOIN Usuario u ON p.idPaciente = u.idUsuario "+
                "SET numeroCarteirinha = ? "+
                "WHERE cpf = ? ";
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, numCarteirinha);
            stmt.setString(2, cpf);

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Erro ao atualizar número da carteirinha do paciente com ID: "+cpf+ e);
        }
    }

    public void updateContatoEmergencia(String cpf, String contatoEmergencia)
    {
        String querySql = "UPDATE Paciente p"+
                "INNER JOIN Usuario u ON p.idPaciente = u.idUsuario "+
                "SET contatoCarteirinha = ? "+
                "WHERE cpf = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, contatoEmergencia);
            stmt.setString(2, cpf);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar o contato de emergência do paciente com o ID: "+cpf+ e);
        }
    }

}
